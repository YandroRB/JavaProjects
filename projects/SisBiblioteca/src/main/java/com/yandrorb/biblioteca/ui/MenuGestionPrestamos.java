package com.yandrorb.biblioteca.ui;

import com.yandrorb.biblioteca.excepciones.NoElementosException;
import com.yandrorb.biblioteca.io.Consola;
import com.yandrorb.biblioteca.modelo.*;
import com.yandrorb.biblioteca.servicios.GestionPrestamo;

public class MenuGestionPrestamos implements Mostrable<Identificable> {
    Consola consola = new Consola();
    @Override
    public String mostrar() {
        return """
                ==========================
                GESTION DE PRESTAMOS
                ==========================
                1. Prestar Libro
                2. Devolver Libro
                3. Buscar Prestamo
                4. Listar Prestamos
                5. Eliminar Prestamo
                6. Regresar
                ==========================
                Seleccione una opcion:
                """;
    }
    public void opciones(GestionPrestamo gestion,
                         Repositorio<Usuario> repositorioU,
                         Repositorio<Libro> repositorioL)
    {
        int opcion=consola.leerOpcion();
        switch (opcion){
            case 1->{
                PrestarLibro prestarLibro=new PrestarLibro();
                consola.mostrarMensaje(prestarLibro.mostrar());
                prestarLibro.proceso(gestion,repositorioU,repositorioL);
            }
            case 2->{
                DevolverLibro devolverLibro=new DevolverLibro();
                consola.mostrarMensaje(devolverLibro.mostrar());
                devolverLibro.proceso(gestion);
            }
            case 3->{
                BuscarPrestamo buscarPrestamo=new BuscarPrestamo();
                consola.mostrarMensaje(buscarPrestamo.mostrar());
                buscarPrestamo.proceso(gestion);
            }
            case 4->{
                ListarPrestamos listarPrestamos = new ListarPrestamos();
                BuscarPrestamo buscarPrestamo = new BuscarPrestamo();
                try {
                    listarPrestamos.proceso(gestion,EnumListarPrestamo.TODOS);
                    buscarPrestamo.proceso(gestion);
                } catch (NoElementosException e) {
                    consola.mostrarMensaje(e.getMessage());
                }
            }
            case 5->{
                EliminarPrestamo eliminarPrestamo = new EliminarPrestamo();
                consola.mostrarMensaje(eliminarPrestamo.mostrar());
                eliminarPrestamo.proceso(gestion);
            }
        }


    }

}
