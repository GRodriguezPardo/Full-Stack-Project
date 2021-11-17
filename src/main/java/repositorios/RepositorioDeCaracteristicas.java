package repositorios;

import exceptions.FaltanDatosException;
import mascotas.Caracteristica;
import persistence.PersistenceId;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Clase singleton que guarda todas las posible caracteristicas que puede
 * tener una mascota, en un hashMap referenciadas por un nombre.
 */
@Entity
public class RepositorioDeCaracteristicas extends PersistenceId {
  private static final RepositorioDeCaracteristicas INSTANCE = new RepositorioDeCaracteristicas();
  /*@ElementCollection
  @MapKeyColumn(name = "nombre_caracteristica")
  @Column(name = "caracteristica")
   */
  @Transient
  private final Map<String, Caracteristica> caracteristicas = new HashMap<>();


  /**
   * Contructor privado al ser singleton.
   */
  private RepositorioDeCaracteristicas() {

  }

  /**
   * Metodo estatico para obtener al singleton.
   *
   * @return retorna al singleton.
   */
  public static RepositorioDeCaracteristicas getInstance() {
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
    //EntityManager..createQuery("select a from PosibleCaracteristica a",PosibleCaracteristica.class).getResultList()...;
    //return new Caracteristica<String>();
  }
}
