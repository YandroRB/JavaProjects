package com.yandrorb.biblioteca.ui;

import com.yandrorb.biblioteca.excepciones.NoElementosException;
import com.yandrorb.biblioteca.io.Consola;
import com.yandrorb.biblioteca.modelo.Procesable;
import com.yandrorb.biblioteca.modelo.Repositorio;
import com.yandrorb.biblioteca.modelo.Usuario;

public class ListarUsuario implements Procesable<Usuario> {
    Consola consola=new Consola();
    @Override
    public void proceso(Repositorio<Usuario> repositorio) throws NoElementosException {
        try{
            String listaUsuarios= repositorio.listar();
            consola.mostrarMensaje("""
                        ==========================
                        Lista de usuarios registrados
                        ==========================
                        """);
            consola.mostrarMensaje(String.format("%-10s%-15s%-15s%-15s%-15s","ID","Nombres","Apellidos","Telefono","Tipo Usuario"));
            consola.mostrarMensaje(listaUsuarios);
        }catch (NoElementosException e){
            throw new NoElementosException("No se encontraron usuarios registrados");
        }
    }
}
