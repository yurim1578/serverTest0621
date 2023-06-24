package com.project.metasu.member.controller;

import com.project.metasu.admin.service.ReviewService;
import com.project.metasu.item.domain.entity.Review;
import com.project.metasu.member.domain.entity.Member;
import com.project.metasu.member.dto.MemberDto;
import com.project.metasu.member.repository.MemberRepository;
import com.project.metasu.member.service.MemberService;
import com.project.metasu.member.service.OrderMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
  @Autowired
  private final ReviewService reviewService;
  @Autowired
  private final MemberService memberService;
  @Autowired
  private final OrderMasterService orderMasterService;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private final MemberRepository memberRepository;




  @GetMapping("/delete")
  public String delete(@RequestParam(value="id")String id){

    memberRepository.deleteById(id);  // 해당 회원 삭제
    return "redirect:/member/login";
  }
  @PostMapping("/update/confirm")
  public String confirm(@RequestParam(value="passwordConfirm") String passwordConfirm, Model model, Authentication authentication){
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String memberId = userDetails.getUsername();
    Member member = memberService.getInfo(memberId);
    if(bCryptPasswordEncoder.matches(passwordConfirm,member.getMemberPw())){
      model.addAttribute("confirm", "true");
    }else{
      model.addAttribute("confirm","false");
    }
    model.addAttribute("member",member);

    return "member/mypage";
  }



  @PostMapping("/update")
  public String update(@RequestParam(value="id")String id,
                       @RequestParam(value = "password")String password,
                       @RequestParam(value="name")String name,
                       @RequestParam(value="email")String email,
                       @RequestParam(value="phone")String phone,
                       //@RequestParam(value="auth")String auth,
                       @RequestParam(value="addr")String addr,Model model){

    MemberDto member = memberService.findMemberById(id);
    password = bCryptPasswordEncoder.encode(password);  // 인코딩

    member.setMemberPw(password);
    member.setMemberName(name);
    member.setMemberEmail(email);
    member.setMemberPhone("콜");
    //member.setMemberAuth(auth);
    member.setMemberAddr1(addr);
    memberRepository.save(member.toEntity());

    System.out.println(member.getMemberName());
    return "redirect:/member/mypage";

  }
  @GetMapping("/login")
  public String Login() {
    return "member/login";
  }
  // 로그인 실패 시 로그인 페이지로 이동

  @PostMapping("/login")
  public String Login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
    // 사용자 인증 로직 구현
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    System.out.println(username);
    System.out.println(password);
    if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
      model.addAttribute("error", "Invalid username or password");
      return "login";
    }

    // 인증 성공 시 세션에 인증 정보 저장
    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authentication);

    return "redirect:/home/main";
  }
  @GetMapping("/register")
  public String Register(Model model) {
    model.addAttribute("memberDto", new MemberDto());
    return "member/register";
  }
  @PostMapping("/register")
  public String register(Model model, @ModelAttribute("memberDto") MemberDto memberDto) {

    Optional<Member> member = memberRepository.findByMemberId(memberDto.getMemberId()); // 폼에 입력한 id를 가져옴.
    if(member.isPresent()) {// 해당 ID가 있으면,
      System.out.println("중복된 아이디입니다.");
      model.addAttribute("memberDto",memberDto);
      return "member/register";
    }
    else {  // 해당 ID가 없으면 회원가입
      memberService.insert(memberDto);
      return "redirect:/member/login";
    }
  }


  @GetMapping("/error")
  public String loginError(Model model) {
    model.addAttribute("loginError", true);
    return "member/login";
  }

  @GetMapping("/id")  // 비밀번호 찾기 페이지
  public String id(){
    return "member/id";
  }

  @PostMapping("/id") //아이디 찾기
  public String id(Model model,@RequestParam("name") String name, @RequestParam("email")String email){

    Optional<Member> memberOptional  = memberRepository.findMemberByMemberNameAndMemberEmail(name,email);

    if(memberOptional.isPresent()){
      String memberId = memberOptional.get().getMemberId();
      model.addAttribute("memberId",memberId);
      return "member/login";
    }else{  // 아이디가 없으면
      model.addAttribute("message", true);
      return "member/id";
    }
  }

  @GetMapping("/password")  // 비밀번호 찾기 페이지
  public String password(){
    return "member/password";
  }

  @PostMapping("/password") // 비밀번호 전송
  public String password(@RequestParam("id") String id,
                         @RequestParam("email") String email,Model model
  ){

    Optional<Member> member = memberRepository.findMemberByMemberIdAndMemberEmail(id, email);

    if(member.isPresent()){
      MemberDto memberDto = memberService.findMemberById(member.get().getMemberId());
      memberDto.setMemberPw(bCryptPasswordEncoder.encode("123"));
      memberRepository.save(memberDto.toEntity()); // 저장
      memberService.passwordSend(id); // 비밀번호 전송
      model.addAttribute("message",true);
      return "/member/login";
    }else{
      model.addAttribute("message",true);
      return "/member/password";
    }
  }





}