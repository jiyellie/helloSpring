package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemberRepository;
import com.example.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
 단축키
 테스트 자동 생성 CTRL SHIFT T
 이전 실행 실행하기 crtl f10
 */
//통합테스트 vs 단위 테스트 : 단위 테스트가 제일 좋음 통합테스트의 경우 거의 잘못 만들어서 하게 되는 경우가 맣음
@SpringBootTest //ㄹㅇ 스프링 컨테이너를 올려서 실행함
@Transactional
/* @Transactional: 테스트 케이스에 이 애노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고,
테스트 완료 후에 항상 롤백한다. 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지
않는다.

 test 케이스에 transactional어노테이션을 사용하면 커밋을 안시키고 롤백을 시킨다.*/
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

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

}