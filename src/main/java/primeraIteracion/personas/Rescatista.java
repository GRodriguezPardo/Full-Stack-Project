package primeraIteracion.personas;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import primeraIteracion.exceptions.FaltanDatosException;
import primeraIteracion.mascotas.Mascota;
import primeraIteracion.mascotas.MascotaPerdida;

/**
 * Es una clase que representa una situacion de mascota perdida encontrada.
 * Posee informacion sobre la persona
 */
public class Rescatista extends Persona {
  private MascotaPerdida mascota;
  private LocalDate fecha;

  /**
   * Constructor de la clase.
   * Los primeros 4 parametros son heredeados.
   * El ultimo parametro es propio de la clase.
   *
   * @param _nombreYApellido es el nombre y apellido de la persona.
   * @param _fechaNacimiento es la fecha de nacimiento de la persona.
   * @param _contacto es la lista de formas de contacto que tiene la persona.
   * @param _fecha es la fecha en la que reporto la mascota perdida.
   * @param _mascota es la mascota rescatada por el rescatista.
   */
  public Rescatista (String _nombreYApellido,
                 LocalDate _fechaNacimiento,
                 List<Contacto> _contacto,
                 LocalDate _fecha,
                 MascotaPerdida _mascota) {
    super(_nombreYApellido, _fechaNacimiento, _contacto);
    if(Objects.isNull(_mascota)
       || Objects.isNull(_fecha)){
      throw new FaltanDatosException(
              "Se debe proveer mascota y fecha"
      );
    }
    this.mascota = _mascota;
    this.fecha = _fecha;

    RepositorioDeRescates repositorio = RepositorioDeRescates.getInstance();
    repositorio.agregarRescate(this);
  }

  /**
   * Getter de la fecha.
   * @return retorna la fecha.
   */
  public LocalDate getFecha() {
    return this.fecha;
  }

  /**
   * Getter de la mascota perdida.
   * @return retorna la mascota perdida.
   */
  public MascotaPerdida getMascota() {
    return this.mascota;
  }

  public void agregarAlRepositorio() {
    RepositorioDeRescates a = RepositorioDeRescates.getInstance();
    a.agregarRescate(this);
  }

}
