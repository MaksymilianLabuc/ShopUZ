package com.janbat.shopuz.repository;

import com.janbat.shopuz.model.DiscountCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Long> {
    DiscountCode findByCodeName(String codeName);
}

