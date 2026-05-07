/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gadgetrent.gui;

import gadgetrent.modelo.*;
import javax.swing.*;
import java.awt.*;

/**
 * Ventana secundaria que muestra todos los detalles de un artículo.
 * Se abre desde VentanaPrincipal al presionar "Ver Detalles".
 */
public class VentanaDetalle extends JFrame {

    public VentanaDetalle(Articulo articulo, JFrame padre) {
        super("Detalles — " + articulo.getCodigoInventario());
        inicializarComponentes(articulo);
        setLocationRelativeTo(padre);
        setVisible(true);
    }

    private void inicializarComponentes(Articulo articulo) {
        setSize(420, 400);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));

        // Encabezado con tipo de artículo
        JLabel lblTipo = new JLabel("  " + articulo.getClass().getSimpleName(),
                                    JLabel.LEFT);
        lblTipo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTipo.setBorder(BorderFactory.createEmptyBorder(12, 10, 6, 10));
        add(lblTipo, BorderLayout.NORTH);

        // Panel de campos (etiqueta + JTextField de solo lectura)
        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        // Campos comunes
        agregarCampo(panel, "Código de inventario:", articulo.getCodigoInventario());
        agregarCampo(panel, "Marca:", articulo.getMarca());
        agregarCampo(panel, "Costo base diario:", 
                     "$" + String.format("%.2f", articulo.getCostoBaseDiario()));
        agregarCampo(panel, "Seguro calculado:", 
                     "$" + String.format("%.2f", articulo.calcularSeguro()));

        // Campos específicos según tipo
        if (articulo instanceof Laptop) {
            Laptop l = (Laptop) articulo;
            agregarCampo(panel, "RAM:", l.getCantidadRAM() + " GB");
            agregarCampo(panel, "Rastreo GPS:", "✓ Disponible");
        } else if (articulo instanceof Drone) {
            Drone d = (Drone) articulo;
            agregarCampo(panel, "Rango de vuelo máx.:", d.getRangoVueloMaximo() + " km");
            agregarCampo(panel, "Rastreo GPS:", "✓ Disponible");
        } else if (articulo instanceof Camara) {
            Camara c = (Camara) articulo;
            agregarCampo(panel, "Megapíxeles:", c.getMegapixeles() + " MP");
            agregarCampo(panel, "Rastreo GPS:", "✗ No disponible");
        }

        add(panel, BorderLayout.CENTER);

        // Botón cerrar
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBtn.add(btnCerrar);
        add(panelBtn, BorderLayout.SOUTH);
    }

    /** Crea un par etiqueta + campo de texto de solo lectura. */
    private void agregarCampo(JPanel panel, String etiqueta, String valor) {
        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("Arial", Font.BOLD, 12));

        JTextField campo = new JTextField(valor);
        campo.setEditable(false);
        campo.setBackground(new Color(245, 245, 245));
        campo.setFont(new Font("Arial", Font.PLAIN, 12));

        panel.add(lbl);
        panel.add(campo);
    }
}