package com.yandrorb.biblioteca.ui;

import com.yandrorb.biblioteca.excepciones.NoElementosException;
import com.yandrorb.biblioteca.excepciones.ObjectoNoDisponibleException;
import com.yandrorb.biblioteca.io.ValidarConsola;
import com.yandrorb.biblioteca.modelo.*;
import com.yandrorb.biblioteca.servicios.GestionPrestamo;

public class DevolverLibro implements Mostrable {
    ValidarConsola<Identificable> validar = new ValidarConsola<>();
    @Override
    public String mostrar() {
        return """
               ==========================
               DEVOLVER LIBRO
               ==========================
               """;
    }
    public void proceso(GestionPrestamo gRepositorio){
        int opcion = validar.leerOpcionValidada(valor -> valor >= 0 && valor < EnumBuscarPor.values().length,
                "0)PRESTAMOS\n"+"1)USUARIO\n"+"2)LIBRO");
        EnumBuscarPor buscarPor=EnumBuscarPor.values()[opcion];
        ListarPrestamos listarPrestamos =new ListarPrestamos();

        try{
            listarPrestamos.proceso(gRepositorio,EnumListarPrestamo.TODOS);
            validar.mostrarMensaje("Ingrese el id del "+EnumBuscarPor.values()[opcion]);
            Prestamo prestamo=gRepositorio.getPrestamoPor(validar.leerTexto(), buscarPor);
            if(!prestamo.estaVencido()&&prestamo.getFechaDevuelto()==null){
                validar.mostrarMensaje("Le quedan aun "+prestamo.diasRestantes()+" para devolver " +
                        "el libro, ¿Desea devolverlo? Y(Si) Cualquier tecla(NO)");
                String resputa=validar.leerTexto(3);
                if(!resputa.equalsIgnoreCase("y")) return;
            }
            if(prestamo.devolver()){
                prestamo.getPrestador().eliminarPrestamo(prestamo);
                prestamo.getLibroPrestado().devolver();
                validar.mostrarMensaje("Se ha registrado la devolucion correctamente");
            }
            else{
                validar.mostrarMensaje("El libro ya ha sido devuelto");
            }
        }catch (ObjectoNoDisponibleException | NoElementosException e){
            validar.mostrarMensaje(e.getMessage());
        }
    }
}
