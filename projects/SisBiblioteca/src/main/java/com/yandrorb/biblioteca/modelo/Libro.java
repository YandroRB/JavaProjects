package com.yandrorb.biblioteca.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class Libro implements Identificable {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private String identificador;
    private String titulo;
    private String autor;
    private String editorial;
    private LocalDate fechaPublicacion;
    private String ISBN;
    private String genero;
    private int numeroCopias;
    private int copiasDisponibles;

    public Libro(String titulo, String autor, String editorial,LocalDate fechaPublicacion, String ISBN) {
        this.identificador = String.valueOf(ID_GENERATOR.incrementAndGet());
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
        this.editorial = editorial;
        this.ISBN = ISBN;
        this.numeroCopias=copiasDisponibles=1;
    }

    public Libro(String titulo, String editorial, String ISBN) {
        this.identificador = String.valueOf(ID_GENERATOR.incrementAndGet());;
        this.titulo = titulo;
        this.editorial = editorial;
        this.ISBN = ISBN;
        this.numeroCopias=copiasDisponibles=1;
    }

    public Libro(String titulo,String autor,String editorial, LocalDate fechaPublicacion, String ISBN, String genero, int numeroCopias) {
        this.identificador = String.valueOf(ID_GENERATOR.incrementAndGet());;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.fechaPublicacion = fechaPublicacion;
        this.ISBN = ISBN;
        this.genero = genero;
        this.numeroCopias=copiasDisponibles= numeroCopias;
    }

    public Libro(String identificador, String titulo, String autor, String editorial, LocalDate fechaPublicacion, String ISBN, String genero, int numeroCopias, int copiasDisponibles) {
        ID_GENERATOR.incrementAndGet();
        this.identificador = identificador;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.fechaPublicacion = fechaPublicacion;
        this.ISBN = ISBN;
        this.genero = genero;
        this.numeroCopias = numeroCopias;
        this.copiasDisponibles = copiasDisponibles;
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
    public void setIdentificador(String identificador){
        this.identificador=identificador;
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

    public String toCSV(){
        return identificador+","+titulo+","+autor+","+editorial+","+fechaPublicacion+","+
                ISBN+","+genero+","+numeroCopias+","+copiasDisponibles;
    }
    @Override
    public String toString(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada=fechaPublicacion!=null?fechaPublicacion.format(formato):"";
        String identificador=String.format("%03d",Integer.parseInt(this.identificador));
        return String.format("%-10s%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s",identificador,this.titulo,this.autor,this.editorial,fechaFormateada,this.ISBN,this.genero,this.numeroCopias,this.copiasDisponibles);
    }
}
