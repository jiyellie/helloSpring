package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
 단축키
 테스트 자동 생성 CTRL SHIFT T
 이전 실행 실행하기 crtl f10
 */
class MemberServiceTest {

    //MemberService memberService = new MemberService();
    //memberService 테스트시 불필요한 MemoryMemberRepository를 가져와서 실행하고 있다 다른 리파지토리를 테스트하고 있는거나 다름없음
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void beforeEach() {
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);//di 외부 주입
    }

    @AfterEach // 각각의 메소드가 끝날때 마다 실행이 된다.
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    void 회원가입() { //테스트시 굳이 영문으로 할 필요는없음 빌드 될때 테스트는 포함이 안됨
        //선호하는 테스트 순서
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //멤버는 다른데 이름이 같을 때 먼저 만들어놨던 중복 데이터오류가 나야하는 걸 테스트
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //assertThrows 두 번째 인자인 () -> memberService.join(member2)를 실행하여 첫 번째 인자인 예외 타입과 같은지 검사합니다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*try {
            memberService.join(member2);
            fail("예외가 발생해야 한다");
        }catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        //then
    }

    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}