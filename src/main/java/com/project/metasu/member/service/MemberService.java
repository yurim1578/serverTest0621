package com.project.metasu.member.service;

import com.project.metasu.member.domain.entity.Member;
import com.project.metasu.member.dto.MemberDto;
import com.project.metasu.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
public class MemberService  {


  private final MemberRepository memberRepository;
  @Autowired
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.memberRepository = memberRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }


  public void insert(MemberDto memberDto) {
    memberDto.setMemberPw(bCryptPasswordEncoder.encode(memberDto.getMemberPw()));
    memberDto.setMemberAuth("MB");
    memberDto.setMemberAddr2("home");
    memberRepository.save(memberDto.toEntity());
  }
  public MemberDto findMemberByIdAndEmail(String memberId, String email) {
    return memberRepository.findByMemberIdAndMemberEmail(memberId, email)
        .map(MemberDto::new)
        .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + memberId + ", email=" + email));
  }
  public MemberDto findMemberById(String memberId) {
    return memberRepository.findById(memberId)
        .map(MemberDto::new)
        .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + memberId));
  }

  public Member getInfo(String memberId) {
    Optional<Member> member = this.memberRepository.findByMemberId(memberId);
    return member.isPresent()? member.get() : null;
  }

  public Member getCurrentUser() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (principal instanceof UserDetails) {
      String username = ((UserDetails) principal).getUsername();
      Optional<Member> member = this.memberRepository.findByMemberId(username);
      return member.isPresent()? member.get() : null;
    }

    return null;
  }

  public boolean matchesPassword(String rawPassword, String encodedPassword) {
    return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
  }
  public boolean passwordSend(String id) {  //및 Java Mail 라이브러리와 SMTP 프로토콜을 통한 비밀번호 찾기

    Optional<Member> result = memberRepository.findByMemberId(id);
    String host = "smtp.naver.com";
    String port = "465";
    String email = "ssjjhh09cc@naver.com";       //"tkflwk23@naver.com"
    String password = "eksdnjfdmlqka";           //"tjdwns12@@@"

    if (!result.isPresent()) {
      return false;
    } else {

      Properties props = System.getProperties();
      props.put("mail.smtp.host", host);  // SMTP 서버 주소
      props.put("mail.smtp.port", port);  // SMTP 포트 번호
      props.put("mail.smtp.auth", "true");    // 권한 설정
      props.put("mail.smtp.ssl.enable", "true");          // ssl 보안연결 사용 ( 인터넷 보안 프로토콜 )
      //  props.put("mail.smtp.starttls.enable","true");  // tcl 보안연결

      Session session = Session.getInstance(props, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(email, "eksdnjfdmlqka");   // 송신자의 이메일 주소 및 비밀번호
        }
      });

      session.setDebug(true);   //for debug

      try {
        Message mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(email));
        mimeMessage.setRecipient(Message.RecipientType.TO,
            new InternetAddress("ssjjhhcc@gmail.com"));    // 지정된 이메일로 회원가입 성공 메시지 발송
        mimeMessage.setSubject("비밀번호를 찾았습니다.");
        mimeMessage.setText(result.get().getMemberName() + "님의 비밀번호는 123 입니다.");

        Transport transport = session.getTransport();
        transport.connect(host, email, password);  // SMTP 서버 주소, 발송자 ID, 비밀번호 와 연결
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
      } catch (MessagingException e) {
        System.out.println("존재하지 않는 메일주소입니다.");
      }
      return true;
    }
  }

  public Optional<Member> findMemberByNameAndEmail(String name, String email) {
    return this.memberRepository.findByMemberNameAndMemberEmail(name,email);


  }

}
