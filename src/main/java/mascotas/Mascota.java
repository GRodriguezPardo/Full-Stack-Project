package mascotas;

import exceptions.FaltanDatosException;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Clase que representa una mascota que posee un dueño.
 */
public class Mascota {
  private final Especie especie;
  private final String nombre;
  private final String apodo;
  private final Sexo sexo;
  private final Short edad;
  private final String descripcion;
  private final List<Image> fotos;
  private final Map<String, Caracteristica> caracteristicas;
  private final Tamanio tamanio;

  /**
   * Constructor de una mascota.
   * Realiza los checkeos de los datos minimos que se debe saber de la misma.
   *
   * @param _especie         es la especie de la mascota.
   * @param _nombre          es el nombre de la mascota.
   * @param _apodo           es el apodo de la mascota.
   * @param _edad            es la edad aproximada de la mascota.
   * @param _sexo            es el sexo de la mascota.
   * @param _descripcion     es una descripcion fisica de la mascota.
   * @param _caracteristicas es un hashMap de caracteristicas de la mascota,
   *                         referenciadas por su nombre.
   */
  public Mascota(Especie _especie,
                 String _nombre,
                 String _apodo,
                 Short _edad,
                 Sexo _sexo,
                 String _descripcion,
                 List<Image> _fotos,
                 Map<String, Caracteristica> _caracteristicas,
                 Tamanio tamanio) {
    if (Objects.isNull(_nombre)
            || Objects.isNull(_edad)
            || Objects.isNull(_sexo)
            || Objects.isNull(_descripcion)) {
      throw new FaltanDatosException(
              "Se debe proveer un nombre, una edad aproximada, el sexo, y una descripcion"
      );
    }
    this.especie = _especie;
    this.nombre = _nombre;
    this.apodo = _apodo;
    this.edad = _edad;
    this.sexo = _sexo;
    this.descripcion = _descripcion;
    this.fotos = new ArrayList<>(_fotos);
    this.caracteristicas = new HashMap<>(_caracteristicas);
    this.tamanio = tamanio;
  }

  //TODO: Implementar logica de negocio para evaluar compatibilidad
  public boolean esCompatible(Caracteristica caracteristica) {
    return true;
  }

  public Tamanio getTamanio() {
    return tamanio;
  }

  public Especie getEspecie() {
    return especie;
  }

  public String getNombre() {
    return nombre;
  }

  public String getApodo() {
    return apodo;
  }

  public Short getEdad() {
    return edad;
  }

  public Sexo getSexo() {
    return sexo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public List<Image> getFotos() {
    return fotos;
  }

  public Map<String, Caracteristica> getCaracteristicas() {
    return caracteristicas;
  }
}
