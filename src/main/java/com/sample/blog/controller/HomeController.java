package com.sample.blog.controller;

import com.sample.blog.domain.member.Member;
import com.sample.blog.domain.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Slf4j
@Controller
public class HomeController {

    private MemberRepository memberRepository;

    private static Member loginMember = null;

    //@Autowired
    public HomeController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        log.info("is memberRepository null > {}", memberRepository == null);
    }

    @GetMapping("/index")
    public String index(){
        log.info("Request : index");
        return "Index.html";
    }

    @GetMapping("/home")
    public String home(){
        log.info("Request : home");
        return "Home";
    }

    @GetMapping("/login")
    public void login(){
        log.info("Request : login");
    }

    @PostMapping("/login")
    public ModelAndView login(Member memberInfo){
        Optional<Member> loginMemberOpt = null;
        //Member loginMember = null;

        if(!memberRepository.login(memberInfo)){
            log.info("login failed > {}",memberInfo);
            return new ModelAndView("login");
            //return "login";
        }

        // there is a problem with 'member', because of login.mustache has not properties 'nick', 'id'
        // so that we have to find a correct member
        loginMemberOpt = memberRepository.findByLoginId(memberInfo.getUserId());

        if(loginMemberOpt == null || loginMemberOpt.isEmpty()){
            log.info("login failed > {} is not a member", memberInfo.getUserId());
            return new ModelAndView("login");
        }else{
            loginMember = loginMemberOpt.get();
        }

        log.info("login member > {}",loginMember);
        ModelAndView modelAndView = new ModelAndView("home","User",loginMember);

        return modelAndView;
    }

    @GetMapping("/join")
    public void join(){
        log.info("Request : join");
    }

    @PostMapping("/join")
    public String join(Member member){
        Member joinedMember = memberRepository.save(member);
        if(joinedMember == null){
            log.info("duplicated user > {}",member);
            return "join";
        }

        return "home";
    }
}
