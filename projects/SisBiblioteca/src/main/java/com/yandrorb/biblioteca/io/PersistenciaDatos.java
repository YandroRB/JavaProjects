package com.yandrorb.biblioteca.io;

import com.yandrorb.biblioteca.excepciones.ArchivoNoEncontradoException;
import com.yandrorb.biblioteca.modelo.*;
import com.yandrorb.biblioteca.servicios.GestionPrestamo;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaDatos {
    public void guardarUsuario(Repositorio<Usuario> uRepositorio) throws ArchivoNoEncontradoException{
        guardarDatos(uRepositorio,"identificador,nombre,apellido,email,telefono,tipoUsuario,prestamos",
                "projects/SisBiblioteca/src/main/java/usuarios.csv");
    }
    public void guardarLibros(Repositorio<Libro> libRepositorio) throws ArchivoNoEncontradoException{
        guardarDatos(libRepositorio,"identificador,titulo,autor,editorial,fechaPublicacion," +
                "ISBN,genero,numeroCopias,copiasDisponibles","projects/SisBiblioteca/src/main/java/libros.csv");
    }
    public void guardarPrestamos(GestionPrestamo gprestamos) throws ArchivoNoEncontradoException{
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("projects/SisBiblioteca/src/main/java/prestamos.csv"))){
            bw.write("identificador,libroPrestado,prestador,fechaPrestamo,fechaDevuelto,fechaDevolucion");
            for(Prestamo p:gprestamos.getPrestamos()){
                bw.newLine();
                bw.write(p.toCSV());
            }

        }catch (IOException e){
            throw new ArchivoNoEncontradoException("No se guardar los prestamos");
        }
    }
    public List<Prestamo> leerPrestamos(Repositorio<Libro> rLibros,Repositorio<Usuario> rUsuarios ) throws ArchivoNoEncontradoException{
        List<Prestamo> prestamos = new ArrayList<>();
        try(BufferedReader br= new BufferedReader(new FileReader("projects/SisBiblioteca/src/main/java/prestamos.csv"))){
            String linea;
            br.readLine();
            while((linea = br.readLine())!=null){
                Prestamo prestamo = getPrestamo(linea,rLibros,rUsuarios);
                prestamos.add(prestamo);
            }
        }catch (IOException e){
            throw new ArchivoNoEncontradoException(e.getMessage());
        }
        return prestamos;
    }
    public List<Libro> leerLibros() throws ArchivoNoEncontradoException{
        List<Libro> libros = new ArrayList<>();
        try(BufferedReader br=new BufferedReader(new FileReader("projects/SisBiblioteca/src/main/java/libros.csv"))){
            String linea;
            br.readLine();
            while((linea=br.readLine())!=null){
                Libro libro = getLibro(linea);
                libros.add(libro);
            }

        }catch(IOException e){
            throw new ArchivoNoEncontradoException("No se encontraron los libros");
        }
        return libros;
    }
    public List<Usuario> leerUsuarios() throws ArchivoNoEncontradoException{
        List<Usuario> usuarios = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("projects/SisBiblioteca/src/main/java/usuarios.csv"))){
            String linea;
            br.readLine();
            while((linea=br.readLine())!=null){
                Usuario usuario=getUsuario(linea);
                usuarios.add(usuario);
            }
        }catch(IOException e){
            throw new ArchivoNoEncontradoException("No se encontraron los usuarios");
        }
        return usuarios;
    }
    private Prestamo getPrestamo(String linea,Repositorio<Libro> rLibros,Repositorio<Usuario> rUsuarios){
        String[] datos=linea.split(",");
        String identificador=datos[0];
        Libro libroPrestado=rLibros.buscar(datos[1]);
        Usuario prestador=rUsuarios.buscar(datos[2]);
        LocalDateTime fechaPrestamo=LocalDateTime.parse(datos[3]);
        LocalDateTime fechaDevuelto=datos[4].equals("null")?null:LocalDateTime.parse(datos[4]);
        LocalDateTime fechaDevolucion=LocalDateTime.parse(datos[5]);
        Prestamo prestamo= new Prestamo(identificador,libroPrestado,prestador,fechaPrestamo,fechaDevuelto,fechaDevolucion);
        if(prestador!=null){
            for(String pID: prestador.getListPrestamos()){
                if(identificador.equals(pID)){
                    prestador.agregarPrestamo(prestamo);
                }
            }
        }
        return prestamo;
    }
    private Usuario getUsuario(String linea){
        String[] datos = linea.split(",");
        String identificador=datos[0];
        String nombre=datos[1];
        String apellido=datos[2];
        String email=datos[3].equals("null")?null:datos[3];
        String telefono=datos[4];
        EnumTipoU tipoUsuario=EnumTipoU.valueOf(datos[5]);
        String listPrestamos=datos[6].equals("null")?"":datos[6];
        return new Usuario(identificador,nombre,apellido,email,telefono,tipoUsuario,listPrestamos);
    }
    private Libro getLibro(String linea) {
        String[] datos = linea.split(",");
        String identificador=datos[0];
        String titulo=datos[1];
        String autor=datos[2];
        String editorial=datos[3];
        LocalDate fechaPublicacion=datos[4].equals("null") ? null : LocalDate.parse(datos[4]);
        String ISBN=datos[5];
        String genero=datos[6].equals("null")?null:datos[6];
        int numeroCopias=Integer.parseInt(datos[7]);
        int copiasDisponibles=Integer.parseInt(datos[8]);
        return new Libro(identificador,titulo,autor,editorial,
                fechaPublicacion,ISBN,genero,numeroCopias,copiasDisponibles);
    }

    private <O extends Identificable> void guardarDatos(Repositorio<O> repositorio,String header,String ubicacionArchivo)
            throws ArchivoNoEncontradoException{
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(ubicacionArchivo))){
            bw.write(header);
            for(O o:repositorio.getLista()){
                bw.newLine();
                bw.write(o.toCSV());
            }

        } catch (IOException e) {
            throw new ArchivoNoEncontradoException("No se pudo guardar los datos");
        }
    }
}
