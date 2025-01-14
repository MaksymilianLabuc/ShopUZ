package com.janbat.shopuz;

import com.janbat.shopuz.model.Cart;
import com.janbat.shopuz.model.CartItem;
import com.janbat.shopuz.model.ProductListing;
import com.janbat.shopuz.repository.CartRepository;
import com.janbat.shopuz.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest // Kluczowa adnotacja dla testów Spring Boot
public class CartServiceTest {

    @MockBean // Używamy @MockBean zamiast @Mock
    private CartRepository cartRepository;

    @Autowired // Wstrzykujemy testowany serwis
    private CartService cartService;

    private String testUsername;
    private ProductListing testProduct;
    private Cart testCart;

    @BeforeEach
    void setUp() {
        testUsername = "testuser";
        testProduct = new ProductListing();
        testProduct.setId(1L);
        testProduct.setName("Test Product");
        testCart = new Cart();
        testCart.setUsername(testUsername);
        testCart.setItems(new ArrayList<>());
    }

    @Test
    void getCartByUsername_existingCart() {
        // Testuje pobieranie koszyka po nazwie użytkownika, gdy koszyk istnieje.
        when(cartRepository.findByUsername(testUsername)).thenReturn(testCart);

        Cart cart = cartService.getCartByUsername(testUsername);

        // Sprawdza, czy zwrócony koszyk jest identyczny z oczekiwanym.
        assertEquals(testCart, cart);
        // Weryfikuje, czy metoda findByUsername została wywołana.
        verify(cartRepository).findByUsername(testUsername);
    }

    // Pozostałe testy analogicznie, z użyciem @MockBean i @Autowired

    @Test
    void addItemToCart_newCart() {
        // Testuje dodawanie przedmiotu do koszyka, gdy koszyk dla danego użytkownika nie istnieje.
        when(cartRepository.findByUsername(testUsername)).thenReturn(null);

        cartService.addItemToCart(testUsername, testProduct);

        // Weryfikuje, czy metoda findByUsername została wywołana.
        verify(cartRepository).findByUsername(testUsername);
        // Weryfikuje, czy metoda save została wywołana z dowolnym obiektem Cart.
        verify(cartRepository).save(any(Cart.class));
    }

    @Test
    void addItemToCart_existingCart() {
        // Testuje dodawanie przedmiotu do koszyka, gdy koszyk dla danego użytkownika już istnieje.
        when(cartRepository.findByUsername(testUsername)).thenReturn(testCart);

        cartService.addItemToCart(testUsername, testProduct);

        // Weryfikuje, czy metoda findByUsername została wywołana.
        verify(cartRepository).findByUsername(testUsername);
        // Weryfikuje, czy metoda save została wywołana z konkretnym koszykiem testowym.
        verify(cartRepository).save(testCart);
        // Sprawdza, czy do koszyka został dodany jeden element.
        assertEquals(1, testCart.getItems().size());
        // Sprawdza, czy dodany element to oczekiwany produkt.
        assertEquals(testProduct, testCart.getItems().get(0).getProduct());
    }

    @Test
    void removeItemFromCart_itemExists() {
        // Testuje usuwanie istniejącego przedmiotu z koszyka.
        when(cartRepository.findByUsername(testUsername)).thenReturn(testCart);
        CartItem item = new CartItem();
        item.setProduct(testProduct);
        testCart.getItems().add(item);

        cartService.removeItemFromCart(testUsername, testProduct.getId());

        // Weryfikuje, czy metoda findByUsername została wywołana.
        verify(cartRepository).findByUsername(testUsername);
        // Weryfikuje, czy metoda save została wywołana.
        verify(cartRepository).save(testCart);
        // Sprawdza, czy lista przedmiotów w koszyku jest pusta po usunięciu elementu
        assertTrue(testCart.getItems().isEmpty());
    }

    @Test
    void removeItemFromCart_itemDoesNotExist() {
        // Testuje usuwanie przedmiotu z koszyka, gdy dany przedmiot nie istnieje w koszyku
        when(cartRepository.findByUsername(testUsername)).thenReturn(testCart);

        cartService.removeItemFromCart(testUsername, 999L); // Nieistniejący ID

        // Weryfikuje, czy metoda findByUsername została wywołana.
        verify(cartRepository).findByUsername(testUsername);
        // Weryfikuje, czy metoda save została wywołana
        verify(cartRepository).save(testCart);
        assertTrue(testCart.getItems().isEmpty()); // Powinno być puste, bo na początku było puste
    }

    @Test
    void removeItemFromCart_cartDoesNotExist() {
        // Testuje usuwanie przedmiotu z koszyka, gdy koszyk dla danego użytkownika nie istnieje.
        when(cartRepository.findByUsername(testUsername)).thenReturn(null);

        cartService.removeItemFromCart(testUsername, testProduct.getId());

        // Weryfikuje, czy metoda findByUsername została wywołana
        verify(cartRepository).findByUsername(testUsername);
        // Weryfikuje, czy metoda save *nigdy* nie została wywołana
        verify(cartRepository, never()).save(any()); // Sprawdzamy, że save nigdy nie został wywołany
    }

//    @Test
//    void getAllProductsInCarts_multipleCarts() {
//        Cart cart2 = new Cart();
//        cart2.setItems(new ArrayList<>());
//        ProductListing product2 = new ProductListing();
//        product2.setId(2L);
//        cart2.getItems().add(new CartItem(product2, 1, cart2));
//        List<Cart> carts = List.of(testCart, cart2);
//        when(cartRepository.findAll()).thenReturn(carts);
//        testCart.getItems().add(new CartItem(testProduct, 1, testCart));
//
//        List<ProductListing> products = cartService.getAllProductsInCarts();
//
//        assertEquals(2, products.size());
//        assertTrue(products.contains(testProduct));
//        assertTrue(products.contains(product2));
//    }

    @Test
    void getAllProductsInCarts_noCarts() {
        // Testuje pobieranie wszystkich produktów znajdujących się w koszykach, gdy nie ma żadnych koszyków.
        when(cartRepository.findAll()).thenReturn(new ArrayList<>());

        List<ProductListing> products = cartService.getAllProductsInCarts();

        // Sprawdza, czy lista zwróconych produktów jest pusta.
        assertTrue(products.isEmpty());
    }
}