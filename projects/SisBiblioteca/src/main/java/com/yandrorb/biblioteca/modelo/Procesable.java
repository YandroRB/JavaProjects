package com.yandrorb.biblioteca.modelo;

import com.yandrorb.biblioteca.excepciones.NoElementosException;

public interface Procesable<T extends Identificable>{
    void proceso(Repositorio<T> repositorio) throws NoElementosException;
    default void proceso(){}
}
