package com.example.hellospring.controller;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hellocontroller {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","spring");//data라는 키의 값으로 hello
        return "hello";//hello라는 html을 찾음
    }

    @GetMapping("hello-mvc")//소스를 보면 html태그들이 있는데
    public String helloMvc(@RequestParam(name = "name")String name, Model model){//required 가 기본이 true이기 때문에 파라미터를 넘겨줘야한다
        model.addAttribute("name",name );
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody//html body가 아니라 http 웅답바디 부분에 값을 넣어주겠다
    public String helloString(@RequestParam("name")String name){
        return "hello " + name; //소스 보기하면 html태그가 없다
    }

    //데이터를 내놓아야할때
    @GetMapping("hello-api")
    @ResponseBody//html body가 아니라 http 웅답바디 부분에 값을 넣어주겠다
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    //xml(<HTML></HTML>) 방식 무겁다 -> 요즘은 json 방식
    static class Hello {
        private String name;
        //GETTER SETTER 방식 자바빈 표준방식(PROPERTY 접근방식)
        public String getName() {
            return name;
        }

        public String setName(String name) {
            return this.name = name;
        }
    }




}

