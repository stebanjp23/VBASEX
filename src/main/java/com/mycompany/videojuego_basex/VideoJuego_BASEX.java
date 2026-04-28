package com.mycompany.videojuego_basex;

import com.mycompany.videojuego_basex.Dao.BaseXVideojuegoDAOImpl;
import com.mycompany.videojuego_basex.Modelo.Videojuego;
import com.mycompany.videojuego_basex.Modelo.Videojuego.*;
import util.ScannerPrompt;

public class VideoJuego_BASEX
{
    static BaseXVideojuegoDAOImpl BX = new BaseXVideojuegoDAOImpl();
    public static void main(String[] args) throws Exception
    {
        while(true)
        {
            System.out.println("------- MENU BASEX ----------");
            System.out.println("1. Listar todos los videojuegos");
            System.out.println("2. Buscar videojuego por ID");
            System.out.println("3. Crear videojuego");
            System.out.println("4. Eliminar videojuego");
            System.out.println("5. Editar videojuego");
            System.out.println("0. Cerrar");

            switch(ScannerPrompt.intPrompt("Selecciona: ", true, false))
            {
                case 1:
                    BX.llistarTots();

                    break;
                case 2:
                    BX.cercarPerId(ScannerPrompt.stringPrompt("ID: ", false).toLowerCase());

                    break;
                case 3:
                    String nuevoId = ScannerPrompt.stringPrompt("ID: ", false);
                    if(!BX.cercarPerId(nuevoId))
                    {
                        System.out.println("Estado:");
                        System.out.println("1. Disponible");
                        System.out.println("2. Agotado");
                        System.out.println("3. Oferta");
                        Estado nuevoEstado = null;
                        switch(ScannerPrompt.intPrompt("Selección: ", true, false))
                        {
                            case 1:
                                nuevoEstado = Estado.DISPONIBLE;
                                break;
                            case 2:
                                nuevoEstado = Estado.AGOTADO;
                                break;
                            case 3:
                                nuevoEstado = Estado.OFERTA;
                                break;
                            default:
                                throw new AssertionError();
                        }

                        String nuevoTitulo = ScannerPrompt.stringPrompt("Titulo: ", false);
                        String nuevoDesarrollador = ScannerPrompt.stringPrompt("Desarrollador: ", false);
                        double nuevoPrecio = ScannerPrompt.doublePrompt("Precio: ", true, false);
                        String[] nuevoPlataformas = ScannerPrompt.stringPrompt("Plataformas (separadas por coma, ejemplo: PLAYSTATION 4, XBOX): ", false).split(",");
                        String nuevoLanzamiento = ScannerPrompt.stringPrompt("Año de lanzamiento: ", false);

                        // Creación del objeto con los datos limpios
                        Videojuego nuevo = new Videojuego
                        (
                            nuevoId, 
                            nuevoEstado,
                            nuevoTitulo,
                            nuevoDesarrollador,
                            nuevoPrecio,
                            nuevoPlataformas,
                            nuevoLanzamiento
                        );

                        BX.inserir(nuevo);
                    }
                    else
                        System.err.println("Este ID ya esixte!");

                    break;
                case 4:
                    BX.eliminar(ScannerPrompt.stringPrompt("ID: ", false));

                    break;
                case 5:
                    String actualizarId = ScannerPrompt.stringPrompt("ID: ", false);

                    if(BX.cercarPerId(actualizarId))
                    {
                        System.out.println("Actualizar:");
                        System.out.println("1. Precio");
                        System.out.println("2. Estado");

                        switch(ScannerPrompt.intPrompt("Selección: ", true, false))
                        {
                            case 1:
                                BX.modificarPreu(actualizarId, ScannerPrompt.doublePrompt("Precio: ", true, false));

                                break;
                            case 2:
                                System.out.println("Estado:");
                                System.out.println("1. Disponible");
                                System.out.println("2. Agotado");
                                System.out.println("3. Oferta");
                                Estado actualizarEstado = null;
                                switch(ScannerPrompt.intPrompt("Selección: ", true, false))
                                {
                                    case 1:
                                        actualizarEstado = Estado.DISPONIBLE;
                                        break;
                                    case 2:
                                        actualizarEstado = Estado.AGOTADO;
                                        break;
                                    case 3:
                                        actualizarEstado = Estado.OFERTA;
                                        break;
                                    default:
                                        throw new AssertionError();
                                }

                                BX.modificarEstado(actualizarId, actualizarEstado);

                                break;
                            default:
                                throw new AssertionError();
                        }
                    }
                    else
                        System.err.println("Este ID no existe!");

                    break;
                case 0:
                    return;
                default:
                    throw new AssertionError();
            }
        }
    }
}
