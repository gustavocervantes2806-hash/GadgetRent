/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gadgetrent.modelo;

import gadgetrent.interfaz.Rastreable;

/**
 * Drone de alquiler. Implementa Rastreable porque puede
 * conectarse a la red GPS de GadgetRent.
 * Seguro calculado según rango de vuelo máximo.
 */
public class Drone extends Articulo implements Rastreable {

    private double rangoVueloMaximo; // en kilómetros

    public Drone(String codigoInventario, String marca,
                 double costoBaseDiario, double rangoVueloMaximo) {
        super(codigoInventario, marca, costoBaseDiario);
        this.rangoVueloMaximo = rangoVueloMaximo;
    }

    /**
     * Seguro = rango de vuelo * 2.5 (a mayor rango, mayor riesgo).
     * Ej: 10km rango → seguro = 10 * 2.5 = $25
     */
    @Override
    public double calcularSeguro() {
        return rangoVueloMaximo * 2.5;
    }

    @Override
    public void conectarRastreo() {
        System.out.println("Drone [" + codigoInventario + "] conectado al GPS.");
    }

    @Override
    public String obtenerUbicacion() {
        return "GPS-Drone-" + codigoInventario + ": Ubicación activa";
    }

    @Override
    public String toCSV() {
        // tipo,codigo,marca,costoBaseDiario,rangoVueloMaximo
        return "Drone," + codigoInventario + "," + marca + "," 
               + costoBaseDiario + "," + rangoVueloMaximo;
    }

    public double getRangoVueloMaximo() { return rangoVueloMaximo; }
    public void setRangoVueloMaximo(double rangoVueloMaximo) { 
        this.rangoVueloMaximo = rangoVueloMaximo; 
    }
}