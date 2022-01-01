package com.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue
    @Column(name="TEAM_ID")
    private Long id;

    private String name;

    // 객체와 테이블간에 연관관계를 맺는 차이를 이해해야함.
    // 객체의 양방향 관계는 서로 다른 단방향 관계 2개다.
    // 양방향 맵핑을 위한 작업
    // 하인
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

}
