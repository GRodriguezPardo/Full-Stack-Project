package primeraIteracion.mascotas;

import java.util.HashMap;
import java.util.Objects;

/**
 * Clase que representa una caracteristica.
 * La clase Caracteristica conoce una hashMap que relaciona el nombre de un parametro
 * con un parametro que posee la caracteristica.
 */
public class Caracteristica {
  HashMap<String,Parametro> parametros = new HashMap<>();

  /**
   * Constructor de la clase.
   * Pide un hashMap atributos que posee la Caracteristica.
   *
   * @param _parametros es un hashMap que relaciona un nombre de un parametro como
   *                    key, a dicho parametro.
   */
  public Caracteristica(HashMap<String,Parametro> _parametros) {
    this.parametros = new HashMap<String,Parametro>(_parametros);
  }

  /**
   * Crea una version clonada del hashMap Parametros y de los objetos que contiene.
   * Luego crea un clon de si mismo con esa lista clonada.
   *
   * @return Devuelve un clon de si mismo.
   */
  public Caracteristica clonar() {
    HashMap<String,Parametro> copiaParametros = new HashMap<>();
    parametros.forEach((nombre, parametro) -> copiaParametros.put(nombre,parametro.clonar()));
    return new Caracteristica(copiaParametros);
  }

  /**
   * Cuenta la cantidad de Parametros que tiene le Caracteristica.
   *
   * @return retorna la cantidad de parametros.
   */
  public Integer cantidadDeParametros() {
    return this.parametros.size();
  }

  /**
   * Modifica el valor de un Parametro referenciado por su nombre,
   * por el valor pasado por parametro.
   *
   * @param nombre es el nombre del parametro (su referencia o key).
   * @param valor es el valor por el cual sera reemplazado.
   */
  public void modificarParametro(String nombre, Object valor){
    this.parametros.get(nombre).setValor(valor);
  }

  /**
   * Checkea que todos los valores de todos los Parametros hayan sido instanciados.
   *
   * @return retorna el valor de verdad de la comrpobacion.
   */
  public Boolean parametrosRellenados() {
    return this.parametros
        .values()
        .stream()
        .noneMatch(parametro -> Objects.isNull(parametro.getValor()));
  }
}
