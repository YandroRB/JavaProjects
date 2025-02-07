package com.yandrorb.biblioteca.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Prestamo implements Identificable  {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private final String identificador;
    private Libro libroPrestado;
    private Usuario prestador;
    private LocalDateTime fechaPrestamo;
    private LocalDateTime fechaDevuelto;
    private LocalDateTime fechaDevolucion;

    public Prestamo(Libro libroPrestado, Usuario prestador, LocalDateTime fechaPrestamo,int diasDevolucion) {
        this.identificador = String.valueOf(ID_GENERATOR.incrementAndGet());
        this.libroPrestado = libroPrestado;
        this.prestador = prestador;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaPrestamo.plusDays(diasDevolucion);
    }

    public Prestamo(String identificador, Libro libroPrestado, Usuario prestador, LocalDateTime fechaPrestamo, LocalDateTime fechaDevuelto, LocalDateTime fechaDevolucion) {
        ID_GENERATOR.incrementAndGet();
        this.identificador = identificador;
        this.libroPrestado = libroPrestado;
        this.prestador = prestador;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevuelto = fechaDevuelto;
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
    public void devolverEnDias(int dias) {
        fechaDevolucion = fechaPrestamo.plusDays(dias);
    }
    public boolean estaVencido(){
        return LocalDateTime.now().isAfter(fechaDevolucion);
    }
    public long diasRestantes(){
        return ChronoUnit.DAYS.between(LocalDateTime.now(), fechaDevolucion);
    }
    public boolean devolver(){
        if(this.fechaDevuelto == null){
            this.fechaDevuelto = LocalDateTime.now();
            return true;
        }
        return false;
    }
    public String toCSV(){
        return identificador+","+libroPrestado.getIdentificador()+","+prestador.getIdentificador()+","+
                fechaPrestamo+","+fechaDevuelto+","+fechaDevolucion;
    }
    @Override
    public String toString(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("%-10s%-20s%-50s%-20s%-20s%-20s",
                this.identificador,
                libroPrestado.getIdentificador()+" "+libroPrestado.getTitulo(),
                prestador.getIdentificador()+" "+prestador.getNombre()+" "+
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
