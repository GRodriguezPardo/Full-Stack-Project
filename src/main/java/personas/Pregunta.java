package personas;

import repositorios.RepositorioDePreguntas;

public class Pregunta {
  String cuerpo;
  boolean visible = true;

  public Pregunta(String pregunta) {
    this.cuerpo = pregunta;
  }

  public String getCuerpo() {
    return this.cuerpo;
  }

  public void ocultar() {
    this.visible = false;
  }

  public void mostrar() {
    this.visible = true;
  }
}
