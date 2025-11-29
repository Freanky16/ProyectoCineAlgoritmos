package modelo;

public class sala {
    private int capacidad;
    private String ID;
    private boolean estado;
    
    private MapaAsientos mapaAsientos; 

    public sala(int capacidad, String ID, boolean estado) {
        this.capacidad = capacidad;
        this.ID = ID;
        this.estado = estado;
        
        this.mapaAsientos = new MapaAsientos(); 
        
        this.inicializarAsientos(capacidad); 
    }
    
    private void inicializarAsientos(int totalAsientos) {
        int fila = 0;
        int columna = 1;

        for (int i = 0; i < totalAsientos; i++) {
            char filaChar = (char) ('A' + fila); 
            
            asientos nuevoAsiento = new asientos(filaChar, (char) ('0' + columna), false); 
            
            this.mapaAsientos.agregarAsiento(nuevoAsiento);

            columna++;
            if (columna > 10) {
                columna = 1;
                fila++;
            }
        }
        System.out.println("[Sistema] Sala " + ID + " inicializada con " + totalAsientos + " asientos en LDE.");
    }
    
    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public MapaAsientos getMapaAsientos() {
        return mapaAsientos;
    }
}
