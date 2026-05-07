/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gadgetrent.modelo;

/**
 * Cámara réflex de alquiler.
 * NO implementa Rastreable: es un modelo clásico sin GPS.
 * Seguro con tarifa fija estándar.
 */
public class Camara extends Articulo {

    private double megapixeles;

    public Camara(String codigoInventario, String marca,
                  double costoBaseDiario, double megapixeles) {
        super(codigoInventario, marca, costoBaseDiario);
        this.megapixeles = megapixeles;
    }

    /**
     * Tarifa fija estándar para todas las cámaras: $15.
     * No depende de ningún atributo específico.
     */
    @Override
    public double calcularSeguro() {
        return 15.0;
    }

    @Override
    public String toCSV() {
        // tipo,codigo,marca,costoBaseDiario,megapixeles
        return "Camara," + codigoInventario + "," + marca + "," 
               + costoBaseDiario + "," + megapixeles;
    }

    public double getMegapixeles() { return megapixeles; }
    public void setMegapixeles(double megapixeles) { this.megapixeles = megapixeles; }
}