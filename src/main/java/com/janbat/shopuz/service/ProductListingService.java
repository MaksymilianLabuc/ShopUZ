package com.janbat.shopuz.service;

import com.janbat.shopuz.model.ProductListing;
import com.janbat.shopuz.repository.ProductListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductListingService {
    @Autowired
    private ProductListingRepository productListingRepository;

    public List<ProductListing> getAllProductListings(){
        return productListingRepository.findAll();
    }
    public void saveProduct(ProductListing product) {
        productListingRepository.save(product);
    }
    public Optional<ProductListing> findById(Long id){
        return productListingRepository.findById(id);
    }
}
