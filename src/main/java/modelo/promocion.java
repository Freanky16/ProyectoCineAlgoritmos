package modelo;
public class promocion {
  private String bebida;
  private String canchita;
  private String dulce;

    public promocion(String bebida, String canchita, String dulce) {
        this.bebida = bebida;
        this.canchita = canchita;
        this.dulce = dulce;
    }

    public promocion() {
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public String getCanchita() {
        return canchita;
    }

    public void setCanchita(String canchita) {
        this.canchita = canchita;
    }

    public String getDulce() {
        return dulce;
    }

    public void setDulce(String dulce) {
        this.dulce = dulce;
    }
   
}
