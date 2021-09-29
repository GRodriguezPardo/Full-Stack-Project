package personas;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;
@Embeddable
public class Respuesta {
  @Embedded
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
