/**
 * @file ResourceNotFoundException.java
 * @brief Wyjątek zgłaszany, gdy zasób nie zostanie znaleziony.
 *
 * Ta klasa definiuje wyjątek, który jest zgłaszany, gdy żądany zasób nie zostanie znaleziony.
 */

package com.janbat.shopuz.controller;

/**
 * @brief Wyjątek zgłaszany, gdy zasób nie zostanie znaleziony.
 *
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String productNotFound) {
        super(productNotFound);
    }
}
