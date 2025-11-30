package modelo;

public class MapaAsientos {
    
    NodoDoble inicio; 
    NodoDoble fin; 

    public MapaAsientos() {
        this.inicio = null;
        this.fin = null;
    }
    
    public void agregarAsiento(asientos nuevoAsiento) {
        NodoDoble nuevoNodo = new NodoDoble(nuevoAsiento); 
        
        if (inicio == null) {
            inicio = nuevoNodo;
            fin = nuevoNodo;
        } else {
            fin.derecha = nuevoNodo;
            nuevoNodo.izquierda = fin;
            fin = nuevoNodo;
        }
    }
    
    public void mostrarOcupacion() {
        NodoDoble actual = inicio;
        System.out.println("--- DISPOSICIÓN DE ASIENTOS ---");
        
        while (actual != null) {
            Object datoObjeto = actual.dato;
            asientos asiento = (asientos) datoObjeto; 
            
            String estado = asiento.isEstado() ? "OCUPADO" : "LIBRE";
            String pos = String.valueOf(asiento.getFila()) + asiento.getColumna();
            
            System.out.print("[" + pos + " - " + estado + "] ");
            actual = actual.derecha;
        }
    }
}