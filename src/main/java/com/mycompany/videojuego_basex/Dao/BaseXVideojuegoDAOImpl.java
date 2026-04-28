/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videojuego_basex.Dao;

import com.mycompany.videojuego_basex.Modelo.Videojuego;
import com.mycompany.videojuego_basex.db.ConexionBaseX;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.basex.api.client.ClientQuery;
import org.basex.api.client.LocalQuery;
import org.basex.api.client.LocalSession;

/**
 *
 * @author juane
 */
public class BaseXVideojuegoDAOImpl implements VideojuegoDao {

    @Override
    public void llistarTots() {
        List<Videojuego> lista_juegos = new ArrayList<>();
        try {
            LocalSession lc = ConexionBaseX.getSession();
            String consulta
                    = "for $x in /cataleg/joc "
                    + "return concat("
                    + "$x/@id, '|', "
                    + "$x/@estat, '|', "
                    + "$x/titol, '|', "
                    + "$x/desenvolupador, '|', "
                    + "$x/preu, '|', "
                    + "string-join($x/plataformes/plataforma, ','), '|', "
                    + "$x/any_llancament"
                    + ")";

            LocalQuery qc = lc.query(consulta);
            while (qc.more()) {
                String things;
                things = qc.next();

                String[] separa = things.split("\\|");

                lista_juegos.add(
                        new Videojuego(separa[0], separa[1], separa[2], separa[3], Double.parseDouble(separa[4]), separa[5].split(","), separa[6])
                );
            }

            for (Videojuego lista_juego : lista_juegos) {
                System.out.println(lista_juego.toString());
            }

        } catch (IOException e) {
            System.out.println("Conexión no se ha podido establecer");
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void cercarPerId(String id_buscar) {
        try {
            LocalSession lc = ConexionBaseX.getSession();
            String consulta
                    = "for $x in /cataleg/joc[@id='" + id_buscar + "'] "
                    + "return concat("
                    + "$x/@id, '|', "
                    + "$x/@estat, '|', "
                    + "$x/titol, '|', "
                    + "$x/desenvolupador, '|', "
                    + "$x/preu, '|', "
                    + "string-join($x/plataformes/plataforma, ','), '|', "
                    + "$x/any_llancament "
                    + ")";

            LocalQuery qc = lc.query(consulta);

            if (qc.more()) {
                String things;
                things = qc.next();

                String[] separa = things.split("\\|");

                Videojuego encontrado = new Videojuego(separa[0], separa[1], separa[2], separa[3], Double.parseDouble(separa[4]), separa[5].split(","), separa[6]);
                System.out.println(encontrado.toString());
                
            }else{
                System.out.println("No hay resultado con el id ["+id_buscar+"]");
            }

           

        } catch (IOException e) {
            System.out.println("Conexión no se ha podido establecer");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void inserir(Videojuego joc) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modificarPreu(String id, double nouPreu) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
