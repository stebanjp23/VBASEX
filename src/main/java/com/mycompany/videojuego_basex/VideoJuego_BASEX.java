package com.mycompany.videojuego_basex;

import com.mycompany.videojuego_basex.db.ConexionBaseX;
import org.basex.api.client.LocalSession;

public class VideoJuego_BASEX {

    public static void main(String[] args) throws Exception {
        try (LocalSession session = ConexionBaseX.getSession()) {
            System.out.println("Conexion BaseX OK");
            System.out.println(session.execute("INFO DB"));
        } finally {
            ConexionBaseX.close();
        }
    }
}
