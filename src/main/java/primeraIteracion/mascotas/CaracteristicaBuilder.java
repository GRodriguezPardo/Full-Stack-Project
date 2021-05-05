package primeraIteracion.mascotas;

import java.util.HashMap;

/**
 * Builder de la clase Caracteristica.
 */
public class CaracteristicaBuilder {
  private HashMap<String, Parametro> parametros = new HashMap<>();

  /**
   * Constructor vacio propio de un builder sin variables.
   */
  public CaracteristicaBuilder() {

  }

  /**
   * Agrega un Parametro al hash de Parametros que tendra la caracteristica.
   *
   * @param nombre es el nombre o key que referenciara al nuevo parametro.
   * @param nuevoParametro es el parametro a agregar.
   */
  public void agregarParametro(String nombre, Parametro nuevoParametro) {
    this.parametros.put(nombre, nuevoParametro);
  }

  /**
   * Finaliza la creacion de la caracteristica.
   *
   * @return Devuelve la caracteristica creada.
   */
  public Caracteristica finalizarCaracteristica() {
    if (parametros.isEmpty()) {
      //TODO
    }
    return new Caracteristica(parametros);
  }
}
