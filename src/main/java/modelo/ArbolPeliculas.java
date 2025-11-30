package modelo;

public class ArbolPeliculas {
    private NodoArbol raiz;

    public ArbolPeliculas() {
        this.raiz = null;
    }

    private int getAltura(NodoArbol n) {
        if (n == null) return 0;
        return n.altura;
    }

    private int getBalance(NodoArbol n) {
        if (n == null) return 0;
        return getAltura(n.izquierda) - getAltura(n.derecha);
    }

    private NodoArbol rotacionDerecha(NodoArbol y) {
        NodoArbol x = y.izquierda;
        NodoArbol T2 = x.derecha;

        // Rotación
        x.derecha = y;
        y.izquierda = T2;

        // Actualizar alturas
        y.altura = Math.max(getAltura(y.izquierda), getAltura(y.derecha)) + 1;
        x.altura = Math.max(getAltura(x.izquierda), getAltura(x.derecha)) + 1;

        return x;
    }

    private NodoArbol rotacionIzquierda(NodoArbol x) {
        NodoArbol y = x.derecha;
        NodoArbol T2 = y.izquierda;

        y.izquierda = x;
        x.derecha = T2;

        // Actualizar alturas
        x.altura = Math.max(getAltura(x.izquierda), getAltura(x.derecha)) + 1;
        y.altura = Math.max(getAltura(y.izquierda), getAltura(y.derecha)) + 1;

        return y; 
    }

    public void insertar(Pelicula peli) {
        raiz = insertarRec(raiz, peli);
    }

    // --- INSERTAR RECURSIVO (Lógica AVL) ---
    private NodoArbol insertarRec(NodoArbol nodo, Pelicula peli) {
        // 1. Inserción normal de ABB
        if (nodo == null) {
            return new NodoArbol(peli);
        }

        int comparacion = peli.getId().compareTo(nodo.dato.getId());

        if (comparacion < 0) {
            nodo.izquierda = insertarRec(nodo.izquierda, peli);
        } else if (comparacion > 0) {
            nodo.derecha = insertarRec(nodo.derecha, peli);
        } else {
            return nodo; 
        }

        nodo.altura = 1 + Math.max(getAltura(nodo.izquierda), getAltura(nodo.derecha));

        int balance = getBalance(nodo);

        if (balance > 1 && peli.getId().compareTo(nodo.izquierda.dato.getId()) < 0) {
            return rotacionDerecha(nodo);
        }
        if (balance < -1 && peli.getId().compareTo(nodo.derecha.dato.getId()) > 0) {
            return rotacionIzquierda(nodo);
        }
        if (balance > 1 && peli.getId().compareTo(nodo.izquierda.dato.getId()) > 0) {
            nodo.izquierda = rotacionIzquierda(nodo.izquierda);
            return rotacionDerecha(nodo);
        }
        if (balance < -1 && peli.getId().compareTo(nodo.derecha.dato.getId()) < 0) {
            nodo.derecha = rotacionDerecha(nodo.derecha);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    public Pelicula buscar(String id) {
        return buscarRec(raiz, id);
    }

    private Pelicula buscarRec(NodoArbol nodo, String id) {
        if (nodo == null) {
            return null; // No encontrado
        }

        int comparacion = id.compareTo(nodo.dato.getId());

        if (comparacion == 0) {
            return nodo.dato; 
        }
        
        if (comparacion < 0) {
            return buscarRec(nodo.izquierda, id); 
        }
        
        return buscarRec(nodo.derecha, id); 
    }
    
    public void mostrarCatalogo() {
        inOrden(raiz);
    }

    private void inOrden(NodoArbol nodo) {
        if (nodo != null) {
            inOrden(nodo.izquierda);
            System.out.println("ID: " + nodo.dato.getId() + " | " + nodo.dato.getNombre());
            inOrden(nodo.derecha);
        }
    }
    public java.util.ArrayList<Pelicula> obtenerListaOrdenada() {
        java.util.ArrayList<Pelicula> lista = new java.util.ArrayList<>();
        recolectarInOrden(raiz, lista);
        return lista;
    }

    private void recolectarInOrden(NodoArbol nodo, java.util.ArrayList<Pelicula> lista) {
        if (nodo != null) {
            recolectarInOrden(nodo.izquierda, lista);
            lista.add(nodo.dato); 
            recolectarInOrden(nodo.derecha, lista);
        }
    }
}