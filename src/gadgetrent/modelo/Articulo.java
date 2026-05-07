/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gadgetrent.modelo;

/**
 * Clase base abstracta para todos los artículos del inventario de GadgetRent.
 * No se puede instanciar directamente; siempre se alquila algo específico.
 */
public abstract class Articulo {

    // Atributos protegidos para que las subclases puedan acceder
    protected String codigoInventario;
    protected String marca;
    protected double costoBaseDiario;

    /**
     * Constructor base.
     * @param codigoInventario Código único del artículo en inventario
     * @param marca            Marca del equipo
     * @param costoBaseDiario  Costo base de alquiler por día
     */
    public Articulo(String codigoInventario, String marca, double costoBaseDiario) {
        this.codigoInventario = codigoInventario;
        this.marca = marca;
        this.costoBaseDiario = costoBaseDiario;
    }

    /**
     * Calcula la tarifa de seguro del artículo.
     * Cada subclase implementa su propia fórmula.
     * @return Monto del seguro en pesos
     */
    public abstract double calcularSeguro();

    /**
     * Serializa el artículo a una línea CSV para guardarlo en archivo.
     * @return String con los datos separados por comas
     */
    public abstract String toCSV();

    // --- Getters y Setters ---

    public String getCodigoInventario() { return codigoInventario; }
    public void setCodigoInventario(String codigoInventario) { 
        this.codigoInventario = codigoInventario; 
    }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public double getCostoBaseDiario() { return costoBaseDiario; }
    public void setCostoBaseDiario(double costoBaseDiario) { 
        this.costoBaseDiario = costoBaseDiario; 
    }

    @Override
    public String toString() {
        return "[" + codigoInventario + "] " + marca + " - $" + costoBaseDiario + "/día";
    }
}