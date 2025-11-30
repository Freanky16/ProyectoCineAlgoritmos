package modelo;

public class NodoArbol {
    // Usamos TU clase Pelicula existente
    public Pelicula dato; 
    public NodoArbol izquierda;
    public NodoArbol derecha;
    public int altura;

    public NodoArbol(Pelicula pelicula) {
        this.dato = pelicula;
        this.izquierda = null;
        this.derecha = null;
        this.altura = 1; // Altura inicial
    }
}