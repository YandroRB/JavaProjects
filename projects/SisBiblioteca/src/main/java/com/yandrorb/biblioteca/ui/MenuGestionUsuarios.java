package com.yandrorb.biblioteca.ui;

import com.yandrorb.biblioteca.excepciones.NoElementosException;
import com.yandrorb.biblioteca.io.Consola;
import com.yandrorb.biblioteca.modelo.Mostrable;
import com.yandrorb.biblioteca.modelo.Repositorio;
import com.yandrorb.biblioteca.modelo.Usuario;

public class MenuGestionUsuarios implements Mostrable<Usuario> {
    private final Consola consola = new Consola();
    @Override
    public String mostrar(){
        return """
                ==========================
                GESTION DE USUARIOS
                ==========================
                1. Registrar Usuario
                2. Listar Usuarios
                3. Buscar Usuario
                4. Eliminar Usuario
                5. Regresar
                ==========================
                Seleccione una opcion:
                """;
    }

    @Override
    public void opciones(Repositorio<Usuario> repositorio) {
        int opcion = consola.leerOpcion();
        switch (opcion) {
            case 1 -> {
                RegistrarUsuario registro = new RegistrarUsuario();
                registro.proceso(repositorio);
            }
            case 2 -> {
                ListarUsuario listar = new ListarUsuario();
                BuscarUsuario buscar = new BuscarUsuario();
                try{
                    listar.proceso(repositorio);
                    buscar.proceso(repositorio);
                }catch(NoElementosException e){
                    consola.mostrarMensaje(e.getMessage());
                }
            }
            case 3 -> {
                BuscarUsuario buscar = new BuscarUsuario();
                consola.mostrarMensaje(buscar.mostrar());
                buscar.proceso(repositorio);
            }
            case 4 -> {
                EliminarUsuario eliminar = new EliminarUsuario();
                consola.mostrarMensaje(eliminar.mostrar());
                eliminar.proceso(repositorio);
            }
        }

    }
}
