package modelo;

/**
 * Nodo genérico para estructuras dobles (Listas Dobles).
 * Usa 'Object' para guardar cualquier tipo de dato.
 */
class NodoDoble {
    
    NodoDoble izquierda;
    Object dato; // Guarda cualquier tipo de objeto (asientos, etc.)
    NodoDoble derecha;

    public NodoDoble(Object dato) {
        this.dato = dato;
        this.izquierda = null;
        this.derecha = null;
    }
}