package com.mycompany.videojuego_basex;

import com.mycompany.videojuego_basex.Dao.BaseXVideojuegoDAOImpl;
import com.mycompany.videojuego_basex.Modelo.Videojuego;
import com.mycompany.videojuego_basex.db.ConexionBaseX;
import java.util.Scanner;
import org.basex.api.client.LocalSession;

public class VideoJuego_BASEX {
    
    public static BaseXVideojuegoDAOImpl BX = new BaseXVideojuegoDAOImpl();
    public static void main(String[] args) throws Exception {
        Scanner lector = new Scanner(System.in);
        try (LocalSession session = ConexionBaseX.getSession()) {
            int s;
            
            menu();
            System.out.print("Selecciona: ");
            s = lector.nextInt();
            lector.nextLine();
            
            switch (s) {
                case 1:
                    BX.llistarTots();
                    break;
                case 2:
                    String id;
                    System.out.print("Introduce el id: ");
                    id = lector.nextLine().toLowerCase();
                    BX.cercarPerId(id);
                    break;
                case 3: 
                    String id_nuevo;
                    System.out.println(" ------- CREACIÓN JUEGO -------");
                    System.out.println("");
                    System.out.print("1. Indica el id: ");
                    id_nuevo = lector.nextLine();
                    
                    if(BX.cercarPerId(id_nuevo)){
                        System.out.print("2. Titulo Juego: ");
                        String Titulo = lector.nextLine();
                        System.out.print("3. Desarrollador: ");
                        String Desarrollador = lector.nextLine();
                        System.out.print("4. Precio ");
                        double precio = lector.nextInt();
                        System.out.print("5. Plataformas [PC, PLAY, XBOX]: ");
                        String plataformas = lector.nextLine();
                        System.out.print("6. Año de lanzamiento: ");
                        String año_lanzamiento = lector.nextLine();
                        System.out.print("7. Estado [Disponible o Agotado] ");
                        String estado = lector.nextLine();
                        
                        Videojuego nuevo = new Videojuego(id_nuevo, estado, Titulo, Desarrollador, precio, plataformas.split(","), año_lanzamiento);
                        BX.inserir(nuevo);
                    }else{
                        System.out.println("Id introducido ya existe");
                    }
                case 4: 
                    String id_eliminar;
                    System.out.println("---------- ELIMINAR JUEGO ----------");
                    System.out.println("");
                    System.out.print("Introduce el id del juego a eliminar: ");
                    id_eliminar = lector.nextLine();
                    
                    BX.eliminar(id_eliminar);
                    
                    break;
                case 5:
                    break;
                    
            }
            
        } finally {
            ConexionBaseX.close();
        }
    }
    
    public static void menu(){
        System.out.println("------- MENU BASEX ----------");
        System.out.println("1. Listar ");
        System.out.println("2. Buscar Juego ");
        System.out.println("3. Crear Juego");
        System.out.println("4. Eliminar ");
        System.out.println("5. Editar Juego ");
        System.out.println("0. Cerrar");
    }
}
