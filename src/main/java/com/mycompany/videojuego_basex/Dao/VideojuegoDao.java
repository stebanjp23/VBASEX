/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videojuego_basex.Dao;

import com.mycompany.videojuego_basex.Modelo.Videojuego;
import java.util.List;

/**
 *
 * @author juane
 */
public interface VideojuegoDao {
    
    //Listar todo
    void llistarTots();
    //Buscar juego 
    boolean cercarPerId(String id);
    
    //Añadir nuevo juego
    void inserir(Videojuego joc);
    
    //Eliminar juego 
    void eliminar(String id);
    
    //modificar un videojuego
    void modificarPreu(String id, double nouPreu);
}
