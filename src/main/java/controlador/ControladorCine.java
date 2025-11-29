package controlador;

import modelo.administrador;
import modelo.comprobante;
import modelo.sala;
import modelo.asientos;

import java.util.Date;

public class ControladorCine {
    
    private administrador admin;
    private sala salaPrincipal;
    
    public ControladorCine() {
        this.admin = new administrador("admin01", "1234");
        this.salaPrincipal = new sala(50, "S01", true); 
        
        System.out.println("ControladorCine iniciado. Entidades de negocio listas.");
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
}