package personas;

import persistence.PersistenceId;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Entity
public class Pregunta extends PersistenceId {
  private final String cuerpoDuenio;
  private final String cuerpoInteresado;

  @ElementCollection
  private final List<String> opciones;
  private boolean visible = true;

  public Pregunta(String cuerpoDuenio, String cuerpoInteresado, List<String> opciones) {
    this.cuerpoDuenio = cuerpoDuenio;
    this.cuerpoInteresado = cuerpoInteresado;
    this.opciones = opciones;

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

  public List<String> getOpciones() {
    return this.opciones;
  }
}
