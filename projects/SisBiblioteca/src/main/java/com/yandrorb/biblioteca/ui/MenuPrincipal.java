package com.yandrorb.biblioteca.ui;

import com.yandrorb.biblioteca.modelo.Mostrable;

public class MenuPrincipal implements Mostrable {

    @Override
    public String mostrar() {
        return """
                ==========================
                SISTEMA DE BIBLIOTECA
                ==========================
                1. Gestion de Libros
                2. Gestion de Usuarios
                3. Prestamos y Devoluciones
                4. Salir
                ==========================
                Seleccione una opcion:
                """;
    }
}
