package personas;

import exceptions.FaltanDatosException;
import mascotas.Mascota;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Es una clase de tipo de persona pero capaz de poseer una o mas mascotas.
 */
public class Duenio {
  private final Persona persona;
  private final List<Mascota> mascotas = new ArrayList<>();

  /**
   * Constructor de la clase.
   * El parametro son los datos de la persona encapsulados en el objeto Persona.
   *
   * @param _persona son los datos de la persona
   */
  public Duenio(Persona _persona) {
    if (Objects.isNull(_persona)) {
      throw new FaltanDatosException("Debe proveer datos de la persona");
    }
    this.persona = _persona;
  }

  /**
   * Agrega una mascota a la lista de mascotas de la clase
   *
   * @param mascota es la mascota a ser agregada
   */
  public void agregarMascota(Mascota mascota) {
    this.mascotas.add(mascota);
  }

  public Persona getPersona() {
    return this.persona;
  }

  public void contactarDuenioPorMascota() {
    this.persona.contactarPorMascota();
  }

  public void contactarDuenioPorInteresado() {
    this.persona.contactarPorInteresado();
  }

  public Boolean tieneMascota(Mascota mascota) {
    return mascotas.contains(mascota);
  }
}

