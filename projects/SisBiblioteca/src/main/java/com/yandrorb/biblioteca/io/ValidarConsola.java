package com.yandrorb.biblioteca.io;

import com.yandrorb.biblioteca.excepciones.NoElementosException;
import com.yandrorb.biblioteca.excepciones.ObjectoNoDisponibleException;
import com.yandrorb.biblioteca.modelo.Identificable;
import com.yandrorb.biblioteca.modelo.Repositorio;

import java.util.function.Predicate;

public class ValidarConsola<T extends Identificable> extends Consola{
    public T validarDato(String texto, Repositorio<T> repositorio) throws NoElementosException {
        if(repositorio.estaVacio()) throw new NoElementosException("No hay elementos");
        do{
            try {
                mostrarMensaje(texto);
                String leer=leerTexto();
                T objeto=repositorio.buscar(leer);
                if(objeto!=null){
                    return objeto;
                }
                throw new ObjectoNoDisponibleException("No se ha encontrado el dato ingresado");
            }catch (ObjectoNoDisponibleException e){
                mostrarMensaje(e.getMessage());
            }
        }while (true);
    }
    public int leerOpcionValidada(Predicate<Integer> validador,String texto){
        do{
            mostrarMensaje(texto);
            int leer=leerOpcion();
            if(validador.test(leer)) return leer;
        }while(true);

    }
}
