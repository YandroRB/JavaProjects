package com.yandrorb.biblioteca.ui;

import com.yandrorb.biblioteca.excepciones.LibroNoDisponibleException;
import com.yandrorb.biblioteca.excepciones.NoElementosException;
import com.yandrorb.biblioteca.excepciones.PrestamoNoDisponibleException;
import com.yandrorb.biblioteca.io.Consola;
import com.yandrorb.biblioteca.io.ValidarConsola;
import com.yandrorb.biblioteca.modelo.*;
import com.yandrorb.biblioteca.servicios.GestionPrestamo;

import java.time.LocalDateTime;

public class PrestarLibro implements Mostrable {
    Consola consola = new Consola();
    @Override
    public String mostrar() {
        return """
                ==========================
                PRESTAMOS DE LIBROS
                ==========================
                """;
    }
    public void proceso(GestionPrestamo gprestamos, Repositorio<Usuario>rUsuarios, Repositorio<Libro> rLibros) {
        ValidarConsola<Usuario> entradaUsuario=new ValidarConsola<>();
        ValidarConsola<Libro> entradaLibro=new ValidarConsola<>();
        Usuario usuario;
        Libro libro;
        try{
            usuario=entradaUsuario.validarDato("Ingrese su id de usuario (001 ->1):",rUsuarios);
            boolean seguir=true;
            while (seguir){
                if(usuario.getPrestamos().length>usuario.getTipoUsuario().getCantidadPrestamos()){
                    throw new PrestamoNoDisponibleException("Usted tiene prestado "+usuario.getPrestamos().length+" de "
                            +usuario.getTipoUsuario().getCantidadPrestamos()+", no puede prestar mas libros");
                }
                ListarLibro listarLibros=new ListarLibro();
                listarLibros.proceso(rLibros);
                libro=entradaLibro.validarDato("Ingrese el id del libro (001 ->1):",rLibros);
                try {
                    if(libro.estaDisponible() == EnumEstado.PRESTADO){
                        throw new LibroNoDisponibleException("No hay copias disponibles");
                    }
                    Prestamo prestamo=new Prestamo(libro,usuario, LocalDateTime.now(),30);
                    libro.prestar();
                    usuario.agregarPrestamo(prestamo);
                    gprestamos.addPrestamo(prestamo);
                    consola.mostrarMensaje("Se ha agregado correctamente el libro");
                }catch (LibroNoDisponibleException e){
                    consola.mostrarMensaje(e.getMessage());
                }
                consola.mostrarMensaje("¿Desea continuar? (Y)Si (Cualquier otra tecla)No");
                if(!consola.leerTexto(3).equalsIgnoreCase("y")) seguir=false;
            }
        }catch (NoElementosException|PrestamoNoDisponibleException e){
            consola.mostrarMensaje(e.getMessage());
        }
    }
}
