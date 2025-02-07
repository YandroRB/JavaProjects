package com.yandrorb.biblioteca.ui;

import com.yandrorb.biblioteca.excepciones.NoElementosException;
import com.yandrorb.biblioteca.io.Consola;
import com.yandrorb.biblioteca.modelo.EnumListarPrestamo;
import com.yandrorb.biblioteca.servicios.GestionPrestamo;

public class ListarPrestamos  {
    Consola consola = new Consola();
    public void proceso(GestionPrestamo gestionPrestamo, EnumListarPrestamo enumListarPrestamo)throws NoElementosException {
        String listaPrestamos= gestionPrestamo.listarPrestamos(enumListarPrestamo);
        consola.mostrarMensaje(String.format("""
                    ==========================
                    LISTA DE PRESTAMOS POR %s
                    ==========================
                    """,enumListarPrestamo.toString()));
        consola.mostrarMensaje( String.format("%-10s%-20s%-50s%-20s%-20s%-20s","ID","LIBRO","NOMBRES y APELLIDOS",
                "FECHA PESTAMO","FECHA DEVOLUCION","FECHA DEVUELTO"));
        consola.mostrarMensaje(listaPrestamos);
    }
}
