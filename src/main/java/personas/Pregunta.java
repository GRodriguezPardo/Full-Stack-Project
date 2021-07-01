package personas;

public class Pregunta {
  private final String cuerpoDuenio;
  private final String cuerpoInteresado;
  private boolean visible = true;

  public Pregunta(String cuerpoDuenio, String cuerpoInteresado) {
    this.cuerpoDuenio = cuerpoDuenio;
    this.cuerpoInteresado = cuerpoInteresado;
  }

  public String getCuerpoDuenio() {
    return cuerpoDuenio;
  }

  public String getCuerpoInteresado() {
    return cuerpoInteresado;
  }

  public void ocultar() {
    this.visible = false;
  }

  public void mostrar() {
    this.visible = true;
  }

  public boolean isVisible() {
    return this.visible;
  }
}
