package personas;

public class Interesado {

  private Persona persona;

  public Interesado (Persona persona){
    this.persona=persona;
  }

  public void contactarDuenioPorSugerencia() {
    this.persona.contactarPorSugerenciaSemanal();
  }

}
