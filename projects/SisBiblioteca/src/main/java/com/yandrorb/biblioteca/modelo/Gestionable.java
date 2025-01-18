package com.yandrorb.biblioteca.modelo;

public interface Gestionable<T,O extends Identificable> {
    T agregar(O objecto);
    boolean eliminar(O objecto);
    O buscar(String identificador);
    String listar();
}
