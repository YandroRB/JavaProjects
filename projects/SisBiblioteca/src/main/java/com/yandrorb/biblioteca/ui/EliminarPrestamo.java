package com.yandrorb.biblioteca.ui;

import com.yandrorb.biblioteca.excepciones.ObjectoNoDisponibleException;
import com.yandrorb.biblioteca.io.Consola;
import com.yandrorb.biblioteca.modelo.Identificable;
import com.yandrorb.biblioteca.modelo.Mostrable;
import com.yandrorb.biblioteca.modelo.Prestamo;
import com.yandrorb.biblioteca.servicios.GestionPrestamo;

public class EliminarPrestamo implements Mostrable<Identificable> {
    Consola consola = new Consola();
    @Override
    public String mostrar() {
        return """
                ==========================
                ELIMINAR PRESTAMO
                ==========================
               """;
    }
    public void proceso(GestionPrestamo gestionPrestamo){
        consola.mostrarMensaje("Ingrese el id del prestamo para eliminar (001 -> 1)");
        Prestamo prestamo;
        try{
            prestamo=gestionPrestamo.getPrestamo(consola.leerTexto());
            if(gestionPrestamo.removePrestamo(prestamo)){
                consola.mostrarMensaje("Se ha eliminado exitosamente");
            }
            else {
                consola.mostrarMensaje("No se ha podido eliminar el elemento");
            }
        }catch (ObjectoNoDisponibleException e){
            consola.mostrarMensaje(e.getMessage());
        }
    }
}
