package modelo;

/**
 * Nodo genérico para estructuras simples (Listas, Pilas, Colas).
 * Usa 'Object' para guardar cualquier tipo de dato.
 */
class NodoSimple {
    
    Object dato; // Guarda cualquier tipo de objeto (comprobante, promocion, etc.)
    NodoSimple siguiente;

    public NodoSimple(Object dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}