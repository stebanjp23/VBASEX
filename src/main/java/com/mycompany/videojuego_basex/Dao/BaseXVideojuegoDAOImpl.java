/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videojuego_basex.Dao;

import com.mycompany.videojuego_basex.Modelo.Videojuego;
import com.mycompany.videojuego_basex.Modelo.Videojuego.*;
import com.mycompany.videojuego_basex.db.ConexionBaseX;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
            while(qc.more()) lista_juegos.add(crearObjecteVideojoc(qc.next()));
            for(Videojuego lista_juego : lista_juegos) System.out.println(lista_juego.toString());
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
            if(qc.more())
                System.out.println(crearObjecteVideojoc(qc.next()).toString());
            else
                System.out.println("No hay resultado con el id ["+id_buscar+"]");
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
    
    
    private Videojuego crearObjecteVideojoc(String resultatQuery)
    {
        String[] resultatSeparat = resultatQuery.split("\\|");
        return new Videojuego(resultatSeparat[0], Estado.valueOf(resultatSeparat[1].toUpperCase()), resultatSeparat[2], resultatSeparat[3], Double.parseDouble(resultatSeparat[4]), resultatSeparat[5].split(","), resultatSeparat[6]);
    }
}
