package com.sample.blog.domain.member;

import lombok.Data;

@Data
public class Member {
    Long id;
    String userId;
    String pw;
    String nick;
}
