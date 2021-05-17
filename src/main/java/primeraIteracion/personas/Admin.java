package primeraIteracion.personas;

import primeraIteracion.mascotas.Caracteristica;
import primeraIteracion.mascotas.PosiblesCaracteristicas;

public class Admin extends Perfil {
  public Admin(String usuario, String clave) {
    super(usuario, clave);
  }

  public void nuevaCaracteristica(String nombre, Caracteristica nuevaCaracteristica) {
    PosiblesCaracteristicas.getInstance()
      .agregarPosibleCaracteristica(nombre, nuevaCaracteristica);
  }
}
