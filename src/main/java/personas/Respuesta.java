package personas;

import persistence.PersistenceId;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Respuesta extends PersistenceId {
  @ManyToOne
  public Pregunta pregunta;
  public String respuesta;

  public Respuesta(String respuesta, Pregunta pregunta) {
    this.pregunta = pregunta;
    this.respuesta = respuesta;
  }

  public Pregunta getPregunta() {
    return this.pregunta;
  }

  public String getRespuesta() {
    return this.respuesta;
  }

  public boolean coincide(Respuesta respuesta){
    return Objects.equals(this.getPregunta(), respuesta.getPregunta());
  }
}
