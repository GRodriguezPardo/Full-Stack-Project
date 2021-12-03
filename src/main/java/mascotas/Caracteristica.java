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
  private String valor = null;
  public String nombre ;

  /**
   * Es el constructor de la clase.
   * Como no se exige un valor inicial queda en null.
   */
  public Caracteristica(String nombre) {
    this.nombre = nombre;
  }

  /**
   * Getter deñ atributo "valor".
   *
   * @return retorna el valor del atributo "valor".
   */
  public String getValor() {
    return valor;
  }

  /**
   * Modifica el valor asignado al atributo "valor".
   *
   * @param valor es el valor que se le asignara.
   */
  public void setValor(String valor) {
    this.valor = valor;
  }

}
