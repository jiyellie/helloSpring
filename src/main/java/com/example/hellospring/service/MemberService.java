package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemberRepository;
import com.example.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
  단축키
  변수 추출 : ctrl + alt + v
  새로운 메서드 뽑아 냄 : CTRL + ALT + SHIFT + T
 */
//@Service
public class MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    //test 시 다른 레파지토리 테스트 방지를 위함
    private final MemberRepository memberRepository;
//    @Autowired //스프링이 알아서 주입
    public MemberService(MemberRepository memberRepository) {//직접 생성이 아닌 외부에서 넣어주는 방식으로 변경
        this.memberRepository = memberRepository;
    }


    /**
     * 회원가입
     * @param member
     * @return
     */
    public  Long join(Member member) {
        validateDuplicateMmeber(member); //중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMmeber(Member member) {
        //그냥 get해도 되지만 별로 권장하지 않음
        //ifPresent
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 회원 전체 조회
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원조회
     * @param memberId
     * @return
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
