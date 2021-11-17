package repositorios;

import exceptions.DatosErroneosException;
import exceptions.FaltanDatosException;
import mascotas.Caracteristica;
import mascotas.PosibleCaracteristica;
import persistence.PersistenceId;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
  private final List<PosibleCaracteristica> posiblesCaracteristicas = new ArrayList<>();

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
   * Agrega una nueva posible Caracteristica pasada por parametro a la lista
   * de posibles caracteristicas.
   *
   * @param nuevaCaracteristica nombre de la nueva psoible Caracteristica.
   */
  public void agregarPosibleCaracteristica(String nuevaCaracteristica) {
    if (Objects.isNull(nuevaCaracteristica)) {
      throw new FaltanDatosException("No ha aportado caracteristica");
    }
    if (!this.caracteristicaExistente(nuevaCaracteristica)) {
      this.posiblesCaracteristicas.add(new PosibleCaracteristica(nuevaCaracteristica));
    }

  }

  public boolean caracteristicaExistente(String nombre) {
    return this.posiblesCaracteristicas.stream().filter(c -> c.seLlamaAsi(nombre)).collect(Collectors.toList()).isEmpty();
  }

  /**
   * Busca el nombre de caracteristica pasado por parametro
   * y devulve un clon de la misma.
   *
   * @param nombre es el nombre de la Caracteristica que se busca.
   * @return retorna una instancia la caracteristica buscada.
   */
  public Caracteristica definirCaracteristica(String nombre) {
    if (this.caracteristicaExistente(nombre)) {
      return new Caracteristica();
    } else {
      throw new DatosErroneosException("Caractetristica invalida");
    }
    //Caracteristica caracteristica = caracteristicas.get(nombre);   return caracteristica.clonar();

    //EntityManager..createQuery("select a from PosibleCaracteristica a",PosibleCaracteristica.class).getResultList()...;
    //return new Caracteristica<String>();
  }
}
