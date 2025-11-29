package modelo;

public class RegistroVentas {
    
    NodoSimple inicio; 

    public RegistroVentas() {
        this.inicio = null;
    }
    
    public void registrarComprobante(comprobante nuevoComprobante) {
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
            Object datoObjeto = actual.dato;
            comprobante comp = (comprobante) datoObjeto; 
            total += comp.obtenerTotal(); 
            
            actual = actual.siguiente;
        }
        return total;
    }
}