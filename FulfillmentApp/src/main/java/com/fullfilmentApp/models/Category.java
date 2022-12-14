package com.fullfilmentApp.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String label;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
