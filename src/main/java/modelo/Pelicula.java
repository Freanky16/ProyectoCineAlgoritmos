package modelo;
public class Pelicula {
    private String id;
    private String nombre;
    private double duracion;
    private String clasificacion;
    private String genero;
    private boolean disponible;
    private String rutaImagen;

    public Pelicula(String id, String nombre, double duracion, String clasificacion, String genero, boolean disponible, String rutaImagen) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.clasificacion = clasificacion;
        this.genero = genero;
        this.disponible = disponible;
        this.rutaImagen = rutaImagen;
    }

    public Pelicula() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
    
}
