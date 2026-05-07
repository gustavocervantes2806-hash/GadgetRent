/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gadgetrent.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Cliente registrado en GadgetRent.
 * AGREGACIÓN con Articulo: si el cliente se da de baja,
 * los artículos siguen existiendo en el inventario.
 */
public class Cliente {

    private String nombre;
    private String documento;
    // Agregación: el cliente "tiene" artículos alquilados, pero no los "posee"
    private List<Articulo> articulosAlquilados;

    public Cliente(String nombre, String documento) {
        this.nombre = nombre;
        this.documento = documento;
        this.articulosAlquilados = new ArrayList<>();
    }

    /**
     * Registra que este cliente tiene alquilado un artículo.
     * @param articulo Artículo a agregar a la lista del cliente
     */
    public void alquilarArticulo(Articulo articulo) {
        articulosAlquilados.add(articulo);
    }

    /**
     * Registra la devolución de un artículo.
     * El artículo sigue existiendo en el inventario general.
     * @param articulo Artículo a remover de la lista del cliente
     */
    public void devolverArticulo(Articulo articulo) {
        articulosAlquilados.remove(articulo);
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public List<Articulo> getArticulosAlquilados() { return articulosAlquilados; }

    public String toCSV() {
        // documento,nombre
        return documento + "," + nombre;
    }

    @Override
    public String toString() {
        return nombre + " [" + documento + "]";
    }
}