package com.mohamed.springboot.controller;

import com.mohamed.springboot.model.Produit;
import com.mohamed.springboot.model.SalesId;
import com.mohamed.springboot.model.Sales;
import com.mohamed.springboot.repository.ProduitRepository;
import com.mohamed.springboot.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/produit")
public class ProduitController {
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private SalesRepository salesRepository;
    @GetMapping
    public List<Produit> getProduits(){
        return produitRepository.findAll();
    }
    @GetMapping("{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable long id){
        Produit produit=produitRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit not found"));
        return ResponseEntity.ok(produit);
    }
    @PostMapping
    public Produit addProduit(@RequestBody Produit produit){
        //produit.setSales(0);
        return produitRepository.save(produit);
    }
    @PutMapping("{id}")
    public ResponseEntity<Produit> editProduit(@PathVariable long id,@RequestBody Produit produit){
        Produit updatedProduit=produitRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit not found"));
        updatedProduit.setPrix(produit.getPrix());
        updatedProduit.setLibelle(produit.getLibelle());
        updatedProduit.setQteStock(produit.getQteStock());
        updatedProduit.setCategorie(updatedProduit.getCategorie());
        produitRepository.save(updatedProduit);
        return ResponseEntity.ok(updatedProduit);
    }
    @PutMapping("addToStock/{id}/{qte}")
    public ResponseEntity<Produit> addToStock(@PathVariable long id,@PathVariable int qte){
        Produit updatedProduit=produitRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit not found"));
        updatedProduit.setQteStock(updatedProduit.getQteStock()+qte);
        produitRepository.save(updatedProduit);
        return ResponseEntity.ok(updatedProduit);
    }
    @PutMapping("commande/{id}/{qte}")
    public ResponseEntity<Sales> order(@PathVariable long id,@PathVariable int qte){
        Produit updatedProduit=produitRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit not found"));
        if(qte>updatedProduit.getQteStock())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "quantity to order bigger than availble stock");
        updatedProduit.setQteStock(updatedProduit.getQteStock()-qte);
        SalesId salesId=new SalesId();
        salesId.setProduitId(id);
        salesId.setYear(2023);
        Sales updatedSales=salesRepository.findById(salesId).orElse(null);
        if(updatedSales!=null)
            updatedSales.setQteSold(updatedSales.getQteSold()+qte);
        else
        {
            updatedSales=new Sales();
            updatedSales.setId(salesId);
            updatedSales.setProduit(updatedProduit);
            updatedSales.setQteSold(qte);
        }
        salesRepository.save(updatedSales);
        produitRepository.save(updatedProduit);
        return ResponseEntity.ok(updatedSales);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteProduit(@PathVariable long id){
        Produit produit=produitRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit not found"));
        produitRepository.delete(produit);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/ruptureStock")
    public ResponseEntity<List<Produit>> getRuptureStock(){
        List<Produit> produits=produitRepository.getRuptureStock();
        return ResponseEntity.ok(produits);
    }
}
