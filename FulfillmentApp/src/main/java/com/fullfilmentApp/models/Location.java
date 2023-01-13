package com.fullfilmentApp.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private String ails;
    private String rack;
    private String shelf;
    private String bin;
    private int pickNbr;
    private boolean isTaken;
    @ManyToOne
    private  Product product;

}
