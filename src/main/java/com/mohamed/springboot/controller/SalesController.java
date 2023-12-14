package com.mohamed.springboot.controller;

import com.mohamed.springboot.model.Response;
import com.mohamed.springboot.model.Sales;
import com.mohamed.springboot.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/sales")
public class SalesController {
    @Autowired
    private SalesRepository salesRepository;
    @GetMapping("/qteSold/{categorie}/{year}")
    public ResponseEntity<Integer> getQteSoldByCategorieAndYear(
            @RequestParam("categorie") String categorie,
            @RequestParam("year") int year) {
        Integer qteSoldList = salesRepository.getQteSoldByCategorieAndYear(categorie, year);
        return ResponseEntity.ok(qteSoldList);
    }
    @GetMapping("/totalSales")
    public ResponseEntity<Sales> getTotalSales() {
        Integer totalSales = salesRepository.getTotalSales();
        Sales r=new Sales();
        r.setQteSold(totalSales);
        return ResponseEntity.ok(r);
    }
    @GetMapping("/top10SoldProduits")
    public ResponseEntity<List<Sales>> getTop10SoldProduits() {
        List<Sales> top10Sales = salesRepository.getTop10Sales();
        if (top10Sales.size() > 10) {
            top10Sales = top10Sales.subList(0, 10);
        }
        return ResponseEntity.ok(top10Sales);
    }
    @GetMapping("/totalSalesPerYear")
    public ResponseEntity<Integer> getTotalSalesPerYear(@RequestParam("year") int year) {
        Integer totalSalesPerYear = salesRepository.getTotalSalesPerYear(year);
        return ResponseEntity.ok(totalSalesPerYear);
    }
    @GetMapping("/top10SoldProduitsPerYear")
    public ResponseEntity<List<Sales>> getTop10SoldProduitsPerYear(@RequestParam("year") int year) {
        List<Sales> top10Sales = salesRepository.getTop10SalesPerYear(year);
        if (top10Sales.size() > 10) {
            top10Sales = top10Sales.subList(0, 10);
        }
        return ResponseEntity.ok(top10Sales);
    }
}
