package com.yandrorb.biblioteca.modelo;

import java.util.List;

public class Usuario implements Identificable {
    private final String identificador;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private EnumTipoU tipoUsuario;
    private List<Prestamo> prestamos;

    public Usuario(String identificador, String nombre, String apellido, String telefono, EnumTipoU tipoUsuario) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.tipoUsuario = tipoUsuario;
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
    @Override
    public String toString(){
        return String.format("%-10s%-15s%-15s%-15s%-15s",this.identificador,this.nombre,this.apellido,this.telefono,this.tipoUsuario);
    }

}
