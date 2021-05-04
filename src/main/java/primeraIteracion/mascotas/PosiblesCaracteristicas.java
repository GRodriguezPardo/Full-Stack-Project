package primeraIteracion.mascotas;

import java.util.HashMap;

public class PosiblesCaracteristicas {
  private HashMap<String,Caracteristica> caracteristicas = new HashMap<>();
  private static final PosiblesCaracteristicas INSTANCE = new PosiblesCaracteristicas();


  private PosiblesCaracteristicas() {

  }

  public static PosiblesCaracteristicas getInstance() {
    return INSTANCE;
  }

  public void agregarPosibleCaracteristica(String nombre,Caracteristica nuevaCaracteristica) {
    this.caracteristicas.put(nombre, nuevaCaracteristica);
  }

  public Caracteristica definirCaracteristica(String nombre){
    Caracteristica caracteristica = caracteristicas.get(nombre);
    return caracteristica.clone();
  }
}
