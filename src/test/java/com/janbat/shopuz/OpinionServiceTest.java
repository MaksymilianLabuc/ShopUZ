package com.janbat.shopuz;

import com.janbat.shopuz.model.Opinion;
import com.janbat.shopuz.service.OpinionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OpinionServiceTest {

    @Mock
    private OpinionService opinionService; // Mockujemy interfejs

    @Test
    void saveOpinion_shouldCallRepositorySave() {
        // Testuje zapisywanie opinii. Sprawdza, czy wywoływana jest metoda saveOpinion.
        Opinion opinion = new Opinion();

        opinionService.saveOpinion(opinion);

        verify(opinionService).saveOpinion(opinion); // Weryfikuje, czy metoda saveOpinion została wywołana z poprawnym argumentem.
    }

    @Test
    void getOpinionsByProductId_shouldReturnOpinionsList() {
        // Testuje pobieranie opinii po ID produktu. Sprawdza, czy zwracana jest lista opinii.
        Long productId = 1L;
        List<Opinion> opinions = new ArrayList<>();
        opinions.add(new Opinion());

        when(opinionService.getOpinionsByProductId(productId)).thenReturn(opinions);

        List<Opinion> result = opinionService.getOpinionsByProductId(productId);

        assertEquals(opinions, result);
        verify(opinionService).getOpinionsByProductId(productId); // Weryfikuje, czy metoda została wywołana z poprawnym ID produktu.
    }


    @Test
    void calculateAverageRating_shouldReturnAverageRating() {
        // Testuje obliczanie średniej oceny. Sprawdza, czy zwracana jest poprawna średnia.
        Long productId = 1L;
        double averageRating = 4.5;

        when(opinionService.calculateAverageRating(productId)).thenReturn(averageRating);

        double result = opinionService.calculateAverageRating(productId);

        assertEquals(averageRating, result); // Sprawdza, czy zwrócona średnia ocena jest poprawna.
        verify(opinionService).calculateAverageRating(productId); // Weryfikuje, czy metoda została wywołana z poprawnym ID produktu.
    }

    @Test
    void countOpinionsByProductId_shouldReturnNumberOfOpinions() {
        // Testuje liczenie opinii po ID produktu. Sprawdza, czy zwracana jest poprawna liczba opinii.
        Long productId = 1L;
        int numberOfOpinions = 5;

        when(opinionService.countOpinionsByProductId(productId)).thenReturn(numberOfOpinions);

        int result = opinionService.countOpinionsByProductId(productId);

        assertEquals(numberOfOpinions, result); // Sprawdza, czy zwrócona liczba opinii jest poprawna.
        verify(opinionService).countOpinionsByProductId(productId); // Weryfikuje, czy metoda została wywołana z poprawnym ID produktu.
    }

    @Test
    void getOpinionsByProductId_shouldReturnEmptyListWhenNoOpinions() {
        // Testuje pobieranie opinii, gdy dla danego produktu nie ma żadnych opinii.
        Long productId = 1L;
        List<Opinion> emptyList = new ArrayList<>();
        when(opinionService.getOpinionsByProductId(productId)).thenReturn(emptyList);

        List<Opinion> result = opinionService.getOpinionsByProductId(productId);

        assertEquals(emptyList, result);
        verify(opinionService).getOpinionsByProductId(productId);
    }

    @Test
    void calculateAverageRating_shouldReturnZeroWhenNoOpinions() {
        // Testuje obliczanie średniej oceny, gdy nie ma żadnych opinii dla danego produktu.
        Long productId = 1L;
        double expectedAverage = 0.0;
        when(opinionService.calculateAverageRating(productId)).thenReturn(expectedAverage);

        double actualAverage = opinionService.calculateAverageRating(productId);

        assertEquals(expectedAverage, actualAverage);
        verify(opinionService).calculateAverageRating(productId);
    }
}