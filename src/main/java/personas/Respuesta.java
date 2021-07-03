package personas;

public class Respuesta {
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
}
