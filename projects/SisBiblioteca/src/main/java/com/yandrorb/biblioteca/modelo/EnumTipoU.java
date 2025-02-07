package com.yandrorb.biblioteca.modelo;

public enum EnumTipoU {
    ESTUDIANTE(5),//3
    PROFESOR(5),
    EXTERNO(5);//1

    final int cantidadPrestamos;
    EnumTipoU(int cantidadPrestamos){
        this.cantidadPrestamos = cantidadPrestamos;
    }
    public int getCantidadPrestamos() {
        return cantidadPrestamos;
    }
}
