package com.mohamed.springboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="sales")
public class Sales {
    @Id
    private long idProduit;

    @MapsId
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "produit_id")
    private Produit produit;

    @Column(name = "qteSold")
    private int qteSold;
}
