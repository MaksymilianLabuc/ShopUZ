package com.janbat.shopuz.service;

import com.janbat.shopuz.model.Opinion;

import java.util.List;

public interface OpinionService {
    void saveOpinion(Opinion opinion);
    List<Opinion> getOpinionsByProductId(Long productId);
    double calculateAverageRating(Long productId);
    int countOpinionsByProductId(Long productId);
}
