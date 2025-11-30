package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.net.URL;
import controlador.ControladorCine;
import modelo.Pelicula;

public class VistaCliente extends JFrame {

    private ControladorCine controlador;
    private JTable tablaPeliculas;
    private DefaultTableModel modeloTabla;
    private JTextField txtBusqueda;
    private JComboBox<String> cmbTipoBusqueda;
    private JLabel lblImagenPoster;

    public VistaCliente(ControladorCine ctrl) {
        this.controlador = ctrl;
        configurarVentana();
        iniciarComponentes();
        cargarDatosTabla(controlador.listarPeliculas()); 
    }

    private void configurarVentana() {
        setTitle("Cartelera de Películas - Odisey Cine");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
    }

    private void iniciarComponentes() {
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelSuperior.add(new JLabel("Buscar por:"));
        String[] opciones = {"Nombre", "Género"};
        cmbTipoBusqueda = new JComboBox<>(opciones);
        panelSuperior.add(cmbTipoBusqueda);
        
        txtBusqueda = new JTextField(20);
        panelSuperior.add(txtBusqueda);
        
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(255, 200, 100));
        btnBuscar.addActionListener(e -> ejecutarBusqueda());
        panelSuperior.add(btnBuscar);
        
        JButton btnRecargar = new JButton("Ver Todas");
        btnRecargar.addActionListener(e -> {
            txtBusqueda.setText("");
            cargarDatosTabla(controlador.listarPeliculas());
        });
        panelSuperior.add(btnRecargar);
        
        add(panelSuperior, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        String[] columnas = {"ID", "Nombre", "Duración", "Género", "Clasificación"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        
        tablaPeliculas = new JTable(modeloTabla);
        tablaPeliculas.setRowHeight(30);
        tablaPeliculas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tablaPeliculas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaPeliculas.getSelectedRow() != -1) {
                mostrarImagenSeleccionada();
            }
        });

        JScrollPane scroll = new JScrollPane(tablaPeliculas);
        panelCentral.add(scroll, BorderLayout.CENTER);

        JPanel panelImagen = new JPanel(new BorderLayout());
        panelImagen.setPreferredSize(new Dimension(250, 0));
        panelImagen.setBorder(BorderFactory.createTitledBorder("Póster"));
        
        lblImagenPoster = new JLabel("Seleccione una película", SwingConstants.CENTER);
        lblImagenPoster.setVerticalAlignment(SwingConstants.CENTER);
        panelImagen.add(lblImagenPoster, BorderLayout.CENTER);
        
        panelCentral.add(panelImagen, BorderLayout.EAST);
        add(panelCentral, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnComprar = new JButton("Comprar Entrada");
        JButton btnComida = new JButton("Comidas y Bebidas");
        
        btnComprar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Modulo de compra cuando se terminen la logica de asientos y salas"));
        btnComida.addActionListener(e -> JOptionPane.showMessageDialog(this, "Modulo de comidas falta"));
        
        panelInferior.add(btnComprar);
        panelInferior.add(btnComida);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void ejecutarBusqueda() {
        String texto = txtBusqueda.getText().trim();
        if (texto.isEmpty()) return;

        String tipo = (String) cmbTipoBusqueda.getSelectedItem();
        ArrayList<Pelicula> resultados = new ArrayList<>();

        if (tipo.equals("Nombre")) {
            Pelicula p = controlador.buscarPeliculaPorNombre(texto);
            if (p != null) resultados.add(p);
        } 
        else if (tipo.equals("Género")) {
            resultados = controlador.buscarPeliculasPorGenero(texto);
        } 

        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron películas.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
            cargarDatosTabla(controlador.listarPeliculas()); 
        } else {
            cargarDatosTabla(resultados); 
        }
    }

    private void cargarDatosTabla(ArrayList<Pelicula> lista) {
        modeloTabla.setRowCount(0);
        for (Pelicula p : lista) {
            Object[] fila = {p.getId(), p.getNombre(), p.getDuracion() + " min", p.getGenero(), p.getClasificacion()};
            modeloTabla.addRow(fila);
        }
        if (lista.size() == 1) {
            tablaPeliculas.setRowSelectionInterval(0, 0);
        } else {
            lblImagenPoster.setIcon(null);
            lblImagenPoster.setText("Seleccione una película");
        }
    }

    private void mostrarImagenSeleccionada() {
        int fila = tablaPeliculas.getSelectedRow();
        if (fila >= 0) {
            String id = (String) modeloTabla.getValueAt(fila, 0);
            Pelicula p = controlador.buscarPelicula(id);
            
            if (p != null && p.getRutaImagen() != null) {
                try {
                    URL imgURL = getClass().getResource(p.getRutaImagen());
                    if (imgURL != null) {
                        ImageIcon iconOriginal = new ImageIcon(imgURL);
                        Image imagenEscalada = iconOriginal.getImage().getScaledInstance(220, 320, Image.SCALE_SMOOTH);
                        lblImagenPoster.setText("");
                        lblImagenPoster.setIcon(new ImageIcon(imagenEscalada));
                    } else {
                        lblImagenPoster.setIcon(null);
                        lblImagenPoster.setText("Imagen no encontrada");
                    }
                } catch (Exception e) {
                    lblImagenPoster.setIcon(null);
                    lblImagenPoster.setText("Error al cargar");
                }
            } else {
                lblImagenPoster.setIcon(null);
                lblImagenPoster.setText("Sin imagen");
            }
        }
    }
}