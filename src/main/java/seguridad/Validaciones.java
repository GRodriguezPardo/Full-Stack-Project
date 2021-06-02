package seguridad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Validaciones {

  public List<Validacion> validaciones = new ArrayList<>();

  public Validaciones() {
    this.agregarValidacion(new ContraseniaAlfanumerica());
    this.agregarValidacion(new ContraseniaFuerte());
    this.agregarValidacion(new ContraseniaLarga());
  }

  public void agregarValidacion(Validacion validacion) {
    this.validaciones.add(validacion);
  }

  public void quitarValidacion(Validacion validacion) {
    this.validaciones.remove(validacion);
  }
  /*El try catch esta solo xq el intelliJ no me obligaba a ponerlo , despues habria
       que encontrar una forma de hacer la validacion sin usar cosas que tengan esa excepcion chequeada , SALVO que esto no moleste*/
  public void hacerValidaciones(String contrasenia) {
    this.validaciones.forEach(v -> {
      try {
        v.validar(contrasenia);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });

  }

}
