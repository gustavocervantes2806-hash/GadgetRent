/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gadgetrent.modelo;

import gadgetrent.interfaz.Rastreable;

/**
 * Laptop de alquiler. Implementa Rastreable porque puede
 * conectarse a la red GPS de GadgetRent.
 * Seguro calculado según cantidad de RAM.
 */
public class Laptop extends Articulo implements Rastreable {

    private int cantidadRAM; // en GB

    public Laptop(String codigoInventario, String marca, 
                  double costoBaseDiario, int cantidadRAM) {
        super(codigoInventario, marca, costoBaseDiario);
        this.cantidadRAM = cantidadRAM;
    }

    /**
     * Seguro = 5% del costo diario por cada GB de RAM.
     * Ej: 16GB RAM, $100/día → seguro = 16 * 100 * 0.05 = $80
     */
    @Override
    public double calcularSeguro() {
        return cantidadRAM * costoBaseDiario * 0.05;
    }

    @Override
    public void conectarRastreo() {
        System.out.println("Laptop [" + codigoInventario + "] conectada al GPS.");
    }

    @Override
    public String obtenerUbicacion() {
        return "GPS-Laptop-" + codigoInventario + ": Ubicación activa";
    }

    @Override
    public String toCSV() {
        // tipo,codigo,marca,costoBaseDiario,cantidadRAM
        return "Laptop," + codigoInventario + "," + marca + "," 
               + costoBaseDiario + "," + cantidadRAM;
    }

    public int getCantidadRAM() { return cantidadRAM; }
    public void setCantidadRAM(int cantidadRAM) { this.cantidadRAM = cantidadRAM; }
}