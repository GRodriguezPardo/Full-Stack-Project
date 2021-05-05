package primeraIteracion.mascotas;

/**
 * Clase que representa una mascota perdida.
 */
public class MascotaPerdida {
  //private List<Foto> fotos;
  private String descripcionEstado;
  private Integer posicionX;
  private Integer posicionY;

  /**
   * Constructor de la clase.
   *
   * @param descripcionEstado es una descripcion del estado en que se encontro a la mascota.
   * @param posicionX es la posicion en X de la localizacion de la mascota encontrada.
   * @param posicionY es la posicion en Y de la localizacion de la mascota encontrada.
   */
  public MascotaPerdida(String descripcionEstado,
                        Integer posicionX,
                        Integer posicionY) {
    this.descripcionEstado = descripcionEstado;
    this.posicionX = posicionX;
    this.posicionY = posicionY;
  }
}
