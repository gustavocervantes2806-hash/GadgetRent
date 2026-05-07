/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gadgetrent.datos;

import gadgetrent.modelo.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Maneja la lectura y escritura de articulos.csv.
 * Formato: tipo,codigo,marca,costoBaseDiario,atributoEspecifico
 */
public class ArticuloDAO {

    private static final String ARCHIVO = "articulos.csv";

    /** Lee todos los artículos del CSV y los devuelve como lista polimórfica. */
    public static List<Articulo> leerTodos() {
        List<Articulo> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(",");
                String tipo   = p[0];
                String codigo = p[1];
                String marca  = p[2];
                double costo  = Double.parseDouble(p[3]);

                switch (tipo) {
                    case "Laptop":
                        lista.add(new Laptop(codigo, marca, costo, Integer.parseInt(p[4])));
                        break;
                    case "Drone":
                        lista.add(new Drone(codigo, marca, costo, Double.parseDouble(p[4])));
                        break;
                    case "Camara":
                        lista.add(new Camara(codigo, marca, costo, Double.parseDouble(p[4])));
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo " + ARCHIVO + ": " + e.getMessage());
        }
        return lista;
    }

    /** Sobreescribe el CSV con la lista completa de artículos. */
    public static void guardarTodos(List<Articulo> articulos) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Articulo a : articulos) {
                pw.println(a.toCSV());
            }
        } catch (IOException e) {
            System.err.println("Error guardando " + ARCHIVO + ": " + e.getMessage());
        }
    }
}