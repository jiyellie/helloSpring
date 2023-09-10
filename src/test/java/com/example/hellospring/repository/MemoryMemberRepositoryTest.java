package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 각각의 메소드가 끝날때 마다 실행이 된다.
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();//get으로 바로 꺼내는건 좋은 방법은 아님
        //Assertions.assertEquals
        //Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result); //많이 사용하는 방식
    }

    //전체 테스트 시 에러가 난다 -> test 순서는 보장이 안된다 -> 똑같은 이름의 고객을 각 메서드 별로 만들었고 이미 저장이 되어있기에 에러가난다
    //위와 같은 경우를 방지하기 위해서는 각각의 CLEAR하는 부분을 만들어 주면 된다.
    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }


}
