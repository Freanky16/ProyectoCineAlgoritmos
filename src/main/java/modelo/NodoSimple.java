package modelo;

class NodoSimple {
    
    Object dato; 
    NodoSimple siguiente;

    public NodoSimple(Object dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}