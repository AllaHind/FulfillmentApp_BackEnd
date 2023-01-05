package com.fullfilmentApp.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

}
