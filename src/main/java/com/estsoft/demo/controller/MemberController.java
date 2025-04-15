package com.estsoft.demo.controller;

import com.estsoft.demo.repository.Member;
import com.estsoft.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    @ResponseBody
    @GetMapping("/members")
    public List<MemberDTO> showMembers() {
        List<Member> memberAll = memberService.getMemberAll();
        return memberAll.stream()
                .map(MemberDTO::new)
                .toList();
    }

    @ResponseBody
    @PostMapping("/members")
    public Member saveMember(@RequestBody Member member) {
        return memberService.insertMember(member);
    }

    // GET /members/{id} -> member 단건 조회
    @ResponseBody
    @GetMapping("/members/{id}")
    public Member selectMemberById(@PathVariable Long id) {
        return memberService.selectMemberById(id);
    }


    // DELETE /members/{id}  -> member 단건 삭제
    @ResponseBody
    @DeleteMapping("/members/{id}")
    public String deleteMemberById(@PathVariable Long id) {
        memberService.deleteMemberById(id);
        return "OK";
    }

    // GET /search/members?name=---
    @GetMapping("/search/members")
    @ResponseBody
    public List<Member> selectMemberByName(@RequestParam("name") String name) {
        return memberService.selectMemberByName(name);
    }


    @GetMapping("/hi")
    public String htmlPage() {
        return "hi";        // hi.html
    }

}
