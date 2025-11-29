package modelo;

public class PilaDevoluciones {

    private NodoSimple cima;

    public PilaDevoluciones() {
        this.cima = null;
    }

    public void apilar(comprobante compDevuelto) {
        NodoSimple nuevoNodo = new NodoSimple(compDevuelto);
        
        nuevoNodo.siguiente = this.cima;
        
        this.cima = nuevoNodo;
        
        System.out.println("[Pila] Devolución " + compDevuelto.getIdVenta() + " apilada.");
    }
    public comprobante desapilar() {
        if (estaVacia()) {
            System.out.println("La pila de devoluciones está vacía.");
            return null;
        }
        
        NodoSimple nodoTemporal = this.cima;
        
        this.cima = this.cima.siguiente;
        
        return (comprobante) nodoTemporal.dato;
    }
    
    public boolean estaVacia() {
        return this.cima == null;
    }
}