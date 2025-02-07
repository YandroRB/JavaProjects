package com.yandrorb.biblioteca.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Usuario implements Identificable {
    private  static final AtomicInteger idUsuario = new AtomicInteger(0);
    private String identificador;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private EnumTipoU tipoUsuario;
    private final List<Prestamo> prestamos;
    private String[] listPrestamos;

    public Usuario( String nombre, String apellido, String telefono, EnumTipoU tipoUsuario) {
        this.identificador = String.valueOf(idUsuario.incrementAndGet());
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.tipoUsuario = tipoUsuario;
        this.prestamos = new ArrayList<>();
    }

    public Usuario(String identificador, String nombre, String apellido, String email, String telefono, EnumTipoU tipoUsuario, String listPrestamos) {
        idUsuario.incrementAndGet();
        this.identificador = identificador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.tipoUsuario = tipoUsuario;
        this.listPrestamos = listPrestamos.split("-");
        this.prestamos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Prestamo[] getPrestamos() {
        return prestamos.toArray(Prestamo[]::new);
    }
    public String[] getListPrestamos() {
        return listPrestamos;
    }

    public void setListPrestamos(String[] listPrestamos) {
        this.listPrestamos = listPrestamos;
    }

    public EnumTipoU getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(EnumTipoU tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String getIdentificador() {
        return this.identificador;
    }
    public void setIdentificador(String identificador) {
        this.identificador=identificador;
    }
    public Usuario agregarPrestamo(Prestamo prestamo) {
        if(this.puedePresar()) {
            this.prestamos.add(prestamo);
        }
        return this;
    }
    public Usuario eliminarPrestamo(Prestamo prestamo) {
        this.prestamos.remove(prestamo);
        return this;
    }
    public boolean puedePresar() {
        return this.prestamos.size() < this.tipoUsuario.getCantidadPrestamos();
    }

    public String listarPrestamos() {
        StringBuilder sb = new StringBuilder();
        for (Prestamo prestamo : prestamos) {
            sb.append(prestamo.toString()).append("\n");
        }
        return sb.toString();
    }
    public String toCSV(){
        StringBuilder prestamosID = new StringBuilder();
        for(Prestamo p : this.prestamos){
            prestamosID.append(p.getIdentificador()).append("-");
        }

        return identificador+","+nombre+","+apellido+","+email+","+telefono+","+tipoUsuario.toString()+","+(prestamosID.isEmpty()?"null":prestamosID.toString());
    }
    @Override
    public String toString(){
        String identificador=String.format("%03d",Integer.parseInt(this.identificador));
        return String.format("%-10s%-15s%-15s%-15s%-15s",identificador,this.nombre,this.apellido,this.telefono,this.tipoUsuario);
    }

}
