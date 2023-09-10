package com.example.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
윈도우단축키
*getter setter : alt + insert */
@Entity//디비를 관리함
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//pk 알아서 생성
    private Long id; //시스템이 저장하는 아이디
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
