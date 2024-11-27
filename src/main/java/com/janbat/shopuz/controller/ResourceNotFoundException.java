package com.janbat.shopuz.controller;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String productNotFound) {
        super(productNotFound);
    }
}
