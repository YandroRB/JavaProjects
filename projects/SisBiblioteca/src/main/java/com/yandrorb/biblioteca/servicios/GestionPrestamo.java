package com.yandrorb.biblioteca.servicios;

import com.yandrorb.biblioteca.excepciones.NoElementosException;
import com.yandrorb.biblioteca.excepciones.ObjectoNoDisponibleException;
import com.yandrorb.biblioteca.modelo.EnumBuscarPor;
import com.yandrorb.biblioteca.modelo.EnumListarPrestamo;
import com.yandrorb.biblioteca.modelo.Prestamo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class GestionPrestamo {
    private final List<Prestamo> prestamos;
    public GestionPrestamo(){
        this.prestamos=new ArrayList<>();
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos.clear();
        this.prestamos.addAll(prestamos);
    }
    public Prestamo[] getPrestamos() {
        return prestamos.toArray(Prestamo[]::new);
    }


    public void addPrestamo(Prestamo prestamo){
        this.prestamos.add(prestamo);
    }
    public boolean removePrestamo(Prestamo prestamo){
        return this.prestamos.remove(prestamo);
    }
    public Prestamo getPrestamo(String identificador) throws ObjectoNoDisponibleException{
        for (Prestamo prestamo: prestamos) {
            if(prestamo.getIdentificador().equals(identificador)){
                return prestamo;
            }
        }
        throw new ObjectoNoDisponibleException("No se ha encotrado el elemento a buscar");
    }
    public Prestamo getPrestamoPor(String identificador, EnumBuscarPor buscarPor) throws ObjectoNoDisponibleException{
        for (Prestamo prestamo: prestamos) {
           boolean coincide=switch (buscarPor){
                case EnumBuscarPor.PRESTAMO ->prestamo.getIdentificador().equals(identificador);
                case EnumBuscarPor.LIBRO ->prestamo.getLibroPrestado().getIdentificador().equals(identificador);
                case EnumBuscarPor.USUARIO ->prestamo.getPrestador().getIdentificador().equals(identificador);
            };
           if(coincide) return prestamo;
        }
        throw new ObjectoNoDisponibleException("No se ha encotrado el elemento a buscar");
    }
    public String listarPrestamos(EnumListarPrestamo listarPor) throws NoElementosException{
        if(prestamos.isEmpty()) throw new NoElementosException("No hay prestamos que listar");
        StringBuilder sb = new StringBuilder();
        return switch (listarPor){
          case TODOS -> {
              for (Prestamo prestamo: prestamos) {
                  sb.append(prestamo.toString()).append("\n");
              }
              yield sb.toString();
          }
          case ACTIVOS -> {
              for(Prestamo prestamo:prestamos){
                  if(prestamo.getFechaDevuelto() ==null && !prestamo.estaVencido()){
                      sb.append(prestamo).append("\n");
                  }
              }
              yield sb.toString();
          }
          case VENCIDOS -> {
              for(Prestamo prestamo:prestamos){
                  if(prestamo.estaVencido()){
                      sb.append(prestamo).append("\n");
                  }
              }
              yield sb.toString();
          }
          case DEVUELTOS -> {
               for(Prestamo prestamo:prestamos){
                   if(prestamo.getFechaDevuelto()!=null){
                       sb.append(prestamo).append("\n");
                   }
               }
               yield sb.toString();
          }
        };
    }
    public void devolverLibro(String identificador) throws ObjectoNoDisponibleException{
        Prestamo prestamo = getPrestamo(identificador);
        if(prestamo!=null){
            prestamo.devolver();
        }
    }
}
