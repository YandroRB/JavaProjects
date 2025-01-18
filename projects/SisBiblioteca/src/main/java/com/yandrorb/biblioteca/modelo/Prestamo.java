package com.yandrorb.biblioteca.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Prestamo implements Identificable  {
    private final String identificador;
    private Libro libroPrestado;
    private Usuario prestador;
    private LocalDateTime fechaPrestamo;
    private LocalDateTime fechaDevuelto;
    private LocalDateTime fechaDevolucion;

    public Prestamo(String identificador,Libro libroPrestado, Usuario prestador, LocalDateTime fechaPrestamo, LocalDateTime fechaDevolucion) {
        this.identificador = identificador;
        this.libroPrestado = libroPrestado;
        this.prestador = prestador;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public Libro getLibroPrestado() {
        return libroPrestado;
    }

    public void setLibroPrestado(Libro libroPrestado) {
        this.libroPrestado = libroPrestado;
    }

    public Usuario getPrestador() {
        return prestador;
    }

    public void setPrestador(Usuario prestador) {
        this.prestador = prestador;
    }

    public LocalDateTime getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDateTime fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDateTime getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public LocalDateTime getFechaDevuelto() {
        return fechaDevuelto;
    }

    public void setFechaDevuelto(LocalDateTime fechaDevuelto) {
        this.fechaDevuelto = fechaDevuelto;
    }

    public boolean estaVencido(){
        return this.fechaDevuelto.isAfter(fechaDevolucion);
    }
    public long diasRestantes(){
        return ChronoUnit.DAYS.between(fechaPrestamo, fechaDevolucion);
    }
    public void devolver(){
        this.fechaDevuelto = LocalDateTime.now();
    }
    @Override
    public String toString(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("%-10s%-20s%-20s%-20s%-20s%-20s%-20s",
                this.identificador,
                libroPrestado.getTitulo(),
                prestador.getNombre(),
                prestador.getApellido(),
                fechaPrestamo.format(formato),
                fechaDevolucion.format(formato),
                fechaDevuelto!=null?fechaDevuelto.format(formato):"");
    }

    @Override
    public String getIdentificador() {
        return this.identificador;
    }
}
