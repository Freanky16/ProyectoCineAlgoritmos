package vista;

import javax.swing.*;
import java.awt.*;
import controlador.ControladorCine;

public class MenuInicio extends JFrame {

    private ControladorCine controlador;

    public MenuInicio(ControladorCine controlador) {
        this.controlador = controlador;
        configurarVentana();
        iniciarComponentes();
    }

    private void configurarVentana() {
        setTitle("Cine Odisey - Bienvenido");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new GridLayout(3, 1, 10, 10)); 
    }

    private void iniciarComponentes() {
        JLabel lblTitulo = new JLabel("BIENVENIDO A ODISEY CINE", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo);

        JButton btnCliente = new JButton("SOY CLIENTE (Ver Cartelera)");
        btnCliente.setFont(new Font("Arial", Font.BOLD, 14));
        btnCliente.setBackground(new Color(100, 200, 255)); 
        
        btnCliente.addActionListener(e -> {
            VistaCliente vCliente = new VistaCliente(controlador);
            vCliente.setVisible(true);
        });
        add(btnCliente);

        JButton btnAdmin = new JButton("SOY ADMINISTRADOR");
        btnAdmin.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Módulo de Admin pendiente (Le toca a tu compañero)");
        });
        add(btnAdmin);
    }
}