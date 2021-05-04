package primeraIteracion.mascotas;

import java.util.HashMap;
import java.util.Objects;

public class Caracteristica {
  HashMap<String,Parametro> parametros = new HashMap<>();

  public Caracteristica(HashMap<String,Parametro> _parametros) {
    this.parametros = new HashMap<String,Parametro>(_parametros);
  }

  public Caracteristica clone() {
    HashMap<String,Parametro> copiaParametros = new HashMap<>();
    parametros.forEach((nombre, parametero) -> copiaParametros.put(nombre,parametero.clone()));
    return new Caracteristica(copiaParametros);
  }

  public Integer cantidadDeParametros() {
    return this.parametros.size();
  }

  public void modificarParametro(String nombre, Object valor){
    this.parametros.get(nombre).setValor(valor);
  }

  public Boolean parametrosRellenados() {
    return this.parametros
        .values()
        .stream()
        .allMatch(parametro -> !Objects.isNull(parametro.getValor()));
  }
}
