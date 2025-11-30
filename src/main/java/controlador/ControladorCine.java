package controlador;

import modelo.administrador;
import modelo.comprobante;
import modelo.sala;
import modelo.Pelicula;          
import modelo.ArbolPeliculas;  
import modelo.ArbolComprobantes;

public class ControladorCine {
    
    private administrador admin;
    private sala salaPrincipal;
    
    private ArbolPeliculas catalogoPeliculas;
    private ArbolComprobantes arbolVentas;
    
    public ControladorCine() {
        this.admin = new administrador("admin01", "1234");
        this.salaPrincipal = new sala(50, "S01", true); 
        
        this.catalogoPeliculas = new ArbolPeliculas();
        this.arbolVentas = new ArbolComprobantes(); 
        
        cargarDatosPrueba(); 
        
        System.out.println("ControladorCine iniciado");
    }
    
    private void cargarDatosPrueba() {
        //Caracteristicas de las peliculas
        catalogoPeliculas.insertar(new Pelicula("P001", "Avengers", 180, "PG-13", "Accion", true, "/imagenes/avengers4.jpg"));
        catalogoPeliculas.insertar(new Pelicula("P005", "Avatar 3", 190, "PG-14", "Ciencia Ficcion", true, "/imagenes/avatar3.jpg"));
        catalogoPeliculas.insertar(new Pelicula("P002", "Titanic", 120, "PG-13", "Romance", true, "/imagenes/titanic.jpg"));
        catalogoPeliculas.insertar(new Pelicula("P003", "Mario Bros", 90, "APT", "Animacion", true, "/imagenes/mario.jpg"));
        catalogoPeliculas.insertar(new Pelicula("P004", "John Wick 4", 120, "PG-14 ", "Accion", true, "/imagenes/JohnWick4.jpg"));
        catalogoPeliculas.insertar(new Pelicula("P005", "Frankenstein", 160, "APT", "Accion", true, "/imagenes/frankenstein.jpg"));
        catalogoPeliculas.insertar(new Pelicula("P006", "Zootopia 2", 110, "APT", "Animacion", true, "/imagenes/zootopia2.jpg"));
    }

    //Buscar por id
    public Pelicula buscarPelicula(String id) {
        return catalogoPeliculas.buscar(id);
    }
    
   //buscar por nombre
    public Pelicula buscarPeliculaPorNombre(String nombre) {
        return catalogoPeliculas.buscarPorNombre(nombre);
    }
    
    //Buscar por genero
    public java.util.ArrayList<Pelicula> buscarPeliculasPorGenero(String genero) {
        return catalogoPeliculas.buscarPorGenero(genero);
    }
    
    public java.util.ArrayList<modelo.Pelicula> listarPeliculas() {
        return catalogoPeliculas.obtenerListaOrdenada();
    }
    public void registrarNuevaVenta(comprobante nuevaVenta) {
       this.admin.getRegistroVentas().registrarComprobante(nuevaVenta);
     this.arbolVentas.insertar(nuevaVenta);
       System.out.println("[Sistema] Venta " + nuevaVenta.getIdVenta() + "se va regstrar en lista y avl");
    }
    
    public comprobante buscarComprobanteRapido(String idVenta) {
        return arbolVentas.buscar(idVenta);
    }
    
    public double obtenerReporteGanancias() {
        return this.admin.getRegistroVentas().calcularGananciasTotales();
    }
    
    public void mostrarEstadoSala() {
        this.salaPrincipal.getMapaAsientos().mostrarOcupacion();
    }
    
    public administrador getAdmin() { return admin; }
    public sala getSalaPrincipal() { return salaPrincipal; }
    
    public void registrarDevolucion(comprobante comprobanteADevolver) {
        this.admin.getPilaDevoluciones().apilar(comprobanteADevolver);
    }
    
    public void gestionarProximaDevolucion() {
        this.admin.procesarProximaDevolucion();
    }
}