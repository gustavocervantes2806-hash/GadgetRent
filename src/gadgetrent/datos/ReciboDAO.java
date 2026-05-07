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
 * Maneja recibos.csv y lineas.csv.
 * recibos.csv:  numeroRecibo,documentoCliente,fecha,total
 * lineas.csv:   numeroRecibo,descripcion,subtotal
 */
public class ReciboDAO {

    private static final String ARCHIVO_RECIBOS = "recibos.csv";
    private static final String ARCHIVO_LINEAS  = "lineas.csv";

    /** Agrega un recibo y sus líneas al final de los CSV (append). */
    public static void guardarRecibo(Recibo recibo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_RECIBOS, true))) {
            pw.println(recibo.toCSV());
        } catch (IOException e) {
            System.err.println("Error guardando " + ARCHIVO_RECIBOS + ": " + e.getMessage());
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_LINEAS, true))) {
            for (LineaDetalle ld : recibo.getLineas()) {
                pw.println(recibo.getNumeroRecibo() + "," + ld.toCSV());
            }
        } catch (IOException e) {
            System.err.println("Error guardando " + ARCHIVO_LINEAS + ": " + e.getMessage());
        }
    }

    /** Lee todos los recibos. Necesita la lista de clientes para reconstruir referencias. */
    public static List<Recibo> leerTodos(List<Cliente> clientes) {
        List<Recibo> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO_RECIBOS);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(",");
                String numero  = p[0];
                String docCli  = p[1];
                Cliente cliente = clientes.stream()
                    .filter(c -> c.getDocumento().equals(docCli))
                    .findFirst().orElse(null);
                if (cliente != null) lista.add(new Recibo(numero, cliente));
            }
        } catch (IOException e) {
            System.err.println("Error leyendo " + ARCHIVO_RECIBOS + ": " + e.getMessage());
        }

        // Cargar líneas de detalle en sus recibos correspondientes
        File archivoLineas = new File(ARCHIVO_LINEAS);
        if (archivoLineas.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivoLineas))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.trim().isEmpty()) continue;
                    String[] p = linea.split(",");
                    lista.stream()
                        .filter(r -> r.getNumeroRecibo().equals(p[0]))
                        .findFirst()
                        .ifPresent(r -> r.agregarLinea(
                            new LineaDetalle(p[1], Double.parseDouble(p[2]))));
                }
            } catch (IOException e) {
                System.err.println("Error leyendo " + ARCHIVO_LINEAS + ": " + e.getMessage());
            }
        }
        return lista;
    }
}