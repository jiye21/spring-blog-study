package com.estsoft.demo.repository;

import jakarta.persistence.*;
import lombok.Getter;
import java.util.*;

@Getter
@Entity
public class Team {
    @Id
    @Column(name = "team_id", updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
}
