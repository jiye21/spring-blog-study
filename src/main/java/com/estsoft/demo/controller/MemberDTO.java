package com.estsoft.demo.controller;

import com.estsoft.demo.repository.Member;
import lombok.Getter;

@Getter
public class MemberDTO {
    private final Long id;
    private final String name;
    private final TeamDTO teamDTO;

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.teamDTO = new TeamDTO(member.getTeam());
    }
}
