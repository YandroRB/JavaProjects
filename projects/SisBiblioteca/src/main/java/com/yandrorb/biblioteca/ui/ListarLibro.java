package com.yandrorb.biblioteca.ui;

import com.yandrorb.biblioteca.excepciones.LibroNoDisponibleException;
import com.yandrorb.biblioteca.excepciones.NoElementosException;
import com.yandrorb.biblioteca.io.Consola;
import com.yandrorb.biblioteca.modelo.Libro;
import com.yandrorb.biblioteca.modelo.Procesable;
import com.yandrorb.biblioteca.modelo.Repositorio;

public class ListarLibro implements Procesable<Libro> {
    Consola consola = new Consola();
    @Override
    public void proceso(Repositorio<Libro> repositorio) throws NoElementosException {
        try{
            String listaLibros= repositorio.listar();
            consola.mostrarMensaje("""
                        ==========================
                        Lista de libros registrados
                        ==========================
                        """);
            consola.mostrarMensaje(String.format("%-10s%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s","ID","Titulo","Autor","Editorial","Fecha de publicación","ISBN","Genero","Numero de copia","Copias disponible"));
            consola.mostrarMensaje(listaLibros);
        }catch (NoElementosException e){
            throw new NoElementosException("No hay libros registrados");
        }

    }
}
