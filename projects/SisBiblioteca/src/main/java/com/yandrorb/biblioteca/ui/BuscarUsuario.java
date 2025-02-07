package com.yandrorb.biblioteca.ui;

import com.yandrorb.biblioteca.excepciones.LibroNoDisponibleException;
import com.yandrorb.biblioteca.excepciones.UsuarioNoDisponibleException;
import com.yandrorb.biblioteca.io.Consola;
import com.yandrorb.biblioteca.modelo.*;

public class BuscarUsuario implements Mostrable<Usuario>, Procesable<Usuario> {
    Consola consola=new Consola();
    @Override
    public String mostrar() {
        return """
                ==========================
                BUSCAR USUARIO
                ==========================
               """;
    }

    @Override
    public void proceso(Repositorio<Usuario> repositorio) {
        consola.mostrarMensaje("Ingrese el id del usuario para mostrar detalles (001 -> 1) : ");
        String identificador=consola.leerTexto();
        Usuario usuario=repositorio.buscar(identificador);
        DetalleUsuario detalleUsuario= new DetalleUsuario();
        try{
            consola.mostrarMensaje(detalleUsuario.mostrar(usuario));
        }catch (UsuarioNoDisponibleException e){
            consola.mostrarMensaje(e.getMessage());
        }
    }
}
