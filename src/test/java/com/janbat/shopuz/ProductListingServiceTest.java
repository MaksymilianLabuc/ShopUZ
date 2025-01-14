package com.janbat.shopuz;

import com.janbat.shopuz.model.ProductListing;
import com.janbat.shopuz.repository.ProductListingRepository;
import com.janbat.shopuz.service.ProductListingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductListingServiceTest {

    @Mock
    private ProductListingRepository productListingRepository;

    @InjectMocks
    private ProductListingService productListingService;

    @Test
    void getAllProductListings_shouldReturnAllListings() {
        // Testuje pobieranie wszystkich ofert produktów.
        List<ProductListing> productListings = new ArrayList<>();
        productListings.add(new ProductListing());
        productListings.add(new ProductListing());

        when(productListingRepository.findAll()).thenReturn(productListings);

        List<ProductListing> result = productListingService.getAllProductListings();

        assertEquals(2, result.size(), "Powinna zostać zwrócona lista z 2 elementami");
        verify(productListingRepository).findAll(); // Weryfikuje, czy metoda findAll została wywołana.
    }

    @Test
    void getAllProductListings_shouldReturnEmptyListWhenNoListings() {
        // Testuje pobieranie wszystkich ofert, gdy nie ma żadnych ofert w bazie.
        when(productListingRepository.findAll()).thenReturn(new ArrayList<>());

        List<ProductListing> result = productListingService.getAllProductListings();

        assertTrue(result.isEmpty(), "Powinna zostać zwrócona pusta lista");
        verify(productListingRepository).findAll(); // Weryfikuje, czy metoda findAll została wywołana.
    }

    @Test
    void saveProduct_shouldCallRepositorySave() {
        // Testuje zapisywanie produktu. Sprawdza, czy wywoływana jest metoda save repozytorium.
        ProductListing product = new ProductListing();

        productListingService.saveProduct(product);

        verify(productListingRepository).save(product); // Weryfikuje, czy metoda save została wywołana z poprawnym produktem.
    }

    @Test
    void findById_shouldReturnProductWhenExists() {
        // Testuje wyszukiwanie produktu po ID, gdy produkt istnieje.
        Long id = 1L;
        ProductListing product = new ProductListing();
        Optional<ProductListing> optionalProduct = Optional.of(product);

        when(productListingRepository.findById(id)).thenReturn(optionalProduct);

        Optional<ProductListing> result = productListingService.findById(id);

        assertTrue(result.isPresent(), "Powinien zostać zwrócony Optional zawierający produkt");
        assertEquals(product, result.get(), "Zwrócony produkt powinien być identyczny z oczekiwanym");
        verify(productListingRepository).findById(id); // Weryfikuje, czy metoda findById została wywołana z poprawnym ID.
    }

    @Test
    void findById_shouldReturnEmptyOptionalWhenNotExists() {
        // Testuje wyszukiwanie produktu po ID, gdy produkt nie istnieje.
        Long id = 1L;
        Optional<ProductListing> emptyOptional = Optional.empty();

        when(productListingRepository.findById(id)).thenReturn(emptyOptional);

        Optional<ProductListing> result = productListingService.findById(id);

        assertTrue(result.isEmpty(), "Powinien zostać zwrócony pusty Optional");
        verify(productListingRepository).findById(id); // Weryfikuje, czy metoda findById została wywołana z poprawnym ID.
    }
}