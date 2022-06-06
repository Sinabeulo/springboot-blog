package com.sample.blog.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    public Member save(Member member){

        member.setId(++sequence);
        log.info("save: member={}",member);

        // TODO - 중복 등록 방지
        Optional<Member> dup = this.findByLoginId(member.getUserId());
        if(!dup.isEmpty())
            return null;        // 중복데이터 일 경우 null 로 반환하여 판단
        // TODO - member pw 암호화 처리해서 저장


        // 검증 후 저장
        store.put(member.getId(), member);

        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId){

        return this.findAll().stream()
                .filter(m->m.getUserId().equals(loginId))
                .findFirst();
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public boolean login(Member memeber){
        Optional<Member> checkMember = findByLoginId(memeber.userId);
        if(checkMember.isEmpty()) {
            return false;
        }

        return checkMember.get().pw.equals(memeber.getPw());
    }

    public void clearStore(){
        store.clear();
    }
}
