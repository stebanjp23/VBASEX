/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videojuego_basex.db;

import javax.naming.NamingException;
import org.basex.api.client.LocalSession;
import org.basex.core.Context;

/**
 *
 * @author juane
 */
public class ConexiónbaseX {

    private static Context context;

    public static void init() throws Exception {
        if (context == null) {
            context = new Context();

            try (LocalSession session = new LocalSession(context)) {
                session.execute("CREATE DB VideojocsDB cataleg.xml");
            }
        }
    }

    public static LocalSession getSession() throws Exception {
        return new LocalSession(context);
    }

    public static void close() throws NamingException {
        if (context != null) {
            context.close();
        }
    }
}
