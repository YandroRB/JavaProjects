package com.yandrorb.biblioteca.io;

import com.yandrorb.biblioteca.excepciones.LibroNoDisponibleException;
import com.yandrorb.biblioteca.excepciones.NoElementosException;
import com.yandrorb.biblioteca.excepciones.ObjectoNoDisponibleException;
import com.yandrorb.biblioteca.excepciones.UsuarioNoDisponibleException;
import com.yandrorb.biblioteca.modelo.Identificable;
import com.yandrorb.biblioteca.modelo.Repositorio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.spec.ECField;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Consola {
    private  final PrintWriter console = new PrintWriter(System.out, true);
    private final BufferedReader lector = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

    public void mostrarMensaje(String mensaje){
        console.println(mensaje);
    }
    public int leerOpcion(){
        try{
            return Integer.parseInt(lector.readLine());
        }catch(IOException | NumberFormatException e) {
            return -1;
        }
    }
    public String leerTexto(int longitud){
        try{
            String texto = lector.readLine();
            return texto.length()<longitud?texto:texto.substring(0,longitud);
        }catch(IOException e){
            return "";
        }
    }
    public String leerTexto(){
        try{
            return lector.readLine();
        }catch(IOException e){
            return "";
        }
    }
    public LocalDate leerFecha(){
        try{
            return LocalDate.parse(lector.readLine());
        }catch (IOException | DateTimeParseException e){
            console.println("Hubo un problema al procesar la fecha ingresada, no cumple con el formato solicitado. No se asignará la fecha.");
            return null;
        }
    }

}
