package mascotas;

import exceptions.FaltanDatosException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Clase singleton que guarda todas las posible caracteristicas que puede
 * tener una mascota, en un hashMap referenciadas por un nombre.
 */
public class PosiblesCaracteristicas {
  private static final PosiblesCaracteristicas INSTANCE = new PosiblesCaracteristicas();
  private final Map<String, Caracteristica> caracteristicas = new HashMap<>();


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
   * @param nombre              nombre que referenciara a la nueva Caracteristica.
   * @param nuevaCaracteristica nueva psoible Caracteristica.
   */
  public void agregarPosibleCaracteristica(String nombre, Caracteristica nuevaCaracteristica) {
    if (Objects.isNull(nuevaCaracteristica)) {
      throw new FaltanDatosException("No ha aportado caracteristica");
    }
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
    return caracteristica.clonar();
  }
}
