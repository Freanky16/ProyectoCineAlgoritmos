package controlador;

/**
 *
 * @author USER
 */
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
    
    // --- MÉTODO CORREGIDO ---
    // (Antes aceptaba 'double monto', ahora acepta el objeto 'comprobante')
    public void registrarNuevaVenta(comprobante nuevaVenta) {
        // (La creación del comprobante ahora se hace en el Main)
        
        // Esta línea ahora funcionará porque getRegistroVentas() existe en el admin
        this.admin.getRegistroVentas().registrarComprobante(nuevaVenta);
    }
    
    public void mostrarEstadoSala() {
        System.out.println("\n--- SOLICITUD DE VISUALIZACIÓN DE ASIENTOS ---");
        this.salaPrincipal.getMapaAsientos().mostrarOcupacion();
    }
    
    public double obtenerReporteGanancias() {
        // Esta línea ahora funcionará
        return this.admin.getRegistroVentas().calcularGananciasTotales();
    }
    
    public administrador getAdmin() {
        return admin;
    }

    public sala getSalaPrincipal() {
        return salaPrincipal;
    }

    // --- MÉTODOS DE LA PILA (YA ESTABAN BIEN) ---
    public void registrarDevolucion(comprobante comprobanteADevolver) {
        // Esta línea ahora funcionará porque getPilaDevoluciones() existe en el admin
        this.admin.getPilaDevoluciones().apilar(comprobanteADevolver);
    }
    
    public void gestionarProximaDevolucion() {
        // Esta línea ahora funcionará porque procesarProximaDevolucion() existe en el admin
        this.admin.procesarProximaDevolucion();
    }
}