package com.vo;


import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
public class Member{

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String name;

    // JPA에게 1:N N:M 인지 알려줘야함.
    // Member입장에선 Many Team은 1
    // Member가 주인 (maaped by 속성 사용 안했기 때문)
    // Member는 TeamId라는 외래키를 갖고 있다 주로 주인이 됨
    // 지연로딩 단순히 member만 조회하면 되는데 굳이 Team을 조회할 필요가 없을때
    // Proxy객체로 멤버를 조회함.

    // 그러나 즉시로딩은 (fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TEAM_ID")
    private Team team;

    // 양방향 연관관계 주었음.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="MEMBER_PRODUCT")
    private List<Product> products = new ArrayList<>();


    // getter setter 관례가 아니라
    // change라 하면 뭔가 중요해보임
    public void changeTeam(Team team){
        this.team = team;
        // 연관간계 편의 메서드
        // 양방향 맵핑해준다.
        team.getMembers().add(this);
    }
}