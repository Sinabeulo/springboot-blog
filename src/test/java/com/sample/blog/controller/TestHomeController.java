package com.sample.blog.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestHomeController {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void Test_GetHome(){
        // when
        String body = restTemplate.getForObject("/home",String.class);

        // then
        //assertThat()
    }
}
