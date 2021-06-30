package personas;

import repositorios.RepositorioDePreguntas;

public class Pregunta {
  String cuerpo;
  boolean respuesta;
  boolean visible = true;
  boolean contestada = false; /*Porque el booleano de respuesta siempre estara en false o true (alguno de los 2 es
  valor default al no setear el atributo y no se sabria si se contesto la pregunta o no)*/

  public Pregunta(String pregunta) {
    this.cuerpo = pregunta;
  }

  public String getCuerpo() {
    return this.cuerpo;
  }

  public void responder(Boolean respuesta) {
    this.respuesta = respuesta;
  }

  public void ocultar() {
    this.visible = false;
  }

  public void mostrar() {
    this.visible = true;
  }
}
