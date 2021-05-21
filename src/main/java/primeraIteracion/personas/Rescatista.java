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
public class Rescatista {
  private Persona persona;
  private MascotaPerdida mascota;
  private LocalDate fecha;

  /**
   * Constructor de la clase.
   * El primer parametro son los datos de la persona encapsulados en el objeto Persona.
   * El ultimo parametro es propio de la clase.
   *
   * @param _persona son los datos de la persona.
   * @param _fecha es la fecha en la que reporto la mascota perdida.
   * @param _mascota es la mascota rescatada por el rescatista.
   */
  public Rescatista (Persona _persona,
                 LocalDate _fecha,
                 MascotaPerdida _mascota) {
     if(Objects.isNull(_persona)) {
      throw new FaltanDatosException("Debe proveer datos de la persona");
    }
    this.persona = _persona;
    if(Objects.isNull(_mascota)
       || Objects.isNull(_fecha)){
      throw new FaltanDatosException(
              "Se debe proveer mascota y fecha"
      );
    }
    this.fecha = _fecha;
    this.mascota = _mascota;

    RepositorioDeRescates.getInstance().agregarRescate(this);
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

  public Persona getPersona() {
    return this.persona;
  }
}
