/*
package com.project.metasu.member.service.impl;

import com.project.metasu.member.domain.entity.Member;
import com.project.metasu.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(username);
        if (member == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(member.getMemberId(), member.getMemberPw(), new ArrayList<>());
    }
}
*/
package com.project.metasu.member.service.impl;

import com.project.metasu.member.domain.entity.Member;
import com.project.metasu.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private final MemberRepository memberRepository;


    public UserDetailsServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        Optional<Member> member = this.memberRepository.findByMemberId(memberId);
//        return member.isPresent()? member.get() : null;

        Optional<Member> optionalMember = memberRepository.findByMemberId(username);
        Member member;
        member = optionalMember.get();
        // Member 객체를 사용하는 코드 작성
        if (member == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(member.getMemberId(), member.getMemberPw(), new ArrayList<>());
    }
}
