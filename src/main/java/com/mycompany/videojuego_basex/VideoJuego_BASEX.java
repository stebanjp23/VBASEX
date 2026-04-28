package com.mycompany.videojuego_basex;

import com.mycompany.videojuego_basex.Dao.BaseXVideojuegoDAOImpl;
import com.mycompany.videojuego_basex.db.ConexionBaseX;
import org.basex.api.client.LocalSession;
import util.ScannerPrompt;

public class VideoJuego_BASEX {
    
    public static BaseXVideojuegoDAOImpl BX = new BaseXVideojuegoDAOImpl();
    public static void main(String[] args) throws Exception {
        try (LocalSession session = ConexionBaseX.getSession()) {
            menu();
        } finally {
            ConexionBaseX.close();
        }
    }
    
    public static void menu(){
        System.out.println("------- MENU BASEX ----------");
        System.out.println("1. Listar todos los videojuegos");
        System.out.println("2. Buscar videojuego por ID");

        switch (ScannerPrompt.intPrompt("Selecciona: ", true, false)) {
            case 1:
                BX.llistarTots();
                break;
            case 2:
                BX.cercarPerId(ScannerPrompt.stringPrompt("ID: ", false).toLowerCase());
                break;
            default:
                throw new AssertionError();
        }
    }
}
