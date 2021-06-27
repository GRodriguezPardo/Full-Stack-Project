package personas;

import repositorios.RepositorioDePreguntas;

public class Pregunta {
  String cuerpo;
  boolean respuesta;
  boolean visible = true;
  boolean contestada = false; /*Porque el booleano de respuesta siempre estara en false o true (alguno de los 2 es
  valor default al no setear el atributo y no se sabria si se contesto la pregunta o no)*/

  public Pregunta(String pregunta, Asociacion asociacion) {
    this.cuerpo = pregunta;
    RepositorioDePreguntas.getInstance().agregarPregunta(this);
    if (asociacion != null) {
      asociacion.agregarPregunta(this);
    }
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
