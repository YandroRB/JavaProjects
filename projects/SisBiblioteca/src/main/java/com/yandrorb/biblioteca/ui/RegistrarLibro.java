package com.yandrorb.biblioteca.ui;

import com.yandrorb.biblioteca.io.Consola;
import com.yandrorb.biblioteca.modelo.Libro;

import com.yandrorb.biblioteca.modelo.Procesable;
import com.yandrorb.biblioteca.modelo.Repositorio;

import java.time.LocalDate;

public class RegistrarLibro implements Procesable<Libro> {
    Consola consola=new Consola();
    @Override
    public void proceso(Repositorio<Libro> repositorio) {
        String titulo;
        String autor;
        String editorial;
        String isbn;
        LocalDate fechaPublicacion;
        consola.mostrarMensaje("""
                ==========================
                Registrar Libro
                ==========================
                Ingrese el nombre del libro:
                """);
        titulo=consola.leerTexto(50);
        consola.mostrarMensaje("Ingrese el nombre del autor: ");
        autor=consola.leerTexto(50);
        consola.mostrarMensaje("Ingrese el nombre del editorial: ");
        editorial=consola.leerTexto(50);
        consola.mostrarMensaje("Ingrese el ISBN: ");
        isbn=consola.leerTexto(50);
        consola.mostrarMensaje("Ingrese la fecha de publicacion (AAAA-MM-DD): ");
        fechaPublicacion=consola.leerFecha();
        repositorio.agregar(new Libro(titulo,autor,editorial,fechaPublicacion,isbn));
    }
}
