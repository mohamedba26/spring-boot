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
    @EmbeddedId
    private SalesId id;
    @ManyToOne
    @MapsId("produitId")
    @JoinColumn(name = "id_produit", nullable = false)
    private Produit produit;
    @Column(name = "qteSold")
    private int qteSold;
}
