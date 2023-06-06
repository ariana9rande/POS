package hjh.spring.POS.controller;

import hjh.spring.POS.domain.Member;
import hjh.spring.POS.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController
{
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService)
    {
        this.memberService = memberService;
    }

    @GetMapping("/signup")
    public String signupForm(Model model)
    {
        model.addAttribute("member", new Member());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(Member member)
    {
        memberService.signup(member);
        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String loginForm(Model model)
    {
        model.addAttribute("member", new Member());
        return "login";
    }

    @PostMapping("/login")
    public String login(Member member)
    {
        boolean loginSuccess = memberService.login(member);
        if (loginSuccess)
        {
            return "redirect:/";
        }
        else
        {
            return "login";
        }
    }
}
