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
            
            System.out.println("Pulsa 1 para listar: ");
            s = lector.nextInt();
            
            if(s == 1){
                BX.llistarTots();
            }
            
        } finally {
            ConexionBaseX.close();
        }
    }
}
