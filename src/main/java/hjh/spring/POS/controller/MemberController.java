package hjh.spring.POS.controller;

import hjh.spring.POS.domain.Member;
import hjh.spring.POS.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member")
public class MemberController
{
    private final MemberService memberService;

    public MemberController(MemberService memberService)
    {
        this.memberService = memberService;
    }

    @GetMapping("/join")
    public String signupForm(Model model)
    {
        model.addAttribute("member", new Member());
        return "join";
    }

    @PostMapping("/join")
    public String joinMember(Member member, Model model)
    {
        boolean isDuplicateEmail = memberService.checkDuplicateEmail(member.getEmail());
        if (isDuplicateEmail)
        {
            String message = "중복된 이메일입니다.";
            model.addAttribute("duplicateEmail", message);
            return "join";
        }
        else
        {
            memberService.register(member);
            model.addAttribute("member", member);
            return "joinSuccess";
        }
    }

    @GetMapping("/login")
    public String loginForm(Model model)
    {
        model.addAttribute("member", new Member());
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model)
    {
        boolean loginSuccess = memberService.login(email, password);
        if (loginSuccess)
        {
            return "redirect:/";
        }
        else
        {
            String message = "잘못된 email/password입니다.";
            model.addAttribute("loginFailed", message);
            return "login";
        }
    }
}
