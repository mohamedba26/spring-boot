package com.mohamed.springboot.repository;

import com.mohamed.springboot.model.Sales;
import com.mohamed.springboot.model.SalesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface SalesRepository extends JpaRepository<Sales, SalesId> {
    @Query("SELECT s.qteSold FROM Sales s WHERE s.produit.categorie = :categorie AND s.id.year = :year")
    Integer getQteSoldByCategorieAndYear(@Param("categorie") String categorie,
                                             @Param("year") int year);
    @Query("SELECT SUM(s.qteSold) FROM Sales s")
    Integer getTotalSales();
    @Query("SELECT s FROM Sales s GROUP BY s.produit ORDER BY SUM(s.qteSold) DESC")
    List<Sales> getTop10Sales();
    @Query("SELECT SUM(s.qteSold) FROM Sales s WHERE s.id.year = :year")
    Integer getTotalSalesPerYear(@Param("year") int year);
    @Query("SELECT s FROM Sales s WHERE s.id.year = :year ORDER BY s.qteSold DESC")
    List<Sales> getTop10SalesPerYear(@Param("year") int year);
}
