package com.yandrorb.biblioteca.ui;

import com.yandrorb.biblioteca.excepciones.LibroNoDisponibleException;
import com.yandrorb.biblioteca.io.Consola;
import com.yandrorb.biblioteca.modelo.Libro;
import com.yandrorb.biblioteca.modelo.Mostrable;
import com.yandrorb.biblioteca.modelo.Procesable;
import com.yandrorb.biblioteca.modelo.Repositorio;

public class EliminarLibro implements Mostrable<Libro>, Procesable<Libro> {
    Consola consola = new Consola();
    @Override
    public String mostrar() {
        return """
                ==========================
                ELIMINAR LIBRO
                ==========================
               """;
    }
    @Override
    public void proceso(Repositorio<Libro> repositorio) {
        consola.mostrarMensaje("Ingrese el id del libro para eliminar (001 -> 1)");
        Libro libro=repositorio.buscar(consola.leerTexto());
        if(repositorio.eliminar(libro)){
            consola.mostrarMensaje("El libro "+libro.getTitulo()+" se eliminado");
        }
        else{
            consola.mostrarMensaje("El libro no existe");
        }
    }
}
