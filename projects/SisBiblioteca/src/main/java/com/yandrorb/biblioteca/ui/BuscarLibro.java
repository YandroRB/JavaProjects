package com.yandrorb.biblioteca.ui;

import com.yandrorb.biblioteca.excepciones.LibroNoDisponibleException;
import com.yandrorb.biblioteca.io.Consola;
import com.yandrorb.biblioteca.modelo.Libro;
import com.yandrorb.biblioteca.modelo.Mostrable;
import com.yandrorb.biblioteca.modelo.Procesable;
import com.yandrorb.biblioteca.modelo.Repositorio;

public class BuscarLibro implements Mostrable<Libro>,Procesable<Libro> {
    Consola consola = new Consola();
    @Override
    public void proceso(Repositorio<Libro> repositorio) {
        consola.mostrarMensaje("Ingrese el id del libro para mostrar detalles (001 -> 1) : ");
        String identificador=consola.leerTexto();
        Libro libro=repositorio.buscar(identificador);
        DetalleLibro detalleLibro= new DetalleLibro();
        try{
            consola.mostrarMensaje(detalleLibro.mostrar(libro));
        }catch (LibroNoDisponibleException e){
            consola.mostrarMensaje(e.getMessage());
        }
    }

    @Override
    public String mostrar() {
        return """
                ==========================
                BUSCAR LIBRO
                ==========================
               """;
    }
}
