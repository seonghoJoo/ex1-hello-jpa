package com.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="ITEM")
@SequenceGenerator(
        name="ITEM_SEQ_GENERATOR",
        sequenceName = "ITEM_SEQ",
        initialValue = 1,allocationSize = 1
)
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;
    private String name;
    private Long price;
    @Column(name = "STOCK_QUANTITY")
    private Long stockQuantity;

}
