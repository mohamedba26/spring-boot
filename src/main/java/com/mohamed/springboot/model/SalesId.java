package com.mohamed.springboot.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class SalesId implements Serializable{
    @Column(name = "id_produit")
    private Long produitId;
    @Column(name = "year")
    private int year;
}
