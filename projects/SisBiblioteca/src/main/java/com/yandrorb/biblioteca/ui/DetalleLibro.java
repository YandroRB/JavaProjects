package com.yandrorb.biblioteca.ui;

import com.yandrorb.biblioteca.excepciones.LibroNoDisponibleException;
import com.yandrorb.biblioteca.modelo.Libro;
import com.yandrorb.biblioteca.modelo.Mostrable;

import java.time.format.DateTimeFormatter;

public class DetalleLibro implements Mostrable<Libro> {
    @Override
    public String mostrar() {
        return "";
    }
    @Override
    public String mostrar(Libro libro) throws LibroNoDisponibleException {
        if(libro == null) throw new LibroNoDisponibleException("Libro no encontrado");
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada=libro.getFechaPublicacion()!=null?libro.getFechaPublicacion().format(formato):"";
        return String.format("""
                ==========================
                Detalle del libro %s
                ==========================
                Titulo: %s
                Autor: %s
                ISBN: %s
                Editorial: %s
                Fecha de publicación: %s
                Cantidad en existencia: %d
                Cantidad disponible: %d
               """,libro.getTitulo(),libro.getTitulo(),libro.getAutor(),libro.getISBN()
                ,libro.getEditorial(),fechaFormateada,libro.getNumeroCopias(),
                libro.getCopiasDisponibles());
    }
}
