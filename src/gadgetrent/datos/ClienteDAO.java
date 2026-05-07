/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gadgetrent.datos;

import gadgetrent.modelo.Cliente;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Maneja la lectura y escritura de clientes.csv.
 * Formato: documento,nombre
 */
public class ClienteDAO {

    private static final String ARCHIVO = "clientes.csv";

    public static List<Cliente> leerTodos() {
        List<Cliente> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(",");
                lista.add(new Cliente(p[1], p[0])); // nombre, documento
            }
        } catch (IOException e) {
            System.err.println("Error leyendo " + ARCHIVO + ": " + e.getMessage());
        }
        return lista;
    }

    public static void guardarTodos(List<Cliente> clientes) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Cliente c : clientes) {
                pw.println(c.toCSV());
            }
        } catch (IOException e) {
            System.err.println("Error guardando " + ARCHIVO + ": " + e.getMessage());
        }
    }
}