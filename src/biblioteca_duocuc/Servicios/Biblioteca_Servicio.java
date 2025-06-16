/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca_duocuc.Servicios;

import biblioteca_duocuc.entidades.Libro;
import biblioteca_duocuc.excepciones.LibroNoEncontradoException;
import biblioteca_duocuc.excepciones.LibroYaPrestadoException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ccabe
 */
public class Biblioteca_Servicio {
    private List<Libro> libros;
    
    public Biblioteca_Servicio() {
        libros = new ArrayList<>();
        
        //Agrego libros manualmente
        libros.add(new Libro("El Alquimista","Paulo Coelho"));
        libros.add(new Libro("Orgullo y prejuicio","Jane Austen"));
        libros.add(new Libro ("Alicia en el pais de las Maravillas","Lewis Carroll"));
    }
    
    public List<Libro> getLibro(){
        return libros;
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
}
