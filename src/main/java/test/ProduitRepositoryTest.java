//package com.mohamed.springboot.test;

import com.mohamed.springboot.controller.ProduitController;
import com.mohamed.springboot.model.Produit;
import com.mohamed.springboot.repository.ProduitRepository;
import com.mohamed.springboot.repository.SalesRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ProduitControllerTest {

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private SalesRepository salesRepository;

    @InjectMocks
    private ProduitController produitController;

    @Test
    void getProduits_shouldReturnListOfProduits() {
        // Arrange
        Produit produit1 = new Produit(1L, "Produit1", 10.0, 100);
        Produit produit2 = new Produit(2L, "Produit2", 20.0, 50);
        List<Produit> expectedProduits = Arrays.asList(produit1, produit2);

        Mockito.when(produitRepository.findAll()).thenReturn(expectedProduits);

        // Act
        List<Produit> actualProduits = produitController.getProduits();

        // Assert
        assertEquals(expectedProduits, actualProduits);
    }

    @Test
    void getProduitById_shouldReturnProduitWhenExists() {
        // Arrange
        long productId = 1L;
        Produit expectedProduit = new Produit(productId, "Produit1", 10.0, 100);

        Mockito.when(produitRepository.findById(productId)).thenReturn(Optional.of(expectedProduit));

        // Act
        ResponseEntity<Produit> responseEntity = produitController.getProduitById(productId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedProduit, responseEntity.getBody());
    }

    @Test
    void getProduitById_shouldThrowExceptionWhenProduitNotFound() {
        // Arrange
        long productId = 1L;

        Mockito.when(produitRepository.findById(productId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResponseStatusException.class, () -> produitController.getProduitById(productId));
    }

    // Add similar tests for other controller methods (addProduit, editProduit, deleteProduit)...
}
