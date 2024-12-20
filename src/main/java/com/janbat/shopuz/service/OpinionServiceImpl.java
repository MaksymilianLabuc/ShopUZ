package com.janbat.shopuz.service;

import com.janbat.shopuz.model.Opinion;
import com.janbat.shopuz.repository.OpinionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionServiceImpl implements OpinionService {

    @Autowired
    private OpinionRepository opinionRepository;

    @Override
    public void saveOpinion(Opinion opinion) {
        opinionRepository.save(opinion);
    }

    @Override
    public List<Opinion> getOpinionsByProductId(Long productId) {
        return opinionRepository.findByProductId(productId);
    }

    @Override
    public double calculateAverageRating(Long productId) {
        List<Opinion> opinions = opinionRepository.findByProductId(productId);
        return opinions.stream().mapToInt(Opinion::getOcena).average().orElse(0.0);
    }

    @Override
    public int countOpinionsByProductId(Long productId) {
        return opinionRepository.countByProductId(productId);
    }
}
