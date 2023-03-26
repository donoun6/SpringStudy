package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 스프링 컨테이너에 해당 어노테이션을 사용하면 스프링이 뜰때 해당 클래스 객체를 생성하여 넣어두고 스프링이 관리를 한다.
 * 이것을 스프링 컨테이너에서 스프링빈이 관리된다 라고 표현한다.
 */
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) { //생성자
        this.memberService = memberService;
        System.out.println("memberService = " + memberService.getClass());
    }

    @GetMapping("/members/new") //조회할때 주로 사용
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") //데이터를 form같은데 넣어서 전달할때 주로 사용
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);

        return "members/membersList";
    }
}
