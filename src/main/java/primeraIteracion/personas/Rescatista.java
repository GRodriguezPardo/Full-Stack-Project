package primeraIteracion.personas;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import primeraIteracion.mascotas.MascotaPerdida;

/**
 * Es una clase de tipo de persona pero capaz de poseer una o mas mascotas.
 */
public class Rescatista extends Persona {
  private MascotaPerdida mascota;

  /**
   * Constructor de la clase.
   * Los primeros 3 parametros son heredeados.
   * El ultimo parametro es propio de la clase.
   *
   * @param _nombreYApellido es el nombre y apellido de la persona.
   * @param _fechaNacimiento es la fecha de nacimiento de la persona.
   * @param _contacto es la lista de formas de contacto que tiene la persona.
   * @param _mascota es la mascota rescatada por el rescatista.
   */
  public Rescatista (String _nombreYApellido,
                 LocalDate _fechaNacimiento,
                 List<Contacto> _contacto,
                 MascotaPerdida _mascota) {
    super(_nombreYApellido, _fechaNacimiento, _contacto);
    if(Objects.isNull(_mascota)){
      //TODO
    }
    this.mascota = _mascota;
  }
}
