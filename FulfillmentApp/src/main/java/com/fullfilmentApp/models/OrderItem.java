package com.fullfilmentApp.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "product_id")
    @OneToOne
    private Product product;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "order_id")

    private Order orderr;
}

