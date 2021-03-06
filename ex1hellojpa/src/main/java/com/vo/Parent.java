package com.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Parent {

    @Id @GeneratedValue
    private Long id;

    private String name;


    // 고아객체
    // cascade.remove 처럼 동작함
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child){
        childList.add(child);
        child.setParent(this);
    }
}
