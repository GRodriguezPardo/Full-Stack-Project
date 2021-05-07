package primeraIteracion.personas;

import java.time.LocalDate;
import java.util.Objects;
import primeraIteracion.mascotas.MascotaPerdida;

/**
 * Builder de la clase Rescatista.
 * Hereda de un builder abstracto de la clase persona ya que comparte comportamiento
 * con otros builder de clases que heredan de persona.
 */
public class RescatistaBuilder extends PersonaBuilder {
  private LocalDate fecha;
  private MascotaPerdida mascota;

  /**
   * Constructor vacio propio de un builder sin variables.
   */
  public RescatistaBuilder() {

  }

  /**
   * Setter de la fecha de la mascota encontrada.
   *
   * @param fecha es la fecha a settear.
   */
  public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
  }

  /**
   * Setter de la mascota perdida.
   *
   * @param _mascota es la mascota perdida a settear.
   */
  public void setMascota(MascotaPerdida _mascota) {
    this.mascota = _mascota;
  }

   /**
   * Definicion del metodo abstracto declarado en su clase padre.
   * Hace los checkeos especificos para la creacion de la clase.
   *
   * @return Retorna el duenio creado con todos los parametros que se definieron.
   */
  @Override
  protected Rescatista creacionEspecifica() {

    return new Rescatista(
        this.nombreYApellido,
        this.fechaNacimiento,
        this.contactos,
        this.fecha,
        this.mascota);
  }

}
