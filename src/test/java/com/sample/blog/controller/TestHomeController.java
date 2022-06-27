package com.sample.blog.controller;

import com.sample.blog.domain.member.Member;
import com.sample.blog.domain.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestHomeController {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void Test_GetHome(){
        // when
        String result = restTemplate.getForObject("/home",String.class);

        // then
        assertThat(result).isNotEmpty();
    }

    @Test
    public void Test_Join(){
        // when
        Member member = new Member();
        member.setId(1L);
        member.setNick("NickName");
        member.setPw("Password");
        member.setUserId("TestId");
        System.out.println("save member : " + member);
        String result = restTemplate.postForObject("/join", member, String.class);

        // then
        assertThat(result).contains("Home");
    }

}
