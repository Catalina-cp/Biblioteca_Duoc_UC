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
            System.out.println("2.- Registrar nuevo usuario");
            System.out.println("3.- Buscar libro");
            System.out.println("4.- Prestar libro");
            System.out.println("5.- Devolver libro");
            System.out.println("6.- Agregar nuevo libro");
            System.out.println("7.- Salir");
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
                    System.out.println("Ingrese el RUT del nuevo usuario: ");
                    String rutNuevo = scanner.nextLine();
                    
                    if (rutNuevo.length() < 11 || rutNuevo.length() > 12 ){
                            System.out.println("El rut es invalido. Debe llevar puntos y guion");
                            break;
                        }
                    
                    //verifica si el rut nuevo ya existe
                    if(servicio.buscarUsuario(rutNuevo) != null) {
                        System.out.println("El usuario con este RUT ya existe.");
                        break;
                    }
                    
                    System.out.println("Ingrese el nombre del nuevo usuario: ");
                    String nombreNuevo = scanner.nextLine();
                    
                    //Crear nuevo usuario
                    servicio.agregarUsuario(rutNuevo, nombreNuevo);
                    System.out.println("Usuario registrado con exito.");
                    break;
                          
                
                case 3:
                    System.out.println("Ingrese el titulo del libro a buscar:");
                    String tituloBuscar = scanner.nextLine();
                    try {
                        Libro libro = servicio.buscarLibro(tituloBuscar);
                        System.out.println("Libro encontrado: " + libro.getTitulo());
                        
                    }catch (LibroNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case 4:
                    System.out.println("Ingrese el RUT del usuario: ");
                    String rutPrestar = scanner.nextLine();
                    
                    if (rutPrestar.length() < 11 || rutPrestar.length() > 12 ){
                            System.out.println("El rut es invalido. Debe llevar puntos y guion");
                            break;
                        }
                    
                    //verifica si el usuario existe
                    if(servicio.buscarUsuario(rutPrestar) == null) {
                        System.out.println("El usuario no existe. No se puede prestar el libro.");
                        break;
                    }
                    
                    System.out.println("Ingrese el titulo del libro a prestar:");
                    String tituloPrestar = scanner.nextLine();
                    
                       
                    try {
                        servicio.prestarLibro(tituloPrestar);
                        System.out.println("Libro prestado con exito.");
         
                    } catch (LibroNoEncontradoException | LibroYaPrestadoException e) {
                        System.out.println(e.getMessage());                     
                    }
                    break;
                    
                case 5:
                    System.out.println("Ingrese el titulo del libro a devolver:");
                    String tituloDevolver = scanner.nextLine();
                    try {
                        servicio.devolverLibro(tituloDevolver);
                    } catch (LibroNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case 6: 
                    System.out.println("Ingrese el titulo del nuevo libro: ");
                    String tituloNuevo = scanner.nextLine();
                    System.out.println("Ingrese el autor del nuevo libro: ");
                    String autorNuevo = scanner.nextLine();
                    
                    Libro nuevoLibro = new Libro(tituloNuevo, autorNuevo);
                    
                    servicio.agregarLibro(nuevoLibro);
                    
                    System.out.println("Libro agregado con exito.");
                    break;
                    
                case 7:
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
