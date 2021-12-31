package com.vo;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="MEMBER")
@SequenceGenerator(
        name="MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1,allocationSize = 1
)
public class Member{

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // JPA에게 1:N N:M 인지 알려줘야함.
    // Member입장에선 Many Team은 1
    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}