package com.sample.blog.controller;

import com.sample.blog.domain.member.Member;
import com.sample.blog.domain.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class HomeController {

    private MemberRepository memberRepository;

    //private static Member loginMember = null;

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
    public ModelAndView login(Member member){
        if(!memberRepository.login(member)){
            log.info("login failed > {}",member);
            return new ModelAndView("login");
            //return "login";
        }

        log.info("login member > {}",member);
        ModelAndView modelAndView = new ModelAndView("home","User",member);
        //loginMember = member;
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
