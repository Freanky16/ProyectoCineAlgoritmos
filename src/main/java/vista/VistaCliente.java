package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import controlador.ControladorCine;
import modelo.Pelicula;

public class VistaCliente extends JFrame {

    private ControladorCine controlador;
    private JTable tablaPeliculas;
    private DefaultTableModel modeloTabla;
    private JTextField txtBuscarId;

    public VistaCliente(ControladorCine ctrl) {
        this.controlador = ctrl;
        configurarVentana();
        iniciarComponentes();
        cargarDatosTabla(); 
    }

    private void configurarVentana() {
        setTitle("Cartelera de Películas");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void iniciarComponentes() {
        JPanel panelSuperior = new JPanel();
        panelSuperior.add(new JLabel("Buscar por ID de Película:"));
        txtBuscarId = new JTextField(10);
        panelSuperior.add(txtBuscarId);
        
        JButton btnBuscar = new JButton("🔍 Buscar (AVL)");
        btnBuscar.setBackground(Color.ORANGE);
        
        btnBuscar.addActionListener(e -> {
            String id = txtBuscarId.getText();
            if (!id.isEmpty()) {
                Pelicula p = controlador.buscarPelicula(id); 
                if (p != null) {
                    JOptionPane.showMessageDialog(this, 
                        "¡PELÍCULA ENCONTRADA!\n\n" +
                        "Nombre: " + p.getNombre() + "\n" +
                        "Género: " + p.getGenero() + "\n" +
                        "Duración: " + p.getDuracion() + " min\n\n" +
                        "Estado: " + (p.isDisponible() ? "Disponible" : "Agotada"));
                } else {
                    JOptionPane.showMessageDialog(this, "Película no encontrada en el catálogo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panelSuperior.add(btnBuscar);
        
        JButton btnRecargar = new JButton("Ver Todas");
        btnRecargar.addActionListener(e -> cargarDatosTabla());
        panelSuperior.add(btnRecargar);
        
        add(panelSuperior, BorderLayout.NORTH);

        String[] columnas = {"ID", "Nombre", "Duración", "Género", "Clasificación"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaPeliculas = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaPeliculas);
        add(scroll, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        JButton btnComprar = new JButton("🎟️ Comprar Entrada");
        JButton btnComida = new JButton("🍿 Comprar Comida");
        
        btnComprar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Módulo de Compra pendiente (Falta lógica de Asientos/Sala)"));
        btnComida.addActionListener(e -> JOptionPane.showMessageDialog(this, "Módulo de Confitería pendiente"));
        
        panelInferior.add(btnComprar);
        panelInferior.add(btnComida);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void cargarDatosTabla() {
        modeloTabla.setRowCount(0); 
        ArrayList<Pelicula> lista = controlador.listarPeliculas();
        
        for (Pelicula p : lista) {
            Object[] fila = {p.getId(), p.getNombre(), p.getDuracion(), p.getGenero(), p.getClasificacion()};
            modeloTabla.addRow(fila);
        }
    }
}