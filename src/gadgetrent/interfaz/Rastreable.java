/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gadgetrent.interfaz;

/**
 * Interfaz para artículos que pueden conectarse a la red GPS.
 * Solo Laptop y Drone la implementan. Camara no.
 */
public interface Rastreable {
    
    /**
     * Conecta el artículo a la red de rastreo GPS.
     */
    void conectarRastreo();
    
    /**
     * Retorna la última ubicación conocida del artículo.
     * @return String con coordenadas o descripción de ubicación
     */
    String obtenerUbicacion();
}