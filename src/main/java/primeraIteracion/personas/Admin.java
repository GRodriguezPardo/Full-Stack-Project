package primeraIteracion.personas;

import primeraIteracion.mascotas.CaracteristicaBuilder;
import primeraIteracion.mascotas.Parametro;
import primeraIteracion.mascotas.PosiblesCaracteristicas;

public class Admin extends Perfil {

  private CaracteristicaBuilder nuevaCaracteristica;

  public Admin(String _clave) {
    super(_clave);
  }

  public void nuevaCaracteristica() {
    this.nuevaCaracteristica = new CaracteristicaBuilder();
  }

  public void agregarParametro(String nombre, Parametro nuevoParametro) {
    this.nuevaCaracteristica.agregarParametro(nombre, nuevoParametro);
  }

  public void finalizarCaracteristica(String nombre) {
    PosiblesCaracteristicas.getInstance()
        .agregarPosibleCaracteristica(nombre, this.nuevaCaracteristica.finalizarCaracteristica());
    this.nuevaCaracteristica = null;
  }
}
