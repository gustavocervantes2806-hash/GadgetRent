/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gadgetrent.gui;

import gadgetrent.datos.ArticuloDAO;
import gadgetrent.modelo.Articulo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Ventana principal de GadgetRent.
 * Muestra una tabla con todo el catálogo de artículos.
 * El botón "Ver Detalles" abre VentanaDetalle para el artículo seleccionado.
 */
public class VentanaPrincipal extends JFrame {

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private List<Articulo> catalogo;
    private JButton btnVerDetalles;

    public VentanaPrincipal() {
        super("GadgetRent — Catálogo de Equipos");
        inicializarComponentes();
        cargarCatalogo();
        setVisible(true);
    }

    private void inicializarComponentes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720, 460);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Título
        JLabel lblTitulo = new JLabel("  Catálogo de Equipos", JLabel.LEFT);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        add(lblTitulo, BorderLayout.NORTH);

        // Tabla (no editable)
        String[] columnas = {"Código", "Tipo", "Marca", "Costo/Día ($)", "Seguro ($)"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setRowHeight(26);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        JButton btnActualizar = new JButton("Actualizar catálogo");
        btnVerDetalles = new JButton("Ver Detalles");
        btnVerDetalles.setFont(new Font("Arial", Font.BOLD, 12));
        btnVerDetalles.setBackground(new Color(51, 102, 153));
        btnVerDetalles.setForeground(Color.WHITE);
        btnVerDetalles.setEnabled(false); // deshabilitado hasta que el usuario seleccione

        panelBotones.add(btnActualizar);
        panelBotones.add(btnVerDetalles);
        add(panelBotones, BorderLayout.SOUTH);

        // Habilitar "Ver Detalles" al seleccionar una fila
        tabla.getSelectionModel().addListSelectionListener(
            e -> btnVerDetalles.setEnabled(tabla.getSelectedRow() >= 0));

        // Abrir detalle con el botón
        btnVerDetalles.addActionListener(e -> abrirDetalle());

        // Abrir detalle también con doble clic en la tabla
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) abrirDetalle();
            }
        });

        btnActualizar.addActionListener(e -> cargarCatalogo());
    }

    private void cargarCatalogo() {
        catalogo = ArticuloDAO.leerTodos();
        modeloTabla.setRowCount(0);

        for (Articulo a : catalogo) {
            modeloTabla.addRow(new Object[]{
                a.getCodigoInventario(),
                a.getClass().getSimpleName(),
                a.getMarca(),
                String.format("%.2f", a.getCostoBaseDiario()),
                String.format("%.2f", a.calcularSeguro())
            });
        }

        if (catalogo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No se encontró 'articulos.csv' o está vacío.\n" +
                "Asegúrate de que el archivo esté en la carpeta raíz del proyecto.",
                "Catálogo vacío", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void abrirDetalle() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) return;
        new VentanaDetalle(catalogo.get(fila), this);
    }
}