package com.yandrorb.biblioteca.ui;

import com.yandrorb.biblioteca.excepciones.LibroNoDisponibleException;
import com.yandrorb.biblioteca.excepciones.PrestamoNoDisponibleException;
import com.yandrorb.biblioteca.excepciones.UsuarioNoDisponibleException;
import com.yandrorb.biblioteca.modelo.Mostrable;
import com.yandrorb.biblioteca.modelo.Prestamo;

import java.time.format.DateTimeFormatter;

public class DetallePrestamo implements Mostrable<Prestamo> {
    @Override
    public String mostrar() {
        return "";
    }

    @Override
    public String mostrar(Prestamo prestamo) throws PrestamoNoDisponibleException {
        if(prestamo == null) throw new PrestamoNoDisponibleException("No se ha podido mostrar detalles del prestamo");
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaPrestamo=prestamo.getFechaPrestamo().format(formato);
        String fechaDevolucion=prestamo.getFechaDevolucion().format(formato);
        String fechaDevuelto=prestamo.getFechaDevuelto()==null?"":prestamo.getFechaDevuelto().format(formato);
        String fechaPublicacionLibro=prestamo.getLibroPrestado().getFechaPublicacion()==null?"":
                prestamo.getLibroPrestado().getFechaPublicacion().format(formato);
        return String.format("""
                ID Prestamo:%s
                Fecha de prestamo:%s
                Fecha de devolucion:%s
                Fecha devuelto:%s
                
                
                =============
                Detalle libro
                =============
                ID Libro:%s
                Titulo Libro:%s
                Autor:%s
                Fecha publicacion:%s
                
                
                ===============
                Detalle Usuario
                ===============
                ID Usuario:%s
                Nombres:%s
                Apellidos:%s
                Tipo de usuario:%s
                """,prestamo.getIdentificador(),fechaPrestamo,fechaDevolucion,fechaDevuelto,
                prestamo.getLibroPrestado().getIdentificador(),prestamo.getLibroPrestado().getTitulo(),
                prestamo.getLibroPrestado().getAutor(),fechaPublicacionLibro,prestamo.getPrestador().getIdentificador(),
                prestamo.getPrestador().getNombre(),prestamo.getPrestador().getApellido(),prestamo.getPrestador().getTipoUsuario());
    }
}
