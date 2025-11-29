package modelo;

class NodoDoble {
    
    NodoDoble izquierda;
    Object dato;
    NodoDoble derecha;

    public NodoDoble(Object dato) {
        this.dato = dato;
        this.izquierda = null;
        this.derecha = null;
    }
}