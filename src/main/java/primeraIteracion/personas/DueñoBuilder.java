package primeraIteracion.personas;

import java.util.ArrayList;
import java.util.List;
import primeraIteracion.mascotas.Mascota;

/**
 * Builder de la clase duenio.
 * Hereda de un builder abstracto de la clase persona ya que comparte comportamiento
 * con otros builder de clases que heredan de persona.
 */
public class DueñoBuilder extends PersonaBuilder {
  private List<Mascota> mascotas = new ArrayList<>();

  /**
   * Constructor vacio propio de un builder sin variables.
   */
  public DueñoBuilder(){

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
   * Hace los checkeos especificos para la creacion de la clase.
   *
   * @return Retorna el duenio creado con todos los parametros que se definieron.
   */
  @Override
  public Duenio creacionEspecifica() {

    /**sacamos esto? El chequeo ya se hace en Duenio*/
    if (mascotas.isEmpty()) {
      //TODO
    }
    return new Duenio(
        this.nombreYApellido,
        this.fechaNacimiento,
        this.contactos,
        this.mascotas);
  }
}
