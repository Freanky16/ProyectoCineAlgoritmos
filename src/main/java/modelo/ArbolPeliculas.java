package modelo;

import java.util.ArrayList;

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
        x.derecha = y;
        y.izquierda = T2;
        y.altura = Math.max(getAltura(y.izquierda), getAltura(y.derecha)) + 1;
        x.altura = Math.max(getAltura(x.izquierda), getAltura(x.derecha)) + 1;
        return x;
    }

    private NodoArbol rotacionIzquierda(NodoArbol x) {
        NodoArbol y = x.derecha;
        NodoArbol T2 = y.izquierda;
        y.izquierda = x;
        x.derecha = T2;
        x.altura = Math.max(getAltura(x.izquierda), getAltura(x.derecha)) + 1;
        y.altura = Math.max(getAltura(y.izquierda), getAltura(y.derecha)) + 1;
        return y; 
    }

    public void insertar(Pelicula peli) {
        raiz = insertarRec(raiz, peli);
    }

    private NodoArbol insertarRec(NodoArbol nodo, Pelicula peli) {
        if (nodo == null) return new NodoArbol(peli);

        int comparacion = peli.getId().compareTo(nodo.dato.getId());

        if (comparacion < 0)
            nodo.izquierda = insertarRec(nodo.izquierda, peli);
        else if (comparacion > 0)
            nodo.derecha = insertarRec(nodo.derecha, peli);
        else
            return nodo; 

        nodo.altura = 1 + Math.max(getAltura(nodo.izquierda), getAltura(nodo.derecha));
        int balance = getBalance(nodo);

        if (balance > 1 && peli.getId().compareTo(nodo.izquierda.dato.getId()) < 0)
            return rotacionDerecha(nodo);
        if (balance < -1 && peli.getId().compareTo(nodo.derecha.dato.getId()) > 0)
            return rotacionIzquierda(nodo);
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

    //busqueda por id
    public Pelicula buscar(String id) {
        return buscarRec(raiz, id);
    }

    private Pelicula buscarRec(NodoArbol nodo, String id) {
        if (nodo == null) return null;
        int comparacion = id.compareTo(nodo.dato.getId());
        if (comparacion == 0) return nodo.dato; 
        if (comparacion < 0) return buscarRec(nodo.izquierda, id); 
        return buscarRec(nodo.derecha, id); 
    }

    private void inOrden(NodoArbol nodo) {
        if (nodo != null) {
            inOrden(nodo.izquierda);
            System.out.println("ID: " + nodo.dato.getId() + " | " + nodo.dato.getNombre());
            inOrden(nodo.derecha);
        }
    }

    public ArrayList<Pelicula> obtenerListaOrdenada() {
        ArrayList<Pelicula> lista = new ArrayList<>();
        recolectarInOrden(raiz, lista);
        return lista;
    }

    private void recolectarInOrden(NodoArbol nodo, ArrayList<Pelicula> lista) {
        if (nodo != null) {
            recolectarInOrden(nodo.izquierda, lista);
            lista.add(nodo.dato); 
            recolectarInOrden(nodo.derecha, lista);
        }
    }

    //buscar película por nombre
    public Pelicula buscarPorNombre(String nombre) {
        return buscarNombreRec(raiz, nombre);
    }

    private Pelicula buscarNombreRec(NodoArbol nodo, String nombre) {
        if (nodo == null) return null;
        
        if (nodo.dato.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
            return nodo.dato;
        }
        
        Pelicula izq = buscarNombreRec(nodo.izquierda, nombre);
        if (izq != null) return izq;
        
        return buscarNombreRec(nodo.derecha, nombre);
    }

    //buscar peliculas por genero
    public ArrayList<Pelicula> buscarPorGenero(String genero) {
        ArrayList<Pelicula> resultados = new ArrayList<>();
        buscarGeneroRec(raiz, genero, resultados);
        return resultados;
    }

    private void buscarGeneroRec(NodoArbol nodo, String genero, ArrayList<Pelicula> lista) {
        if (nodo != null) {
            buscarGeneroRec(nodo.izquierda, genero, lista);
            if (nodo.dato.getGenero().toLowerCase().contains(genero.toLowerCase())) {
                lista.add(nodo.dato);
            }
            buscarGeneroRec(nodo.derecha, genero, lista);
        }
    }
}