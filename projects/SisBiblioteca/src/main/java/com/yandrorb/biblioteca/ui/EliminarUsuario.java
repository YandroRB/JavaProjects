package com.yandrorb.biblioteca.ui;

import com.yandrorb.biblioteca.io.Consola;
import com.yandrorb.biblioteca.modelo.*;

public class EliminarUsuario implements Mostrable<Usuario>, Procesable<Usuario> {
    Consola consola = new Consola();
    @Override
    public String mostrar() {
        return """
                ==========================
                ELIMINAR Usuario
                ==========================
               """;
    }

    @Override
    public void proceso(Repositorio<Usuario> repositorio) {
        consola.mostrarMensaje("Ingrese el id del usuario a eliminar (001 -> 1)");
        Usuario usuario=repositorio.buscar(consola.leerTexto());
        if(repositorio.eliminar(usuario)){
            consola.mostrarMensaje(usuario.getNombre()+" "+usuario.getApellido()+" ha sido eliminado");
        }
        else{
            consola.mostrarMensaje("El usuario no existe");
        }

    }
}
