/**
 * @file ShopUzApplication.java
 * @brief Główna klasa aplikacji ShopUz.
 *
 * Ta klasa zawiera metodę main, która uruchamia aplikację ShopUz.
 */

package com.janbat.shopuz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @brief Główna klasa aplikacji ShopUz.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.janbat.shopuz.repository")
public class ShopUzApplication {

    /**
     * @brief Główna metoda uruchamiająca aplikację ShopUz.
     * @param args Argumenty wiersza poleceń.
     */
    public static void main(String[] args) {
        SpringApplication.run(ShopUzApplication.class, args);
    }

}
