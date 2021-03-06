package com.vo;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name="ORDERS")
@SequenceGenerator(
        name="ORDER_SEQ_GENERATOR",
        sequenceName = "ORDER_SEQ",
        initialValue = 1,allocationSize = 1
)
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    // 주문의 입장에서 Member는 한개에 Order는 여러개이므로
    // 주문이 주인
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "MEMBER_ID")
    private Member member;

    // order 생성시 delivery도 같이 영향을 주고 싶다.
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="DELIVERY_ID")
    private Delivery delivery;

    // 양방향 연관관계
    @OneToMany(mappedBy = "item" , cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(name=  "ORDER_DATE")
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

}
