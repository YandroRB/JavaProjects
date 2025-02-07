package com.yandrorb.biblioteca.io;
import com.yandrorb.biblioteca.excepciones.ArchivoNoEncontradoException;
import com.yandrorb.biblioteca.modelo.Libro;
import com.yandrorb.biblioteca.modelo.Repositorio;
import com.yandrorb.biblioteca.modelo.Usuario;
import com.yandrorb.biblioteca.servicios.GestionPrestamo;
import com.yandrorb.biblioteca.ui.*;
import java.io.*;


public class ConsolaUI {
    private final Consola consola=new Consola();
    //Repositorios
    Repositorio<Libro> gestionLibro=new Repositorio<>();
    Repositorio<Usuario> gestionUsuario=new Repositorio<>();
    GestionPrestamo gestionPrestamo=new GestionPrestamo();

    //instancia de menus
    private final MenuPrincipal menuPrincipal = new MenuPrincipal();
    private final MenuGestionPrestamos menuGestionPrestamos = new MenuGestionPrestamos();
    private final MenuGestionLibros menuGestionLibros = new MenuGestionLibros();
    private final MenuGestionUsuarios menuGestionUsuarios = new MenuGestionUsuarios();
    private PersistenciaDatos persistenciaDatos = new PersistenciaDatos();

    private static ConsolaUI instance;
    private ConsolaUI() {

        try {
            gestionLibro.setLista(persistenciaDatos.leerLibros());
            gestionUsuario.setLista(persistenciaDatos.leerUsuarios());
            gestionPrestamo.setPrestamos(persistenciaDatos.leerPrestamos(gestionLibro,gestionUsuario));
        } catch (ArchivoNoEncontradoException e) {
            consola.mostrarMensaje(e.getMessage());
        }
    }
    public static ConsolaUI getInstance() throws IOException {
        if(instance == null) instance=new ConsolaUI();
        return instance;
    }

    public void iniciar(){
        while(true){

            consola.mostrarMensaje(menuPrincipal.mostrar());
            int opcion= consola.leerOpcion();
            switch(opcion){
                case 1->{
                    consola.mostrarMensaje(menuGestionLibros.mostrar());
                    menuGestionLibros.opciones(gestionLibro);
                    try{
                        persistenciaDatos.guardarLibros(gestionLibro);
                    }catch (ArchivoNoEncontradoException e){
                        consola.mostrarMensaje(e.getMessage());
                    }
                }
                case 2->{
                    consola.mostrarMensaje(menuGestionUsuarios.mostrar());
                    menuGestionUsuarios.opciones(gestionUsuario);
                    try{
                        persistenciaDatos.guardarUsuario(gestionUsuario);
                    }catch (ArchivoNoEncontradoException e){
                        consola.mostrarMensaje(e.getMessage());
                    }
                }
                case 3->{
                    consola.mostrarMensaje(menuGestionPrestamos.mostrar());
                    menuGestionPrestamos.opciones(gestionPrestamo,gestionUsuario,gestionLibro);
                    try{
                        persistenciaDatos.guardarPrestamos(gestionPrestamo);
                    }catch (ArchivoNoEncontradoException e){
                        consola.mostrarMensaje(e.getMessage());
                    }
                }
                case 4->System.exit(0);
                default-> System.out.println("Opcion no valida");
            }
        }
    }
}
