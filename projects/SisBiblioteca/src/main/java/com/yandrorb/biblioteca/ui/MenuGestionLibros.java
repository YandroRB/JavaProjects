package com.yandrorb.biblioteca.ui;
import com.yandrorb.biblioteca.excepciones.LibroNoDisponibleException;
import com.yandrorb.biblioteca.excepciones.NoElementosException;
import com.yandrorb.biblioteca.io.Consola;
import com.yandrorb.biblioteca.modelo.*;
import java.time.LocalDate;


public class MenuGestionLibros implements Mostrable<Libro> {
    Consola consola=new Consola();
    @Override
    public String mostrar() {
        return """
                ==========================
                GESTION DE LIBROS
                ==========================
                1. Registrar Libro
                2. Listar Libros
                3. Buscar Libro
                4. Eliminar Libro
                5. Regresar
                ==========================
                Seleccione una opcion:
                """;
    }

    @Override
    public void opciones(Repositorio<Libro> repositorio) {
            int opcion = consola.leerOpcion();
            switch (opcion) {
                case 1 -> {
                    RegistrarLibro registro = new RegistrarLibro();
                    registro.proceso(repositorio);
                }
                case 2 -> {
                    ListarLibro listar = new ListarLibro();
                    BuscarLibro buscar = new BuscarLibro();
                    try{
                        listar.proceso(repositorio);
                        buscar.proceso(repositorio);
                    }catch(NoElementosException e){
                        consola.mostrarMensaje(e.getMessage());
                    }
                }
                case 3 -> {
                    BuscarLibro buscar = new BuscarLibro();
                    consola.mostrarMensaje(buscar.mostrar());
                    buscar.proceso(repositorio);
                }
                case 4 -> {
                    EliminarLibro eliminar = new EliminarLibro();
                    consola.mostrarMensaje(eliminar.mostrar());
                    eliminar.proceso(repositorio);
                }
            }
    }
}
