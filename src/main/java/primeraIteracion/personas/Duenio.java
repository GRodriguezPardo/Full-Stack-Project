package primeraIteracion.personas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import primeraIteracion.exceptions.FaltanDatosException;
import primeraIteracion.mascotas.Mascota;

/**
 * Es una clase de tipo de persona pero capaz de poseer una o mas mascotas.
 */
public class Duenio extends Persona {
  private List<Mascota> mascotas = new ArrayList<>();

  /**
   * Constructor de la clase.
   * Los primeros 3 parametros son heredeados.
   * El ultimo parametro es propio de la clase.
   *
   * @param _nombreYApellido es el nombre y apellido de la persona.
   * @param _fechaNacimiento es la fecha de nacimiento de la persona.
   * @param _contacto es la lista de formas de contacto que tiene la persona.
   * @param _mascotas es lista de mascotas que posee el dueño.
   */
  public Duenio(String _nombreYApellido,
                 LocalDate _fechaNacimiento,
                 List<Contacto> _contacto,
                 List<Mascota> _mascotas) {
    super(_nombreYApellido, _fechaNacimiento, _contacto);

    
    if (Objects.isNull(_mascotas) ) {
      throw new FaltanDatosException(
              "Se debe proveer al menos una mascota"
      );
      if (_mascotas.isEmpty()) {
        throw new FaltanDatosException(
                "Se debe proveer al menos una mascota"
        );
    }
    Collections.copy(mascotas, _mascotas);
  }

  /**
   * Agrega una mascota a la lista de mascotas de la clase
   *
   * @param mascota es la mascota a ser agregada
   */
  public void agregarMascota(Mascota mascota) {
    this.mascotas.add(mascota);
  }

  public void generarUsuario(String usuario, String clave) {
    RepositorioDeUsuarios repositorio = RepositorioDeUsuarios.getInstance();
    repositorio.agregarUsuario(usuario, clave);
  }
}
