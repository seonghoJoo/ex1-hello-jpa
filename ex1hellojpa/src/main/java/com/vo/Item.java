package com.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="ITEM")
@SequenceGenerator(
        name="ITEM_SEQ_GENERATOR",
        sequenceName = "ITEM_SEQ",
        initialValue = 1,allocationSize = 1
)
@Getter
@Setter
// 부모가 되도록 맵핑이 됨
//@Inheritance(strategy = InheritanceType.JOINED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// DTYPE이 생김
// 무얼 조인 해야할지 알게 됨
// item이 들어온게 movie, album, book인지 구분 가능하게 해줌
@DiscriminatorColumn
public abstract class Item extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;
    private String name;
    private Long price;
    @Column(name = "STOCK_QUANTITY")
    private Long stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

}
