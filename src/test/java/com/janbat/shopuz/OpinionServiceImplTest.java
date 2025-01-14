package com.janbat.shopuz;

import com.janbat.shopuz.model.Opinion;
import com.janbat.shopuz.repository.OpinionRepository;
import com.janbat.shopuz.service.OpinionServiceImpl;
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
public class OpinionServiceImplTest {

    @Mock
    private OpinionRepository opinionRepository;

    @InjectMocks
    private OpinionServiceImpl opinionService;

    @Test
    void saveOpinion_shouldCallRepositorySave() {
        // Testuje zapisywanie opinii. Sprawdza, czy wywoływana jest metoda save repozytorium.
        Opinion opinion = new Opinion();

        opinionService.saveOpinion(opinion);

        verify(opinionRepository).save(opinion); // Weryfikuje, czy metoda save została wywołana z poprawną opinią.
    }

    @Test
    void getOpinionsByProductId_shouldReturnOpinionsList() {
        // Testuje pobieranie opinii po ID produktu.
        Long productId = 1L;
        List<Opinion> opinions = new ArrayList<>();
        Opinion opinion1 = new Opinion();
        Opinion opinion2 = new Opinion();
        opinions.add(opinion1);
        opinions.add(opinion2);

        when(opinionRepository.findByProductId(productId)).thenReturn(opinions);

        List<Opinion> result = opinionService.getOpinionsByProductId(productId);

        assertEquals(2, result.size(), "Lista powinna zawierać 2 opinie");
        assertEquals(opinions, result, "Zwrócona lista powinna być identyczna z mockowaną");
        verify(opinionRepository).findByProductId(productId); // Weryfikuje, czy metoda findByProductId została wywołana z poprawnym ID.
    }

    @Test
    void getOpinionsByProductId_shouldReturnEmptyListWhenNoOpinions() {
        // Testuje pobieranie opinii, gdy nie ma żadnych opinii dla danego produktu.
        Long productId = 1L;
        when(opinionRepository.findByProductId(productId)).thenReturn(new ArrayList<>());

        List<Opinion> result = opinionService.getOpinionsByProductId(productId);

        assertEquals(0, result.size(), "Lista powinna być pusta");
        verify(opinionRepository).findByProductId(productId); // Weryfikuje, czy metoda findByProductId została wywołana z poprawnym ID.
    }

    @Test
    void calculateAverageRating_shouldCalculateCorrectAverage() {
        // Testuje obliczanie średniej oceny.
        Long productId = 1L;
        List<Opinion> opinions = new ArrayList<>();
        Opinion opinion1 = new Opinion();
        opinion1.setOcena(4);
        Opinion opinion2 = new Opinion();
        opinion2.setOcena(5);
        Opinion opinion3 = new Opinion();
        opinion3.setOcena(3);
        opinions.add(opinion1);
        opinions.add(opinion2);
        opinions.add(opinion3);

        when(opinionRepository.findByProductId(productId)).thenReturn(opinions);

        double averageRating = opinionService.calculateAverageRating(productId);

        assertEquals(4.0, averageRating, 0.001, "Średnia powinna wynosić 4.0"); // Delta dla porównań double
        verify(opinionRepository).findByProductId(productId); // Weryfikuje, czy metoda findByProductId została wywołana z poprawnym ID.
    }

    @Test
    void calculateAverageRating_shouldReturnZeroWhenNoOpinions() {
        // Testuje obliczanie średniej oceny, gdy nie ma żadnych opinii.
        Long productId = 1L;
        when(opinionRepository.findByProductId(productId)).thenReturn(new ArrayList<>());

        double averageRating = opinionService.calculateAverageRating(productId);

        assertEquals(0.0, averageRating, 0.001, "Średnia powinna wynosić 0.0"); // Delta dla porównań double
        verify(opinionRepository).findByProductId(productId); // Weryfikuje, czy metoda findByProductId została wywołana z poprawnym ID.
    }

    @Test
    void countOpinionsByProductId_shouldReturnCorrectCount() {
        // Testuje liczenie opinii po ID produktu.
        Long productId = 1L;
        int expectedCount = 3;

        when(opinionRepository.countByProductId(productId)).thenReturn(expectedCount);

        int actualCount = opinionService.countOpinionsByProductId(productId);

        assertEquals(expectedCount, actualCount, "Liczba opinii powinna wynosić 3");
        verify(opinionRepository).countByProductId(productId); // Weryfikuje, czy metoda countByProductId została wywołana z poprawnym ID.
    }

    @Test
    void countOpinionsByProductId_shouldReturnZeroWhenNoOpinions() {
        // Testuje liczenie opinii, gdy nie ma żadnych opinii dla danego produktu.
        Long productId = 1L;
        int expectedCount = 0;

        when(opinionRepository.countByProductId(productId)).thenReturn(expectedCount);

        int actualCount = opinionService.countOpinionsByProductId(productId);

        assertEquals(expectedCount, actualCount, "Liczba opinii powinna wynosić 0");
        verify(opinionRepository).countByProductId(productId); // Weryfikuje, czy metoda countByProductId została wywołana z poprawnym ID.
    }
}