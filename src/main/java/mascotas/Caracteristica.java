package mascotas;

import org.hibernate.annotations.Type;
import persistence.PersistenceId;
import javax.persistence.Entity;

//TODO: Separar las posibles caracteristicas de las caracteristicas de cada mascota
// class

/**
 * Clase template utilizada para definir a una caracteristica.
 * La clase posee un unico atributo cuyo tipo sera el pasado por parametro.
 * Actua como una especie de wrapper que permite generar caracteristicas de cualquier tipo.
 *
 * @param <T> es el tipo de dato que se le dara al valor contenido en la clase.
 */
@Entity
public class Caracteristica<T> extends PersistenceId {
  @Type(type = "java.lang.String")
  private T valor = null;

  /**
   * Es el constructor de la clase.
   * Como no se exige un valor inicial queda en null.
   */
  public Caracteristica() {
  }

  /**
   * Getter deñ atributo "valor".
   *
   * @return retorna el valor del atributo "valor".
   */
  public T getValor() {
    return valor;
  }

  /**
   * Modifica el valor asignado al atributo "valor".
   *
   * @param valor es el valor que se le asignara.
   */
  public void setValor(T valor) {
    this.valor = valor;
  }

  /**
   * Implementacion de un metodo de clonacion de clase.
   *
   * @return retorna una nueva instancia de Parametro pero ya tipada.
   */
  public Caracteristica clonar() {
    return new Caracteristica<T>();
  }
}
