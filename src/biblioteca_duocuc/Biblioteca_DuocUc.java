/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package biblioteca_duocuc;

import biblioteca_duocuc.Servicios.Biblioteca_Servicio;
import biblioteca_duocuc.entidades.Libro;
import biblioteca_duocuc.excepciones.LibroNoEncontradoException;
import biblioteca_duocuc.excepciones.LibroYaPrestadoException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author ccabe
 */
public class Biblioteca_DuocUc {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca_Servicio servicio = new Biblioteca_Servicio();
        boolean salir = false;
        
        
        while (!salir) {
            System.out.println("\n*** BIENVENIDO A BIBLIOTECA DUOC UC *** \n");
            System.out.println("Selecciona una opcion");
            System.out.println("1.- Listar libros");
            System.out.println("2.- Buscar libro");
            System.out.println("3.- Prestar libro");
            System.out.println("4.- Devolver libro");
            System.out.println("5.- Salir");
            int opcion = 0;
            
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            }catch (InputMismatchException e) {
                System.out.println("Debe ingresar un numero valido.");
                scanner.nextLine();
                continue;
            } 
            
            switch (opcion) {
                case 1:
                    System.out.println("\nLista de Libros:");
                    for (Libro libro : servicio.getLibro()) {
                        System.out.println("- " + libro.getTitulo()+ " | Autor: " + libro.getAutor()+ " | Prestado: " + libro.isPrestado());
                    }
                    break;
                
                case 2:
                    System.out.println("Ingrese el titulo del libro a buscar:");
                    String tituloBuscar = scanner.nextLine();
                    try {
                        Libro libro = servicio.buscarLibro(tituloBuscar);
                        System.out.println("Libro encontrado: " + libro.getTitulo());
                        
                    }catch (LibroNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case 3:
                    System.out.println("Ingrese el titulo del libro a prestar:");
                    String tituloPrestar = scanner.nextLine();
                    try {
                        servicio.prestarLibro(tituloPrestar);
                        System.out.println("Libro prestado con exito.");
         
                    } catch (LibroNoEncontradoException | LibroYaPrestadoException e) {
                        System.out.println(e.getMessage());                     
                    }
                    break;
                    
                case 4:
                    System.out.println("Ingrese el titulo del libro a devolver:");
                    String tituloDevolver = scanner.nextLine();
                    try {
                        servicio.devolverLibro(tituloDevolver);
                    } catch (LibroNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case 5:
                    salir = true;
                    System.out.println("Gracias por usar Biblioteca Duoc Uc.");
                    break;
                    
                default:
                    System.out.println("Opcion invalida. Intentelo nuevamente.");
            }
        }
    scanner.close();               
    }  
}
