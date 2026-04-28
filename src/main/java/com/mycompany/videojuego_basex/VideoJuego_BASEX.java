package com.mycompany.videojuego_basex;

import com.mycompany.videojuego_basex.Dao.BaseXVideojuegoDAOImpl;
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
                default:
                    throw new AssertionError();
            }
            
        } finally {
            ConexionBaseX.close();
        }
    }
    
    public static void menu(){
        System.out.println("------- MENU BASEX ----------");
        System.out.println("1. Listar ");
        System.out.println("2. Buscar juego ");
    }
}
