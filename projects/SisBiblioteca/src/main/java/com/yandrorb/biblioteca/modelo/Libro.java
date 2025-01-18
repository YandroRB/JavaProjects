package com.yandrorb.biblioteca.modelo;

import java.time.LocalDate;

public class Libro implements Identificable {
    private final String identificador;
    private String titulo;
    private String autor;
    private String editorial;
    private LocalDate fechaPublicacion;
    private String ISBN;
    private String genero;
    private int numeroCopias;
    private int copiasDisponibles;

    public Libro(String identificador, String titulo, String autor, String editorial, String ISBN) {
        this.identificador = identificador;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.ISBN = ISBN;
        this.numeroCopias=1;
    }

    public Libro(String identificador, String titulo, String editorial, String ISBN) {
        this.identificador = identificador;
        this.titulo = titulo;
        this.editorial = editorial;
        this.ISBN = ISBN;
        this.numeroCopias=1;
    }

    public Libro(String identificador, String titulo, LocalDate fechaPublicacion, String ISBN, String genero, int numeroCopias) {
        this.identificador = identificador;
        this.titulo = titulo;
        this.fechaPublicacion = fechaPublicacion;
        this.ISBN = ISBN;
        this.genero = genero;
        this.numeroCopias = numeroCopias;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNumeroCopias() {
        return numeroCopias;
    }

    public void setNumeroCopias(int numeroCopias) {
        this.numeroCopias = numeroCopias;
    }

    public int getCopiasDisponibles() {
        return copiasDisponibles;
    }

    @Override
    public String getIdentificador() {
        return this.identificador;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public void prestar(){
        if(this.copiasDisponibles>0) this.copiasDisponibles--;
    }
    public void devolver(){
        if(this.copiasDisponibles<this.numeroCopias) this.copiasDisponibles++;
    }
    public EnumEstado estaDisponible(){
        return this.copiasDisponibles>0?EnumEstado.DISPONIBLE:EnumEstado.PRESTADO;
    }

    @Override
    public String toString(){
        return String.format("%-10s%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s",this.identificador,this.titulo,this.autor,this.editorial,this.fechaPublicacion,this.ISBN,this.genero,this.numeroCopias,this.copiasDisponibles);
    }
}
