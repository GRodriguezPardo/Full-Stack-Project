package personas;

public class Respuesta {
  public Pregunta pregunta;
  public boolean respuesta;

  public Respuesta(boolean valor, Pregunta pregunta) {
    this.respuesta = valor;
    this.pregunta = pregunta;
  }

  public Pregunta getPregunta() {
    return this.pregunta;
  }

  public boolean getRespuesta() {
    return this.respuesta;
  }
}
