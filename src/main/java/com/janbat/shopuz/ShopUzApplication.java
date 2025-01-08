package com.janbat.shopuz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.janbat.shopuz.repository")
public class ShopUzApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopUzApplication.class, args);
    }

}
