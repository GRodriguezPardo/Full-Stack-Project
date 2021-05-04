package primeraIteracion.mascotas;

import java.util.HashMap;
import java.util.List;

public class CaracteristicaBuilder {
  private HashMap<String,Parametro> parametros = new HashMap<>();


  public CaracteristicaBuilder() {

  }


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
