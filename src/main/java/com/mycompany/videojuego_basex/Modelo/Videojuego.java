/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videojuego_basex.Modelo;

/**
 *
 * @author juane
 */
public class Videojuego {
    
    public enum Estado {
        DISPONIBLE,
        AGOTADO,
        OFERTA
    }
    
    private String id;
    private Estado estado;
    private String titulo;
    private String desarrollador;
    private double precio;
    private String[] plataforma;
    private String año;

    public Videojuego() {
    }

    public Videojuego(String id, Estado estado, String titulo, String desarrollador, double precio, String[] plataforma, String año) {
        this.id = id;
        this.estado = estado;
        this.titulo = titulo;
        this.desarrollador = desarrollador;
        this.precio = precio;
        this.plataforma = plataforma;
        this.año = año;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String[] getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String[] plataforma) {
        this.plataforma = plataforma;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    @Override
    public String toString() {
        return "🎮 Videojoc\n"
                + "----------------------------------\n"
                + "ID: " + id + "\n"
                + "Estat: " + estado.toString() + "\n"
                + "Títol: " + titulo + "\n"
                + "Desenvolupador: " + desarrollador + "\n"
                + "Preu: " + precio + " €\n"
                + "Plataformes: " + String.join(", ", plataforma) + "\n"
                + "Any: " + año + "\n";
    }

}
