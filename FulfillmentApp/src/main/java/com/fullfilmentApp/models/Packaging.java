package com.fullfilmentApp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class Packaging {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    @OneToMany(mappedBy = "packaging")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Package> packageList;
    @OneToOne
    private Order order;


}
