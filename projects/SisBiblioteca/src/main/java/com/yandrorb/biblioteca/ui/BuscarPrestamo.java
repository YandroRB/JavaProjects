package com.yandrorb.biblioteca.ui;

import com.yandrorb.biblioteca.excepciones.ObjectoNoDisponibleException;
import com.yandrorb.biblioteca.excepciones.PrestamoNoDisponibleException;
import com.yandrorb.biblioteca.io.Consola;
import com.yandrorb.biblioteca.io.ValidarConsola;
import com.yandrorb.biblioteca.modelo.EnumBuscarPor;
import com.yandrorb.biblioteca.modelo.Identificable;
import com.yandrorb.biblioteca.modelo.Mostrable;
import com.yandrorb.biblioteca.modelo.Prestamo;
import com.yandrorb.biblioteca.servicios.GestionPrestamo;

public class BuscarPrestamo implements Mostrable<Identificable> {
    private final ValidarConsola<Identificable> validarConsola=new ValidarConsola<>();
    @Override
    public String mostrar() {
        return """
                ==========================
                BUSCAR PRESTAMO
                ==========================
               """;
    }
    public void proceso(GestionPrestamo gestionPrestamo){
        int opcion = validarConsola.leerOpcionValidada(valor -> valor >= 0 && valor < EnumBuscarPor.values().length,
                "0)PRESTAMOS\n"+"1)USUARIO\n"+"2)LIBRO");
        EnumBuscarPor buscarPor=EnumBuscarPor.values()[opcion];
        DetallePrestamo detallePrestamo = new DetallePrestamo();
        try{
            validarConsola.mostrarMensaje("Ingrese el id del "+EnumBuscarPor.values()[opcion]);
            Prestamo prestamo=gestionPrestamo.getPrestamoPor(validarConsola.leerTexto(), buscarPor);
            validarConsola.mostrarMensaje(detallePrestamo.mostrar(prestamo));
        }catch (PrestamoNoDisponibleException | ObjectoNoDisponibleException e){
            validarConsola.mostrarMensaje(e.getMessage());
        }
    }
}
