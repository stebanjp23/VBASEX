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
        try(LocalSession lc = ConexionBaseX.getSession())
        {
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
    public boolean cercarPerId(String id_buscar) {
        try(LocalSession lc = ConexionBaseX.getSession())
        {
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
            {
                System.out.println(crearObjecteVideojoc(qc.next()).toString());
                return true;
            }
            else
                System.out.println("No hay resultado con el id ["+id_buscar+"]");
        } catch (IOException e) {
            System.out.println("Conexión no se ha podido establecer");
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void inserir(Videojuego joc) {
        try(LocalSession lc = ConexionBaseX.getSession())
        {
            // construir plataformas dinámicamente
            StringBuilder plataforms = new StringBuilder();
            for (String p : joc.getPlataforma()) {
                plataforms.append("<plataforma>").append(p).append("</plataforma>");
            }

            // construir XML del juego
            String nouJoc
                    = "<joc id='" + joc.getId() + "' estat='" + joc.getEstado() + "'>"
                    + "<titol>" + joc.getTitulo() + "</titol>"
                    + "<desenvolupador>" + joc.getDesarrollador() + "</desenvolupador>"
                    + "<preu>" + joc.getPrecio() + "</preu>"
                    + "<plataformes>" + plataforms.toString() + "</plataformes>"
                    + "<any_llancament>" + joc.getAño() + "</any_llancament>"
                    + "</joc>";

            // query final
            String consulta = "insert node " + nouJoc + " into /cataleg";

            // ejecutar
            lc.query(consulta).execute();

        } catch (IOException e) {
            System.out.println("Conexión no se ha podido establecer");
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void eliminar(String id) {
        try(LocalSession lc = ConexionBaseX.getSession())
        {
            String consulta = "delete node //joc[@id='" + id + "']";

            lc.query(consulta).execute();
            System.out.println("Juego eliminado con exito!");

        } catch (IOException e) {
            System.out.println("Conexión no se ha podido establecer");
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void modificarPreu(String id, double nouPreu) {
        try(LocalSession lc = ConexionBaseX.getSession())
        {
            String consulta = "replace value of node //joc[@id='" + id + "']"
                    +"/preu with '"+nouPreu+"'";

            lc.query(consulta).execute();
            
            System.out.println("Precio actualizado con exito!");

        }
        catch (IOException e)
        {
            System.out.println("Conexión no se ha podido establecer");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modificarEstado(String id, Estado nouEstat) {
        try(LocalSession lc = ConexionBaseX.getSession())
        {
            String consulta = "replace value of node //joc[@id='" + id + "']"
                    +"/@estat with '"+nouEstat.toString().toUpperCase()+"'";

            lc.query(consulta).execute();
            
            System.out.println("Estado actualizado con exito!");

        } catch (IOException e) {
            System.out.println("Conexión no se ha podido establecer");
            System.out.println(e.getMessage());
        }
    }
    
    
    private Videojuego crearObjecteVideojoc(String resultatQuery)
    {
        String[] resultatSeparat = resultatQuery.split("\\|");
        System.out.println(resultatSeparat[1].toUpperCase());
        return new Videojuego(resultatSeparat[0], Estado.valueOf(resultatSeparat[1].toUpperCase()), resultatSeparat[2], resultatSeparat[3], Double.parseDouble(resultatSeparat[4]), resultatSeparat[5].split(","), resultatSeparat[6]);
    }
}
