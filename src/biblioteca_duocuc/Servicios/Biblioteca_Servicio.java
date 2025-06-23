/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca_duocuc.Servicios;

import biblioteca_duocuc.entidades.Libro;
import biblioteca_duocuc.entidades.Usuario;
import biblioteca_duocuc.excepciones.LibroNoEncontradoException;
import biblioteca_duocuc.excepciones.LibroYaPrestadoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

/**
 *
 * @author ccabe
 */
public class Biblioteca_Servicio {
    private ArrayList<Libro> libros;  //almacenar todos los libros de la biblioteca
    private HashMap<String,Usuario> usuarios; // gestionar usuarios con RUT como clave unica
    private HashSet<String> titulosUnicos; //almacenar titulos unicos de libros para que no se dupliquen
    private TreeSet<String> titulosOrdenados; // almacenar y mantener ordenados alfabeticamente los titulos de los libros
    
    
    public Biblioteca_Servicio() {
        libros = new ArrayList<>();
        
        //Agrego libros manualmente
        libros.add(new Libro("El Alquimista","Paulo Coelho"));
        libros.add(new Libro("Orgullo y prejuicio","Jane Austen"));
        libros.add(new Libro ("Alicia en el pais de las Maravillas","Lewis Carroll"));
        
        
        //Inicializo HashMap para los usuarios
        usuarios = new HashMap<>();
        usuarios.put("12345678-9", new Usuario("Catalina Cabezas", "12345678-9"));
        usuarios.put("12546873-9", new Usuario("Rosa Ponce", "12546873-9"));
        
        
        //inicializo HashSet y TreeSet
        titulosUnicos = new HashSet<>();
        titulosOrdenados = new TreeSet<>();
        
        for (Libro libro : libros) {
            titulosUnicos.add(libro.getTitulo());
            titulosOrdenados.add(libro.getTitulo());
        }
    }
    
    public ArrayList<Libro> getLibro(){
        return libros;
    }
    
    public HashSet<String> getTitulosUnicos() {
        return titulosUnicos;
    }
    
    public TreeSet<String> getTitulosOrdenados () {
        return titulosOrdenados;
    }
    
    public Libro buscarLibro(String titulo) throws LibroNoEncontradoException {
        for (Libro libro : libros) {
            if (libro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                return libro;
            }
         }
         throw new LibroNoEncontradoException("El libro '" + titulo + "' no fue encontrado.");
    
    }
    
    public void prestarLibro(String titulo) throws LibroNoEncontradoException, LibroYaPrestadoException {
        Libro libro = buscarLibro(titulo);
        if (libro.isPrestado()) {
            throw new LibroYaPrestadoException("El libro '" + titulo + "' no estaba prestado.");
        }
        libro.prestar();
    }
    
    public void devolverLibro(String titulo) throws LibroNoEncontradoException {
        Libro libro = buscarLibro(titulo);
        if (!libro.isPrestado()) {
            System.out.println("El libro '" + titulo + "' no estaba prestado.");
        }else {
            libro.devolver();
            System.out.println("Libro devuelto con exito.");
        }
    }
    
    public void agregarLibro(Libro libro) {
        libros.add(libro);
        titulosUnicos.add(libro.getTitulo());
        titulosOrdenados.add(libro.getTitulo());
    }
    
    public Usuario buscarUsuario(String rut) {
        return usuarios.get(rut);
    }
    
    public void agregarUsuario (String rut, String nombre) {
        usuarios.put(rut, new Usuario(nombre, rut));
    }
}
