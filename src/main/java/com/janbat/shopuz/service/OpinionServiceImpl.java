/**
 * @file OpinionServiceImpl.java
 * @brief Implementacja interfejsu OpinionService do zarządzania operacjami na opiniach.
 *
 * Ta klasa implementuje metody do zarządzania operacjami na opiniach, w tym zapisywanie opinii, pobieranie opinii na podstawie identyfikatora produktu, obliczanie średniej oceny oraz liczenie opinii na podstawie identyfikatora produktu.
 */

package com.janbat.shopuz.service;

import com.janbat.shopuz.model.Opinion;
import com.janbat.shopuz.repository.OpinionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @brief Implementacja interfejsu OpinionService do zarządzania operacjami na opiniach.
 */
@Service
public class OpinionServiceImpl implements OpinionService {

    @Autowired
    private OpinionRepository opinionRepository;

    /**
     * @brief Zapisuje opinię.
     * @param opinion Opinia do zapisania.
     */
    @Override
    public void saveOpinion(Opinion opinion) {
        opinionRepository.save(opinion);
    }

    /**
     * @brief Pobiera opinie na podstawie identyfikatora produktu.
     * @param productId Identyfikator produktu.
     * @return Lista opinii o produkcie.
     */
    @Override
    public List<Opinion> getOpinionsByProductId(Long productId) {
        return opinionRepository.findByProductId(productId);
    }

    /**
     * @brief Oblicza średnią ocenę na podstawie identyfikatora produktu.
     * @param productId Identyfikator produktu.
     * @return Średnia ocena produktu.
     */
    @Override
    public double calculateAverageRating(Long productId) {
        List<Opinion> opinions = opinionRepository.findByProductId(productId);
        return opinions.stream().mapToInt(Opinion::getOcena).average().orElse(0.0);
    }

    /**
     * @brief Liczy opinie na podstawie identyfikatora produktu.
     * @param productId Identyfikator produktu.
     * @return Liczba opinii o produkcie.
     */
    @Override
    public int countOpinionsByProductId(Long productId) {
        return opinionRepository.countByProductId(productId);
    }
}
