package com.yandrorb.biblioteca.modelo;

import com.yandrorb.biblioteca.excepciones.NoElementosException;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class Repositorio<O extends Identificable> implements Gestionable<Repositorio<O>,O> {
    private final List<O>  lista;
    public Repositorio(){
        lista=new ArrayList<>();
    }
    public void setLista(List<O> lista){
        this.lista.clear();
        this.lista.addAll(lista);
    }
    public List<O> getLista(){
        return Collections.unmodifiableList(lista);
    }
    @Override
    public Repositorio<O> agregar(O objecto) {
        lista.add(objecto);
        return this;
    }
    @Override
    public boolean eliminar(O objecto) {
        return lista.remove(objecto);
    }

    @Override
    public O buscar(String identificador) {
        for(O o:lista){
            if(o.getIdentificador().equals(identificador)){
                return o;
            }
        }
        return null;
    }

    @Override
    public String listar() throws NoElementosException {
        StringBuilder sb = new StringBuilder();
        if (lista.isEmpty()) throw new NoElementosException("No hay elementos que listar.");
        for(O o:lista){
            sb.append(o.toString()).append("\n");
        }
        return sb.toString();
    }
    @Override
    public Boolean estaVacio(){
        return lista.isEmpty();
    }
}
