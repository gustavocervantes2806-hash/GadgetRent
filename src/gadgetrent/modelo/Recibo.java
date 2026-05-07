/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gadgetrent.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Recibo generado al formalizar un alquiler.
 * COMPOSICIÓN con LineaDetalle: si este Recibo se anula,
 * sus líneas de detalle se eliminan automáticamente.
 */
public class Recibo {

    private String numeroRecibo;
    private LocalDate fecha;
    private Cliente cliente;
    // Composición: las líneas son parte interna del recibo
    private List<LineaDetalle> lineas;

    public Recibo(String numeroRecibo, Cliente cliente) {
        this.numeroRecibo = numeroRecibo;
        this.cliente = cliente;
        this.fecha = LocalDate.now();
        this.lineas = new ArrayList<>();
    }

    /**
     * Agrega una línea de detalle al recibo.
     * @param linea LineaDetalle con descripción y subtotal
     */
    public void agregarLinea(LineaDetalle linea) {
        lineas.add(linea);
    }

    /**
     * Suma todos los subtotales de las líneas.
     * @return Total del recibo
     */
    public double calcularTotal() {
        double total = 0;
        for (LineaDetalle linea : lineas) {
            total += linea.getSubtotal();
        }
        return total;
    }

    /**
     * Anula el recibo: elimina todas sus líneas de detalle.
     * Esto implementa la COMPOSICIÓN: las líneas no existen sin el recibo.
     */
    public void anular() {
        lineas.clear(); // las líneas desaparecen con el recibo
        System.out.println("Recibo " + numeroRecibo + " anulado.");
    }

    public String getNumeroRecibo() { return numeroRecibo; }
    public LocalDate getFecha() { return fecha; }
    public Cliente getCliente() { return cliente; }
    public List<LineaDetalle> getLineas() { return lineas; }

    public String toCSV() {
        // numeroRecibo,documento_cliente,fecha,total
        return numeroRecibo + "," + cliente.getDocumento() 
               + "," + fecha + "," + calcularTotal();
    }

    @Override
    public String toString() {
        return "Recibo #" + numeroRecibo + " | " + cliente.getNombre() 
               + " | Total: $" + calcularTotal();
    }
}