package com.example.hellospring.controller;

import com.example.hellospring.domain.Member;
import com.example.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
//메모리안에 저장되어있기 때문에 서버를 내리면 데이터는 다 지워짐

//컴포넌트 스캔방식
//싱글톤으로 등록 - 하나씩 등록을 하여 공유한다
@Controller //스프링이 돌 때 스프링 컨테이너가 생성이 되는데 이 어노테이션이 있으면 이 컨테이너에 데이터를 담아서놓는다. -> 스프링 빈이 관리된다
public class MemberController {
    //회원 정보를 여러곳에서 가져다 씀, 이컨트롤러에서는 많은 서비스 메서드가 필요하지 않음
    //public  final MemberService memberService = new MemberService();

    public MemberService memberService;
    //생성자 주입이 좋음
    @Autowired //생성자 호출시 스프링이 스프링 컨테이너에서 memberService를 찾아서 연결해 준다. 의존성 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public  String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);//value 객체를 name 이름으로 추가한다. 뷰 코드에서는 name으로 지정한 이름을 통해서 value를 사용한다.
        return "members/memberList";
    }
}
