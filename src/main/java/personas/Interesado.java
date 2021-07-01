package personas;

public class Interesado {

  private final Persona persona;

  public Interesado(Persona persona) {
    this.persona = persona;
  }

  public void contactarDuenioPorSugerencia(Integer cantidad) {
    this.persona.contactarPorSugerenciaSemanal(cantidad);
  }

}
