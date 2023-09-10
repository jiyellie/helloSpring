package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

/*단축키
 Overrride : alt + enter
 */
//@Repository //구현체에서
public class MemoryMemberRepository implements MemberRepository{
    /*
     실무에서는 동시성문제가 있을 수 있는 공유되는 변수일때는 hashMap이 아닌 con~를, long이 아닌 어텀롱?을 사용해야한다
     */
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        //id 세팅
        member.setId(++sequence);
        //store 저장
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        /*
        방법1
        아래와 같이 받으면 null이 반환될 경우 null처리가 애매함
        return store.get(id);
        */

        //방법2
        //Optional을 활용하여 감싸서 반환해서 null처리가 가능하다
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //stream
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();//하나가 나올때까지 돌려서 있으면 반환 아니면 Optional로 감싸서 반환

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
