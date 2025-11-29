package modelo;

public class MapaAsientos {
    
    NodoDoble inicio; // Ahora usa el NodoDoble genérico
    NodoDoble fin; 

    public MapaAsientos() {
        this.inicio = null;
        this.fin = null;
    }
    
    // El método AÚN recibe 'asientos' (específico)
    public void agregarAsiento(asientos nuevoAsiento) {
        // Pero lo guarda en un NodoDoble (genérico)
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
            // ----- ACTUALIZACIÓN CLAVE (CASTING) -----
            // 1. Obtenemos el dato como 'Object'
            Object datoObjeto = actual.dato;
            // 2. Lo convertimos (cast) de nuevo a 'asientos'
            asientos asiento = (asientos) datoObjeto; 
            
            // 3. Usamos los métodos de 'asientos'
            String estado = asiento.isEstado() ? "OCUPADO" : "LIBRE";
            String pos = String.valueOf(asiento.getFila()) + asiento.getColumna();
            // -----------------------------------------
            
            System.out.print("[" + pos + " - " + estado + "] ");
            actual = actual.derecha;
        }
        System.out.println("\n------------------------------");
    }
}