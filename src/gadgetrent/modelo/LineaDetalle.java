/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gadgetrent.modelo;

/**
 * Una línea de detalle dentro de un Recibo.
 * COMPOSICIÓN: no existe fuera de su Recibo.
 * Si el Recibo se destruye, estas líneas desaparecen con él.
 */
public class LineaDetalle {

    private String descripcion;
    private double subtotal;

    public LineaDetalle(String descripcion, double subtotal) {
        this.descripcion = descripcion;
        this.subtotal = subtotal;
    }

    public String getDescripcion() { return descripcion; }
    public double getSubtotal() { return subtotal; }

    public String toCSV() {
        return descripcion + "," + subtotal;
    }

    @Override
    public String toString() {
        return descripcion + " → $" + subtotal;
    }
}