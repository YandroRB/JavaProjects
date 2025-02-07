package com.yandrorb.biblioteca.ui;

import com.yandrorb.biblioteca.io.Consola;
import com.yandrorb.biblioteca.modelo.EnumTipoU;
import com.yandrorb.biblioteca.modelo.Procesable;
import com.yandrorb.biblioteca.modelo.Repositorio;
import com.yandrorb.biblioteca.modelo.Usuario;

public class RegistrarUsuario implements Procesable<Usuario> {
    Consola consola=new Consola();
    @Override
    public void proceso(Repositorio<Usuario> repositorio) {
        String nombres;
        String apellidos;
        String numeroTelefono;
        EnumTipoU tipo;
        consola.mostrarMensaje("""
                ==========================
                Registrar usuario
                ==========================
                Ingrese sus nombres:
                """);
        nombres=consola.leerTexto(31);
        consola.mostrarMensaje("Ingrese sus apellidos :");
        apellidos=consola.leerTexto(31);
        consola.mostrarMensaje("Ingrese el numero de telefono :");
        numeroTelefono=consola.leerTexto(10);
        tipoU:do{
            consola.mostrarMensaje("""
                    Ingrese el tipo de usuario:
                    0)Estudiante
                    1)Profesor
                    2)Externo
                    """);
            int opcion=consola.leerOpcion();
            switch (opcion){
                case 0,1,2: {
                    tipo=EnumTipoU.values()[opcion];
                    break tipoU;
                }
                default: {consola.mostrarMensaje("Opcion no valido");}
            }
        }while(true);
        repositorio.agregar(new Usuario(nombres,apellidos,numeroTelefono,tipo));
    }
}
