package modelo;

public class NodoArbol {
    public Pelicula dato; 
    public NodoArbol izquierda;
    public NodoArbol derecha;
    public int altura;

    public NodoArbol(Pelicula pelicula) {
        this.dato = pelicula;
        this.izquierda = null;
        this.derecha = null;
        this.altura = 1;
    }
}