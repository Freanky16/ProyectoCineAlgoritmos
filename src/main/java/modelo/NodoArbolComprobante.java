package modelo;

public class NodoArbolComprobante {
    public comprobante dato;
    public NodoArbolComprobante izquierda;
    public NodoArbolComprobante derecha;
    public int altura;

    public NodoArbolComprobante(comprobante comp) {
        this.dato = comp;
        this.izquierda = null;
        this.derecha = null;
        this.altura = 1;
    }
}