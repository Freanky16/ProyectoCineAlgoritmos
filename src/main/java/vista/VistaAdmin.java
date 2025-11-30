package vista;

import controlador.ControladorCine;
import modelo.comprobante;
import javax.swing.*;
import java.awt.*;

public class VistaAdmin extends JFrame {
    
    private ControladorCine controlador;

    public VistaAdmin(ControladorCine ctrl) {
        this.controlador = ctrl;
        setTitle("Panel Administrativo - Odisey Cine");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(new Color(40, 60, 100));
        JLabel lblTitulo = new JLabel("SISTEMA DE GESTIÓN INTERNA");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        panelHeader.add(lblTitulo);
        add(panelHeader, BorderLayout.NORTH);

        //menu
        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 20, 20));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        //gestion de peliculas con la bd
        JButton btnPeliculas = crearBoton("Gestión de Películas", "Administrar cartelera y funciones");
        btnPeliculas.addActionListener(e -> JOptionPane.showMessageDialog(this, "las peliculas van a cargar desde la bd"));

        //seccion de confiteria
        JButton btnConfiteria = crearBoton("Gestión de Confitería", "Inventario de las comidas");
        btnConfiteria.setBackground(new Color(255, 240, 200));
        btnConfiteria.addActionListener(e -> JOptionPane.showMessageDialog(this, "Módulo de Confitería falta"));

        //Reclamos y devoluciones
        JButton btnReclamos = crearBoton("Atención al Cliente / Reclamos", "Buscar ventas y anular");
        btnReclamos.setBackground(new Color(200, 255, 200));
        btnReclamos.addActionListener(e -> abrirModuloReclamos());

        //Registros de ventas
        JButton btnReporte = crearBoton("Reporte Financiero", "Ver ganancias del día");
        btnReporte.addActionListener(e -> {
             double total = controlador.obtenerReporteGanancias();
             JOptionPane.showMessageDialog(this, "Total recaudado en Caja, se puede recorrer con LES " + total);
        });

        panelBotones.add(btnPeliculas);
        panelBotones.add(btnConfiteria);
        panelBotones.add(btnReclamos);
        panelBotones.add(btnReporte);
        
        add(panelBotones, BorderLayout.CENTER);
        
        JButton btnSalir = new JButton("Cerrar Sesión");
        btnSalir.addActionListener(e -> this.dispose());
        add(btnSalir, BorderLayout.SOUTH);
    }
    
    private JButton crearBoton(String titulo, String subtitulo) {
        JButton btn = new JButton("<html><center><b>" + titulo + "</b><br><i>" + subtitulo + "</i></center></html>");
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setFocusPainted(false);
        return btn;
    }

    private void abrirModuloReclamos() {
        String id = JOptionPane.showInputDialog(this, "Ingrese ID del Comprobante(falta)");
        
        if(id != null && !id.isEmpty()) {
            comprobante comp = controlador.buscarComprobanteRapido(id);
            
            if(comp != null) {
                Object[] options = {"OK", "Anular Venta (Devolución)"};
                int choice = JOptionPane.showOptionDialog(this,
                    "DETALLE DE VENTA" +
                    "ID: " + comp.getIdVenta() + "\n" +
                    "Fecha: " + comp.getFecha() + "\n" +
                    "Monto: S/ " + comp.getMontoTotal() + "\n\n" +
                    "Estado: Venta Confirmada",
                    "Gestión de Reclamos",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

                if (choice == 1) {
                    controlador.registrarDevolucion(comp);
                    JOptionPane.showMessageDialog(this, "Venta anulada y enviada de devoluciones");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Comprobante NO encontrado en el sistema.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}