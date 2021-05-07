package primeraIteracion.mascotas;

import java.awt.*;
import java.util.List;
import java.util.Objects;


import primeraIteracion.exceptions.FaltanDatosException;

/**
 * Clase que representa una mascota perdida.
 */
public class MascotaPerdida {
  private String descripcionEstado;
  private List<Image> fotos;
  private Integer posicionX;
  private Integer posicionY;

  /**
   * Constructor de la clase.
   *
   * @param _fotos son las fotos de la mascota perdida, minimo se necesita una.
   * @param descripcionEstado es una descripcion del estado en que se encontro a la mascota.
   * @param posicionX es la posicion en X de la localizacion de la mascota encontrada.
   * @param posicionY es la posicion en Y de la localizacion de la mascota encontrada.
   */
  public MascotaPerdida( String descripcionEstado,
                        List<Image> _fotos,
                        Integer posicionX,
                        Integer posicionY) {
    this.descripcionEstado = descripcionEstado;
    if(Objects.isNull(_fotos)){
      throw new FaltanDatosException("Se necesita proveer minimo una foro");
    }
    if (_fotos.isEmpty()){
      throw new FaltanDatosException("Se necesita proveer minimo una foro");
      }
    this.fotos = _fotos;
    this.posicionX = posicionX;
    this.posicionY = posicionY;
  }
}
