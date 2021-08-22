package test;

import datos.SailorMoonDao;
import domain.SailorMoonDTO;
import java.sql.SQLException;
import java.util.Scanner;
import datos.iSailorMoonDao;
import java.util.List;

public class SailorMoonMain {
    public static void main(String[] args) throws SQLException {
        
        iSailorMoonDao sailorDaoJdbc = new SailorMoonDao();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresá la opción a realizar en la DB: ");
        System.out.println("1- Listar los personajes");
        System.out.println("2- Agregar personaje");
        System.out.println("3- Actualizar personaje por planeta");
        System.out.println("4- Eliminar personaje");
        System.out.println("5- Seleccionar personaje por planeta");
        System.out.println("6- Chequear existencia de un personaje");
        int opcion = Integer.parseInt(sc.nextLine());
        
        switch(opcion){
            case 1: 
                System.out.println("Listar los personajes");
                System.out.println("Listado de todos los personajes: ");
                List<SailorMoonDTO> personajes = sailorDaoJdbc.select();
                for(SailorMoonDTO sailor: personajes){
                    System.out.println("\nPersonaje:\n " + sailor);
                }
                break;
            case 2: 
                System.out.println("Agregar personaje");
                System.out.println("Ingresá los datos del nuevo personaje");
                System.out.println("Nombre: ");
                String nombre = sc.nextLine();
                System.out.println("Apellido: ");
                String apellido = sc.nextLine();
                System.out.println("Planeta: ");
                String planeta = sc.nextLine();
                SailorMoonDTO sailor = new SailorMoonDTO(nombre, apellido, planeta);
                
                sailorDaoJdbc.insert(sailor);
                break;
            case 3: 
                System.out.println("Actualizar personaje por planeta");
                System.out.println("Planeta: ");
                planeta = sc.nextLine(); 
                SailorMoonDTO sailorUpdate1 = new SailorMoonDTO(planeta);
                
                if(sailorDaoJdbc.check(sailorUpdate1) == true){
                    System.out.println("Ingresá los demás datos:");
                    System.out.println("Nombre: ");
                    nombre = sc.nextLine();
                    System.out.println("Apellido: ");
                    apellido = sc.nextLine();
                    
                    SailorMoonDTO sailorUpdate2 = new SailorMoonDTO(nombre, apellido, planeta);
                    sailorDaoJdbc.update(sailorUpdate2);
                } else {
                    System.out.println("Planeta no existente o mal ingresado");
                }
                
                break;
            case 4: 
                System.out.println("Eliminar personaje");
                System.out.println("Ingresá el planeta del personaje a eliminar: ");
                
                System.out.println("Planeta: ");
                planeta = sc.nextLine();
                
                SailorMoonDTO sailorDelete = new SailorMoonDTO(planeta);
                
                if(sailorDaoJdbc.check(sailorDelete) == true){
                    sailorDaoJdbc.delete(sailorDelete);
                } else {
                    System.out.println("Planeta no existente o mal ingresado");
                }

                break;
            case 5: 
                System.out.println("Seleccionar personaje por planeta");
                System.out.println("Ingresa el planeta: ");
                planeta = sc.nextLine();
                SailorMoonDTO sailorPlaneta = new SailorMoonDTO(planeta);
                
                if(sailorDaoJdbc.check(sailorPlaneta) == true){
                    System.out.println(sailorDaoJdbc.selectByPlanet(sailorPlaneta));
                } else {
                    System.out.println("Planeta no existente o mal ingresado");
                }
                
                break;
            case 6:
                System.out.println("Chequear existencia de un personaje");
                System.out.println("Ingresa el planeta: ");
                planeta = sc.nextLine();
                
                SailorMoonDTO sailorCheck = new SailorMoonDTO(planeta);
                
                if(sailorDaoJdbc.check(sailorCheck) == true){
                    sailorDaoJdbc.checkPlanet(sailorCheck);
                } else {
                    System.out.println("Planeta no existente o mal ingresado");
                }
                
                break;
            default:
                System.out.println("Oops, opción inválida");
        }
    }
}
