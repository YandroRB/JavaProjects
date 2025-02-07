package com.yandrorb.biblioteca.modelo;

import com.yandrorb.biblioteca.excepciones.NoElementosException;

public interface Gestionable<T,O extends Identificable> {
    T agregar(O objecto);
    boolean eliminar(O objecto);
    O buscar(String identificador);
    String listar() throws NoElementosException;
    Boolean estaVacio();
}
