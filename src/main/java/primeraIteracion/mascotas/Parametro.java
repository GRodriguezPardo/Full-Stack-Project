package primeraIteracion.mascotas;

/**
 * Clase template utilizada para agregarle parametros a una caracteristica.
 * La clase posee un unico atributo cuyo tipo sera el pasado por parametro.
 * Actua como una especie de wrapper que permite generar atributos de cualquier tipo.
 *
 * @param <E> es el tipo de dato que se le dara al valor contenido en la clase.
 */
public class Parametro<E> {
  private E valor = null;

  /**
   * Es el constructor de la clase.
   * Como no se exige un valor inicial queda en null.
   */
  public Parametro() {

  }

  /**
   * Modifica el valor asignado al atributo "valor".
   *
   * @param _valor es el valor que se le asignara.
   */
  public void setValor(E _valor) {
    this.valor = _valor;
  }

  /**
   * Getter deñ atributo "valor"
   *
   * @return retorna el valor del atributo "valor"
   */
  public E getValor() {
    return valor;
  }

  /**
   * Implementacion de un metodo de clonacion de clase.
   *
   * @return retorna una nueva instancia de Parametro pero ya tipada.
   */
  public Parametro clone() {
    return new Parametro<E>();
  }
}
