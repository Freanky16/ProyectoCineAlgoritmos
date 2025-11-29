package modelo;

public class RegistroVentas {
    
    NodoSimple inicio; // Ahora usa el NodoSimple genérico

    public RegistroVentas() {
        this.inicio = null;
    }
    
    // El método AÚN recibe 'comprobante' (específico)
    public void registrarComprobante(comprobante nuevoComprobante) {
        // Pero lo guarda en un NodoSimple (genérico)
        NodoSimple nuevoNodo = new NodoSimple(nuevoComprobante); 
        
        if (inicio == null) {
            inicio = nuevoNodo;
        } else {
            NodoSimple actual = inicio;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        System.out.println("[Sistema] Venta registrada.");
    }
    
    public double calcularGananciasTotales() { 
        double total = 0.0;
        NodoSimple actual = inicio;
        
        while (actual != null) {
            // ----- ACTUALIZACIÓN CLAVE (CASTING) -----
            // 1. Obtenemos el dato como 'Object'
            Object datoObjeto = actual.dato;
            // 2. Lo convertimos (cast) de nuevo a 'comprobante'
            comprobante comp = (comprobante) datoObjeto; 
            // 3. Usamos el método de 'comprobante'
            total += comp.obtenerTotal(); 
            // -----------------------------------------
            
            actual = actual.siguiente;
        }
        return total;
    }
}