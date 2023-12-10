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
        return produitRepository.save(produit);
    }
    @PutMapping("{id}")
    public ResponseEntity<Produit> editProduit(@PathVariable long id,@RequestBody Produit produit){
        Produit updatedProduit=produitRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit not found"));
        updatedProduit.setPrix(produit.getPrix());
        updatedProduit.setLibelle(produit.getLibelle());
        updatedProduit.setQteStock(produit.getQteStock());
        produitRepository.save(updatedProduit);
        return ResponseEntity.ok(updatedProduit);
    }
    /*@PutMapping("addToStock/{id}/{qte}")
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
        Sales sales= salesRepository.findById(id).orElse(null);
        if(sales!=null)
            sales.setQteSold(sales.getQteSold()+qte);
        else
        {
            sales=new Sales();
            sales.setIdProduit(id);
            sales.setProduit(updatedProduit);
            sales.setQteSold(qte);
        }
        if(sales.getIdProduit()!=0)
            salesRepository.save(sales);
        produitRepository.save(updatedProduit);
        return ResponseEntity.ok(sales);
    }*/
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteProduit(@PathVariable long id){
        Produit produit=produitRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit not found"));
        produitRepository.delete(produit);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
