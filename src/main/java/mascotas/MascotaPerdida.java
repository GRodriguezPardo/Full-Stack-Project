package mascotas;

import exceptions.FaltanDatosException;
import persistence.PersistenceId;
import personas.Posicion;

import javax.persistence.CollectionTable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.awt.*;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa una mascota perdida.
 */
@Entity
public class MascotaPerdida extends PersistenceId {
  private final String descripcionEstado;
  @Transient
  private final List<Image> fotos;
  @Embedded
  private final Posicion posicion;

  /**
   * Constructor de la clase.
   *
   * @param _fotos            son las fotos de la mascota perdida, minimo se necesita una.
   * @param descripcionEstado es una descripcion del estado en que se encontro a la mascota.
   * @param posicion          es la posicion en latitud y longitud de la localizacion de la mascota
   *                          encontrada.
   */
  public MascotaPerdida(String descripcionEstado,
                        List<Image> _fotos,
                        Posicion posicion) {
    this.descripcionEstado = descripcionEstado;
    if (Objects.isNull(_fotos)) {
      throw new FaltanDatosException("Se necesita proveer minimo una foto");
    }
    if (_fotos.isEmpty()) {
      throw new FaltanDatosException("Se necesita proveer minimo una foto");
    }
    this.fotos = _fotos;
    this.posicion = posicion;
  }

  public String getDescripcionEstado() {
    return descripcionEstado;
  }

  public List<Image> getFotos() {
    return fotos;
  }

  public Posicion getPosicion() {
    return this.posicion;
  }
}
