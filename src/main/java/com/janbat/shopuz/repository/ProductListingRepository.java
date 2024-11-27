package com.janbat.shopuz.repository;

import com.janbat.shopuz.model.ProductListing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductListingRepository extends JpaRepository<ProductListing, Long> {
}
