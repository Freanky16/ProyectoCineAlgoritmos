package controlador;

/**
 *
 * @author USER
 */
import modelo.administrador;
import modelo.comprobante;
import modelo.sala;
import modelo.asientos;
import modelo.Pelicula;
import modelo.ArbolPeliculas; 

import java.util.Date;

public class ControladorCine {
    
    private administrador admin;
    private sala salaPrincipal;
    // --- NUEVO ATRIBUTO (Nombre corregido) ---
    private ArbolPeliculas catalogoPeliculas;
    
    public ControladorCine() {
        this.admin = new administrador("admin01", "1234");
        this.salaPrincipal = new sala(50, "S01", true); 
        
        this.catalogoPeliculas = new ArbolPeliculas();
        cargarDatosPrueba(); 
        
        System.out.println("ControladorCine iniciado. Entidades de negocio listas.");
    }
    
    private void cargarDatosPrueba() {
        // Creamos peliculas y las metemos al árbol
        catalogoPeliculas.insertar(new Pelicula("P001", "Avengers", 180, "PG-13", "Accion", true));
        catalogoPeliculas.insertar(new Pelicula("P005", "Avatar 2", 190, "PG", "Ciencia", true));
        catalogoPeliculas.insertar(new Pelicula("P002", "Titanic", 195, "T", "Romance", true));
        catalogoPeliculas.insertar(new Pelicula("P003", "Mario Bros", 90, "APT", "Animacion", true));
    }

    public Pelicula buscarPelicula(String id) {
        return catalogoPeliculas.buscar(id);
    }
    
    public void mostrarCatalogoPeliculas() {
        System.out.println("\n--- CATÁLOGO DE PELÍCULAS (Ordenado por ID - AVL) ---");
        catalogoPeliculas.mostrarCatalogo();
    }

    
    public void registrarNuevaVenta(comprobante nuevaVenta) {
       this.admin.getRegistroVentas().registrarComprobante(nuevaVenta);
    }
    
    public void mostrarEstadoSala() {
        System.out.println("\n--- SOLICITUD DE VISUALIZACIÓN DE ASIENTOS ---");
        this.salaPrincipal.getMapaAsientos().mostrarOcupacion();
    }
    
    public double obtenerReporteGanancias() {
        return this.admin.getRegistroVentas().calcularGananciasTotales();
    }
    
    public administrador getAdmin() {
        return admin;
    }

    public sala getSalaPrincipal() {
        return salaPrincipal;
    }
    
    public void registrarDevolucion(comprobante comprobanteADevolver) {
        this.admin.getPilaDevoluciones().apilar(comprobanteADevolver);
    }
    
    public void gestionarProximaDevolucion() {
        this.admin.procesarProximaDevolucion();
    }
    public java.util.ArrayList<modelo.Pelicula> listarPeliculas() {
        return catalogoPeliculas.obtenerListaOrdenada();
    }
}