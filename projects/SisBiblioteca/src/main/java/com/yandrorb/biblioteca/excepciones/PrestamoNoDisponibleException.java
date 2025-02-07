package com.yandrorb.biblioteca.excepciones;

public class PrestamoNoDisponibleException extends RuntimeException {
    public PrestamoNoDisponibleException(String message) {
        super(message);
    }
}
