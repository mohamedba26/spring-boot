package com.mohamed.springboot.controller;

import com.mohamed.springboot.model.Produit;
import com.mohamed.springboot.model.Sales;
import com.mohamed.springboot.repository.ProduitRepository;
import com.mohamed.springboot.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@CrossOrigin(origins ="http://localhost:4200")
@RestController
    @RequestMapping("/api/sales")
public class SalesController {
    @Autowired
    private SalesRepository salesRepository;
    @Autowired
    private ProduitRepository produitRepository;
    @PostMapping
    public Sales addSales(@RequestBody Sales sales){
        salesRepository.save(sales);
        return sales;
    }

}
