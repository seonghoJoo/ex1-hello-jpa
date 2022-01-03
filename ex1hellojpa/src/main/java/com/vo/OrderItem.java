package com.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="ORDER_ITEM")
@SequenceGenerator(
        name="ORDER_ITEM_SEQ_GENERATOR",
        sequenceName = "ORDER_ITEM_SEQ",
        initialValue = 1,allocationSize = 1
)
@Getter
@Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name="ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ORDER_ID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ITEM_ID")
    private Item item;

    private Long orderPrice;
    private Long count;

}
