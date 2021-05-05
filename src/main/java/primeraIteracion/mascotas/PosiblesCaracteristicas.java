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
   *
   * @return retorna al singleton.
   */
  public static PosiblesCaracteristicas getInstance() {
    return INSTANCE;
  }

  /**
   * Agrega una nueva posible Caracteristica al hashMap de caracteristicas
   * bajo el nombre pasado porparametro.
   *
   * @param nombre nombre que referenciara a la nueva Caracteristica.
   * @param nuevaCaracteristica nueva psoible Caracteristica.
   */
  public void agregarPosibleCaracteristica(String nombre, Caracteristica nuevaCaracteristica) {
    this.caracteristicas.put(nombre, nuevaCaracteristica);
  }

  /**
   * Busca la caracteristica referenciada por el nombre pasado por parametro
   * y devulve un clon de la misma.
   *
   * @param nombre es el nombre de la Caracteristica que se busca.
   * @return retorna un clon de la caracteristica buscada.
   */
  public Caracteristica definirCaracteristica(String nombre) {
    Caracteristica caracteristica = caracteristicas.get(nombre);
    return caracteristica.clone();
  }
}
