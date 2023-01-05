package com.fullfilmentApp.models;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fullfilmentApp.Enum.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name="orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private Date orderDate;
    @OneToMany(mappedBy = "orderr", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<OrderItem> items;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private String shippingAddress;
    private double totalPrice;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
    private LocalDateTime shippedAt;
    private LocalDateTime deliveredAt;


}
