package com.yandrorb.biblioteca.modelo;

import com.yandrorb.biblioteca.excepciones.LibroNoDisponibleException;
import com.yandrorb.biblioteca.excepciones.PrestamoNoDisponibleException;
import com.yandrorb.biblioteca.excepciones.UsuarioNoDisponibleException;

public interface Mostrable<T extends Identificable> {
    String mostrar();
    default String mostrar(Repositorio<T> repositorio){
        return "";
    }
    default String mostrar(T identificable) throws LibroNoDisponibleException, PrestamoNoDisponibleException,UsuarioNoDisponibleException { return "";}
    default void opciones() {}
    default void opciones(Repositorio<T> repositorio) {}
}
