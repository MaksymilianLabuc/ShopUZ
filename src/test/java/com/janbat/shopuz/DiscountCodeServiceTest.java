package com.janbat.shopuz;

import com.janbat.shopuz.model.DiscountCode;
import com.janbat.shopuz.repository.DiscountCodeRepository;
import com.janbat.shopuz.service.DiscountCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DiscountCodeServiceTest {

    @MockBean
    private DiscountCodeRepository discountCodeRepository;

    @Autowired
    private DiscountCodeService discountCodeService;

    @Test
    void save_shouldSaveDiscountCode() {
        // Testuje zapisywanie kodu rabatowego.
        DiscountCode discountCode = new DiscountCode();
        discountCode.setCodeName("TEST_CODE");

        discountCodeService.save(discountCode);

        verify(discountCodeRepository).save(discountCode); // Weryfikuje, czy metoda save została wywołana z poprawnym argumentem.
    }

    @Test
    void findAll_shouldReturnAllDiscountCodes() {
        // Testuje pobieranie wszystkich kodów rabatowych.
        List<DiscountCode> discountCodes = new ArrayList<>();
        DiscountCode code1 = new DiscountCode();
        code1.setCodeName("CODE1");
        DiscountCode code2 = new DiscountCode();
        code2.setCodeName("CODE2");
        discountCodes.add(code1);
        discountCodes.add(code2);

        when(discountCodeRepository.findAll()).thenReturn(discountCodes); // Mockuje zachowanie repozytorium.

        List<DiscountCode> result = discountCodeService.findAll();

        assertEquals(2, result.size()); // Sprawdza, czy zwrócona lista ma odpowiedni rozmiar.
        assertTrue(result.contains(code1)); // Sprawdza, czy lista zawiera oczekiwane kody.
        assertTrue(result.contains(code2));
        verify(discountCodeRepository).findAll(); // Weryfikuje, czy metoda findAll została wywołana.
    }

    @Test
    void findAll_shouldReturnEmptyListWhenNoCodes() {
        // Testuje pobieranie wszystkich kodów rabatowych, gdy nie ma żadnych kodów w bazie.
        when(discountCodeRepository.findAll()).thenReturn(new ArrayList<>());

        List<DiscountCode> result = discountCodeService.findAll();

        assertTrue(result.isEmpty()); // Sprawdza, czy zwrócona lista jest pusta.
        verify(discountCodeRepository).findAll(); // Weryfikuje, czy metoda findAll została wywołana.
    }

    @Test
    void findByCodeName_shouldReturnDiscountCodeWhenExists() {
        // Testuje wyszukiwanie kodu rabatowego po nazwie, gdy kod istnieje.
        String codeName = "EXISTING_CODE";
        DiscountCode discountCode = new DiscountCode();
        discountCode.setCodeName(codeName);

        when(discountCodeRepository.findByCodeName(codeName)).thenReturn(discountCode); // Mockuje zachowanie repozytorium.

        DiscountCode result = discountCodeService.findByCodeName(codeName);

        assertEquals(discountCode, result); // Sprawdza, czy zwrócony kod jest zgodny z oczekiwanym.
        verify(discountCodeRepository).findByCodeName(codeName); // Weryfikuje, czy metoda findByCodeName została wywołana.
    }

    @Test
    void findByCodeName_shouldReturnNullWhenNotExists() {
        // Testuje wyszukiwanie kodu rabatowego po nazwie, gdy kod nie istnieje.
        String codeName = "NON_EXISTING_CODE";

        when(discountCodeRepository.findByCodeName(codeName)).thenReturn(null); // Mockuje zachowanie repozytorium.

        DiscountCode result = discountCodeService.findByCodeName(codeName);

        assertNull(result); // Sprawdza, czy zwrócono null.
        verify(discountCodeRepository).findByCodeName(codeName); // Weryfikuje, czy metoda findByCodeName została wywołana.
    }
}