package com.estsoft.demo.service;

import com.estsoft.demo.repository.Member;
import com.estsoft.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> getMemberAll() {
        return memberRepository.findAll();
    }

    // member 테이블에 insert 쿼리
    public Member insertMember(Member member) {
        Member savedMemeber = memberRepository.save(member);
        return savedMemeber;
    }

    public Member selectMemberById(Long id) {
        Optional<Member> optMember = memberRepository.findById(id);
        return optMember.orElseGet(Member::new); //optMember.orElse(new Member()) -> 안티패턴!!
    }

    public void deleteMemberById(Long id) {
        memberRepository.deleteById(id);
    }

    public List<Member> selectMemberByName(String name) {
        return memberRepository.findByNameContaining(name);
    }
}
