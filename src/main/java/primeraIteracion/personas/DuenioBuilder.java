package primeraIteracion.personas;

import java.util.ArrayList;
import java.util.List;
import primeraIteracion.mascotas.Mascota;

/**
 * Builder de la clase duenio.
 * Hereda de un builder abstracto de la clase persona ya que comparte comportamiento
 * con otros builder de clases que heredan de persona.
 */
public class DuenioBuilder extends PersonaBuilder<Duenio> {
  private List<Mascota> mascotas = new ArrayList<>();

  /**
   * Constructor vacio propio de un builder sin variables.
   */
  public DuenioBuilder(){

  }

  /**
   * Agrega mascotas a la lista de mascotas que tendra el duenio.
   *
   * @param mascota es la mascota a agregar.
   */
  public void agregarMascota(Mascota mascota) {
    this.mascotas.add(mascota);
  }

  /**
   * Definicion del metodo abstracto declarado en su clase padre.
   *
   * @return Retorna el duenio creado con todos los parametros que se definieron.
   */
  @Override
  protected Duenio creacionEspecifica() {
    return new Duenio(
        this.nombreYApellido,
        this.fechaNacimiento,
        this.contactos,
        this.mascotas);
  }
}
