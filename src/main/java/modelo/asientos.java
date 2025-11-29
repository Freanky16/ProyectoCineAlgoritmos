package modelo;
public class asientos {
    private char fila;
    private char columna;
    private boolean estado;

    public asientos(char fila, char columna, boolean estado) {
        this.fila = fila;
        this.columna = columna;
        this.estado = estado;
    }

    public char getFila() {
        return fila;
    }

    public void setFila(char fila) {
        this.fila = fila;
    }

    public char getColumna() {
        return columna;
    }

    public void setColumna(char columna) {
        this.columna = columna;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}
