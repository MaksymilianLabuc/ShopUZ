/**
 * @file ProductListingService.java
 * @brief Klasa ProductListingService do zarządzania operacjami na ofertach produktów.
 *
 * Ta klasa zawiera metody do zarządzania operacjami na ofertach produktów, w tym pobieranie wszystkich ofert, zapisywanie oferty oraz wyszukiwanie oferty na podstawie identyfikatora.
 */

package com.janbat.shopuz.service;

import com.janbat.shopuz.model.ProductListing;
import com.janbat.shopuz.repository.ProductListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @brief Klasa ProductListingService do zarządzania operacjami na ofertach produktów.
 */
@Service
public class ProductListingService {
    @Autowired
    private ProductListingRepository productListingRepository;

    /**
     * @brief Pobiera wszystkie oferty produktów.
     * @return Lista wszystkich ofert produktów.
     */
    public List<ProductListing> getAllProductListings(){
        return productListingRepository.findAll();
    }

    /**
     * @brief Zapisuje ofertę produktu.
     * @param product Oferta produktu do zapisania.
     */
    public void saveProduct(ProductListing product) {
        productListingRepository.save(product);
    }

    /**
     * @brief Znajduje ofertę produktu na podstawie identyfikatora.
     * @param id Identyfikator oferty produktu.
     * @return Opcjonalna oferta produktu.
     */
    public Optional<ProductListing> findById(Long id){
        return productListingRepository.findById(id);
    }
}
