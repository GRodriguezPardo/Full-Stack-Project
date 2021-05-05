package primeraIteracion.mascotas;

import java.util.HashMap;

/**
 * Builder de la clase Caracteristica.
 */
public class CaracteristicaBuilder {
  private HashMap<String,Parametro> parametros = new HashMap<>();

  /**
   * Constructor vacio propio de un builder sin variables.
   */
  public CaracteristicaBuilder() {

  }

  /**
   * Agrega un Parametro a la lista de Parametros que tendra la caracteristica.
   *
   * @param nombre
   * @param nuevoParametro
   */
  public void agregarParametro(String nombre, Parametro nuevoParametro){
    this.parametros.put(nombre, nuevoParametro);
  }

  public Caracteristica finalizarCaracteristica() {
    if(parametros.isEmpty()){
      //TODO
    }
    return new Caracteristica(parametros);
  }
}
