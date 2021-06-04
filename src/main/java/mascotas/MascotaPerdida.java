package mascotas;

import exceptions.FaltanDatosException;

import java.awt.*;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa una mascota perdida.
 */
public class MascotaPerdida {
  private final String descripcionEstado;
  private final List<Image> fotos;
  private final Integer latitud;
  private final Integer longitud;

  /**
   * Constructor de la clase.
   *
   * @param _fotos            son las fotos de la mascota perdida, minimo se necesita una.
   * @param descripcionEstado es una descripcion del estado en que se encontro a la mascota.
   * @param latitud           es la posicion en X de la localizacion de la mascota encontrada.
   * @param longitud          es la posicion en Y de la localizacion de la mascota encontrada.
   */
  public MascotaPerdida(String descripcionEstado,
                        List<Image> _fotos,
                        Integer latitud,
                        Integer longitud) {
    this.descripcionEstado = descripcionEstado;
    if (Objects.isNull(_fotos)) {
      throw new FaltanDatosException("Se necesita proveer minimo una foto");
    }
    if (_fotos.isEmpty()) {
      throw new FaltanDatosException("Se necesita proveer minimo una foto");
    }
    this.fotos = _fotos;
    this.latitud = latitud;
    this.longitud = longitud;
  }

  public String getDescripcionEstado() {
    return descripcionEstado;
  }

  public List<Image> getFotos() {
    return fotos;
  }

  public Integer getLatitud() {
    return latitud;
  }

  public Integer getLongitud() {
    return longitud;
  }
}
