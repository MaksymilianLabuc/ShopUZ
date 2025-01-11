/**
 * @file OpinionService.java
 * @brief Interfejs OpinionService do zarządzania operacjami na opiniach.
 *
 * Ten interfejs definiuje metody do zarządzania operacjami na opiniach, w tym zapisywanie opinii, pobieranie opinii na podstawie identyfikatora produktu, obliczanie średniej oceny oraz liczenie opinii na podstawie identyfikatora produktu.
 */

package com.janbat.shopuz.service;

import com.janbat.shopuz.model.Opinion;

import java.util.List;

/**
 * @brief Interfejs OpinionService do zarządzania operacjami na opiniach.
 */
public interface OpinionService {
    /**
     * @brief Zapisuje opinię.
     * @param opinion Opinia do zapisania.
     */
    void saveOpinion(Opinion opinion);

    /**
     * @brief Pobiera opinie na podstawie identyfikatora produktu.
     * @param productId Identyfikator produktu.
     * @return Lista opinii o produkcie.
     */
    List<Opinion> getOpinionsByProductId(Long productId);

    /**
     * @brief Oblicza średnią ocenę na podstawie identyfikatora produktu.
     * @param productId Identyfikator produktu.
     * @return Średnia ocena produktu.
     */
    double calculateAverageRating(Long productId);

    /**
     * @brief Liczy opinie na podstawie identyfikatora produktu.
     * @param productId Identyfikator produktu.
     * @return Liczba opinii o produkcie.
     */
    int countOpinionsByProductId(Long productId);
}
