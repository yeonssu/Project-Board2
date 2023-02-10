package com.project.board.controller;

import com.project.board.dto.MemberDto;
import com.project.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member/save")
    public String saveForm() {
        return "membersaveform";
    }

    @PostMapping("/member/savepro")
    public String savePro(@ModelAttribute MemberDto memberDto, Model model) {
        memberService.savePro(memberDto);

        model.addAttribute("message", "회원가입이 완료되었습니다");
        model.addAttribute("searchUrl", "/member/login");
        return "message";
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "memberloginform";
    }

    @PostMapping("/member/loginpro")
    public String loginPro(@ModelAttribute MemberDto memberDto, HttpSession session, Model model) {
        MemberDto loginResult = memberService.loginPro(memberDto);
        if (loginResult != null) {
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            model.addAttribute("message", "성공적으로 로그인되었습니다");
            model.addAttribute("searchUrl", "/member/main");
            return "message";
        } else {
            model.addAttribute("message", "아이디와 비밀번호가 일치하지 않습니다");
            model.addAttribute("searchUrl", "/member/login");
            return "message";
        }
    }

    @GetMapping("/member/main")
    public String memberMain() {
        return "membermain";
    }

    @GetMapping("/member")
    public String findAll(Model model) {
        List<MemberDto> memberDtoList = memberService.findAll();
        model.addAttribute("memberList", memberDtoList);
        return "memberlist";
    }

    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model) {
        MemberDto memberDto = memberService.findbyId(id);
        model.addAttribute("member", memberDto);
        return "memberdetail";
    }

    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model) {
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDto memberDto = memberService.updateForm(myEmail);
        model.addAttribute("updateMember", memberDto);
        return "memberupdate";
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDto memberDto) {
        memberService.update(memberDto);
        return "redirect:/member/" + memberDto.getId();
    }

    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id){
        memberService.deleteById(id);
        return "redirect:/member/";
    }
    @GetMapping("/member/logout")
    public String logout(HttpSession session, Model model){
        session.invalidate();
        model.addAttribute("message", "로그아웃되었습니다");
        model.addAttribute("searchUrl", "/");
        return "message";
    }
}
