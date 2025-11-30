package modelo;

public class ArbolComprobantes {
    private NodoArbolComprobante raiz;

    public ArbolComprobantes() {
        this.raiz = null;
    }

    private int getAltura(NodoArbolComprobante n) {
        if (n == null) return 0;
        return n.altura;
    }

    private int getBalance(NodoArbolComprobante n) {
        if (n == null) return 0;
        return getAltura(n.izquierda) - getAltura(n.derecha);
    }

    private NodoArbolComprobante rotacionDerecha(NodoArbolComprobante y) {
        NodoArbolComprobante x = y.izquierda;
        NodoArbolComprobante T2 = x.derecha;
        x.derecha = y;
        y.izquierda = T2;
        y.altura = Math.max(getAltura(y.izquierda), getAltura(y.derecha)) + 1;
        x.altura = Math.max(getAltura(x.izquierda), getAltura(x.derecha)) + 1;
        return x;
    }

    private NodoArbolComprobante rotacionIzquierda(NodoArbolComprobante x) {
        NodoArbolComprobante y = x.derecha;
        NodoArbolComprobante T2 = y.izquierda;
        y.izquierda = x;
        x.derecha = T2;
        x.altura = Math.max(getAltura(x.izquierda), getAltura(x.derecha)) + 1;
        y.altura = Math.max(getAltura(y.izquierda), getAltura(y.derecha)) + 1;
        return y;
    }

    public void insertar(comprobante comp) {
        raiz = insertarRec(raiz, comp);
    }

    private NodoArbolComprobante insertarRec(NodoArbolComprobante nodo, comprobante comp) {
        if (nodo == null) return new NodoArbolComprobante(comp);

        int comparacion = comp.getIdVenta().compareTo(nodo.dato.getIdVenta());

        if (comparacion < 0)
            nodo.izquierda = insertarRec(nodo.izquierda, comp);
        else if (comparacion > 0)
            nodo.derecha = insertarRec(nodo.derecha, comp);
        else
            return nodo;

        nodo.altura = 1 + Math.max(getAltura(nodo.izquierda), getAltura(nodo.derecha));
        int balance = getBalance(nodo);

        if (balance > 1 && comp.getIdVenta().compareTo(nodo.izquierda.dato.getIdVenta()) < 0)
            return rotacionDerecha(nodo);
        if (balance < -1 && comp.getIdVenta().compareTo(nodo.derecha.dato.getIdVenta()) > 0)
            return rotacionIzquierda(nodo);
        if (balance > 1 && comp.getIdVenta().compareTo(nodo.izquierda.dato.getIdVenta()) > 0) {
            nodo.izquierda = rotacionIzquierda(nodo.izquierda);
            return rotacionDerecha(nodo);
        }
        if (balance < -1 && comp.getIdVenta().compareTo(nodo.derecha.dato.getIdVenta()) < 0) {
            nodo.derecha = rotacionDerecha(nodo.derecha);
            return rotacionIzquierda(nodo);
        }
        return nodo;
    }

    public comprobante buscar(String idVenta) {
        return buscarRec(raiz, idVenta);
    }

    private comprobante buscarRec(NodoArbolComprobante nodo, String id) {
        if (nodo == null) return null; 
        int comparacion = id.compareTo(nodo.dato.getIdVenta());

        if (comparacion == 0) return nodo.dato;
        if (comparacion < 0) return buscarRec(nodo.izquierda, id);
        return buscarRec(nodo.derecha, id);
    }
}