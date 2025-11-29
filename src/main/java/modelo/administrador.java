package modelo;

public class administrador { 
    
    private String usuario;
    private String clave;
    private RegistroVentas registroVentas; 
    private PilaDevoluciones pilaDevoluciones;

    public administrador(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
        this.registroVentas = new RegistroVentas(); 
        this.pilaDevoluciones = new PilaDevoluciones();
    }
    
    public void procesarProximaDevolucion() {
        System.out.println("\n--- Procesando Devolución ---");
        comprobante devuelto = this.pilaDevoluciones.desapilar();
        if (devuelto != null) {
            System.out.println("Devolución procesada (POP): " + devuelto.getIdVenta() + " por S/ " + devuelto.obtenerTotal());
        }
    }
    
    //GETTERS

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

    public RegistroVentas getRegistroVentas() {
        return registroVentas;
    }

    public PilaDevoluciones getPilaDevoluciones() {
        return pilaDevoluciones;
    }
}