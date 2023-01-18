package com.fullfilmentApp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fullfilmentApp.Enum.ProductStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
    @Enumerated(value = EnumType.STRING)
    private ProductStatus status;

    @OneToMany(mappedBy = "product")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Location> locations;


}
