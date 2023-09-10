package com.example.hellospring.service;


import com.example.hellospring.repository.JdbcMemberRepository;
import com.example.hellospring.repository.JdbcTemplateMemberRepository;
import com.example.hellospring.repository.JpaMemberRepository;
import com.example.hellospring.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

//자바코드로 직접 등록
//장점: 디비를 바꿀때 그냥 여기서 바꾸기만 하면된다
//어떠한 코드도 만들지 않고 구현체를 확장했을 뿐
 @Configuration
    public class SpringConfig {
        private final MemberRepository memberRepository;
        public SpringConfig(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }
        @Bean
        public MemberService memberService() {
            return new MemberService(memberRepository);
        }
    }

