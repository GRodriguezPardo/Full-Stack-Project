package primeraIteracion.mascotas;

import java.util.HashMap;

/**
 * Clase singleton que guarda todas las posible caracteristicas que puede
 * tener una mascota, en un hashMap referenciadas por un nombre.
 */
public class PosiblesCaracteristicas {
  private HashMap<String, Caracteristica> caracteristicas = new HashMap<>();
  private static final PosiblesCaracteristicas INSTANCE = new PosiblesCaracteristicas();


  /**
   * Contructor privado al ser singleton.
   */
  private PosiblesCaracteristicas() {

  }

  /**
   * Metodo estatico para obtener al singleton.
   * @return retorna al singleton.
   */
  public static PosiblesCaracteristicas getInstance() {
    return INSTANCE;
  }

  public void agregarPosibleCaracteristica(String nombre, Caracteristica nuevaCaracteristica) {
    this.caracteristicas.put(nombre, nuevaCaracteristica);
  }

  public Caracteristica definirCaracteristica(String nombre) {
    Caracteristica caracteristica = caracteristicas.get(nombre);
    return caracteristica.clone();
  }
}
