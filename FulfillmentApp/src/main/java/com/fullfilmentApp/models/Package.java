package com.fullfilmentApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private double height;
    private double length;
    private double width;
    private double maxWeight;
    private String status;
    @ManyToOne
    private Packaging packaging;
    @OneToOne
  private   OrderItem orderItem;







}
