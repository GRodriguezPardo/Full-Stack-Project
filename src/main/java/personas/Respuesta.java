package personas;

public class Respuesta {
  public Pregunta pregunta ;
  public boolean value ;

  Respuesta(boolean valor , Pregunta pregunta){
    this.value = valor;
    this.pregunta = pregunta;
  }
}
