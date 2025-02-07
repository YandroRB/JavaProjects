package com.yandrorb.biblioteca.ui;

import com.yandrorb.biblioteca.excepciones.LibroNoDisponibleException;
import com.yandrorb.biblioteca.excepciones.UsuarioNoDisponibleException;
import com.yandrorb.biblioteca.modelo.Mostrable;
import com.yandrorb.biblioteca.modelo.Prestamo;
import com.yandrorb.biblioteca.modelo.Usuario;

public class DetalleUsuario implements Mostrable<Usuario> {
    @Override
    public String mostrar() {
        return "";
    }

    @Override
    public String mostrar(Usuario usuario) throws UsuarioNoDisponibleException {
        if(usuario ==null) throw new UsuarioNoDisponibleException("Usuario no encontrado");
        StringBuilder listaLibros= new StringBuilder();
        String email= usuario.getEmail()==null?"":usuario.getEmail();
        for (Prestamo prestamo : usuario.getPrestamos()) {
            listaLibros.append(prestamo.getLibroPrestado().getTitulo()).append("\n");
        }
        return String.format("""
                ==========================
                Detalle de %s %s
                ==========================
                Nombres: %s
                Apellidos: %s
                email: %s
                Telefono: %s
                Tipo de usuario: %s
                Libros prestados: %s
                """,usuario.getNombre(),usuario.getApellido(),usuario.getNombre(),usuario.getApellido(),email,usuario.getTelefono()
                ,usuario.getTipoUsuario(),listaLibros);
    }
}
