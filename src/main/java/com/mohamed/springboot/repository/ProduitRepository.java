package com.mohamed.springboot.repository;

import com.mohamed.springboot.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit,Long> {
}
