package com.fullfilmentApp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fullfilmentApp.Enum.PackagingStatus;
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
    @Enumerated(value = EnumType.STRING)
    private PackagingStatus status;
    @OneToMany(mappedBy = "packaging")
    private List<Package> packageList;
    @OneToOne
    private Order order;


}
