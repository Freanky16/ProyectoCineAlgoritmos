package modelo;

/**
 * Implementa la Pila (Stack) usando el NodoSimple genérico.
 * Almacenará los comprobantes que han sido devueltos.
 */
public class PilaDevoluciones {

    private NodoSimple cima; // 'cima' es el 'inicio' de una Pila

    public PilaDevoluciones() {
        this.cima = null;
    }

    // Método de la Pila: PUSH (Apilar)
    // Agrega un elemento a la cima de la pila
    public void apilar(comprobante compDevuelto) {
        // 1. Crea un nodo genérico con el comprobante
        NodoSimple nuevoNodo = new NodoSimple(compDevuelto);
        
        // 2. El 'siguiente' del nuevo nodo apunta a la cima actual
        nuevoNodo.siguiente = this.cima;
        
        // 3. La nueva 'cima' es el nuevo nodo
        this.cima = nuevoNodo;
        
        System.out.println("[Pila] Devolución " + compDevuelto.getIdVenta() + " apilada.");
    }

    // Método de la Pila: POP (Desapilar)
    // Quita el elemento de la cima y lo devuelve
    public comprobante desapilar() {
        if (estaVacia()) {
            System.out.println("La pila de devoluciones está vacía.");
            return null;
        }
        
        // 1. Guarda el nodo de la cima
        NodoSimple nodoTemporal = this.cima;
        
        // 2. Avanza la 'cima' al siguiente nodo
        this.cima = this.cima.siguiente;
        
        // 3. Devuelve el comprobante (haciendo el CASTING)
        return (comprobante) nodoTemporal.dato;
    }
    
    public boolean estaVacia() {
        return this.cima == null;
    }
}