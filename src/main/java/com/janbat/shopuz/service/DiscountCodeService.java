package com.janbat.shopuz.service;

import com.janbat.shopuz.model.DiscountCode;
import com.janbat.shopuz.repository.DiscountCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountCodeService {
    @Autowired
    private DiscountCodeRepository discountCodeRepository;

    public void save(DiscountCode discountCode) {
        discountCodeRepository.save(discountCode);
    }

    public List<DiscountCode> findAll() {
        return discountCodeRepository.findAll();
    }
    public DiscountCode findByCodeName(String codeName) {
        return discountCodeRepository.findByCodeName(codeName);
    }

}
