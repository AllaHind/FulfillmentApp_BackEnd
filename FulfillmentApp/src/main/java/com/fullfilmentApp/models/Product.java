package com.fullfilmentApp.models;

import com.fullfilmentApp.Enum.ProductStatus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   private String name;
    private String sku;
 @ManyToOne
    private Category category;
    private String description;
    private double sellingPrice;
    private double costPrice;
    private  int stockQuantity;
    private int supplyLevel;
    private double volume;
    private double weight;
    private double length;
    private double height;
    private double width;
    @Enumerated(value = EnumType.STRING)
    private ProductStatus status;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private Barcode barcode;



}
