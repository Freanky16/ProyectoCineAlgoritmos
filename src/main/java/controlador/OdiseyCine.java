
package controlador;

// Importamos el modelo 'comprobante' para las pruebas
import modelo.comprobante;
import java.util.Date;

public class OdiseyCine { 
    
    public static void main(String[] args) {
        
        System.out.println("--- 1. INICIANDO CONTROLADOR ---");
        ControladorCine controlador = new ControladorCine(); 
        
        // 2. Prueba de la LDE (Asientos)
        controlador.mostrarEstadoSala();
        
        // 3. Prueba de la LES (Ventas)
        System.out.println("\n--- 2. REGISTRANDO 3 VENTAS ---");
        
        // --- Creamos comprobantes para las pruebas ---
        // (Necesitamos guardarlos para poder simular la devolución)
        comprobante venta1 = new comprobante("V101", new Date().toString(), 25.50);
        comprobante venta2 = new comprobante("V102", new Date().toString(), 40.00);
        comprobante venta3 = new comprobante("V103", new Date().toString(), 15.90);

        controlador.registrarNuevaVenta(venta1); // Venta 1
        controlador.registrarNuevaVenta(venta2); // Venta 2
        controlador.registrarNuevaVenta(venta3); // Venta 3
        
        // 4. Reporte de Ganancias (recorrido de la LES)
        double total = controlador.obtenerReporteGanancias();
        System.out.println("\n--- 3. REPORTE DE GANANCIAS (LES) ---");
        System.out.println("Total registrado: S/ " + total); // Debe ser 81.40
        
        // --- 5. PRUEBA DE PILAS (DEVOLUCIONES) ---
        System.out.println("\n--- 4. SIMULANDO DEVOLUCIONES (PUSH) ---");
        // El cliente devuelve la Venta 2
        controlador.registrarDevolucion(venta2);
        // El cliente devuelve la Venta 3
        controlador.registrarDevolucion(venta3);

        // 6. PROCESANDO DEVOLUCIONES (POP)
        // El admin procesa las devoluciones.
        // Debe salir Venta 3 primero (LIFO)
        controlador.gestionarProximaDevolucion(); // Procesa Venta 3
        controlador.gestionarProximaDevolucion(); // Procesa Venta 2
        controlador.gestionarProximaDevolucion(); // (Prueba de pila vacía)
    }
}