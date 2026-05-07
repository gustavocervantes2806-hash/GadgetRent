/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gadgetrent;

import gadgetrent.gui.VentanaPrincipal;
import javax.swing.*;

/**
 * Punto de entrada de la aplicación GadgetRent.
 */
public class Main {
    public static void main(String[] args) {
        // Usar el look and feel nativo del sistema operativo
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        // Lanzar la GUI en el hilo de eventos de Swing (buena práctica)
        SwingUtilities.invokeLater(VentanaPrincipal::new);
    }
}