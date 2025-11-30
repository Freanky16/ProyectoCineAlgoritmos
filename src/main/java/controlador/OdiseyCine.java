package controlador;

import vista.MenuInicio;

public class OdiseyCine { 
    
    public static void main(String[] args) {
        ControladorCine controlador = new ControladorCine(); 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuInicio(controlador).setVisible(true);
            }
        });
    }
}