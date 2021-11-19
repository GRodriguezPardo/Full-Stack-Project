package repositorios;

import exceptions.DatosErroneosException;
import exceptions.FaltanDatosException;
import mascotas.Caracteristica;
import mascotas.PosibleCaracteristica;
import persistence.PersistenceId;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
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

  @OneToMany
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

  public List<PosibleCaracteristica> getPosiblesCaracteristicas(){
    return this.posiblesCaracteristicas;
  }

  public void eliminarPosibleCaracteristica(String caracteristica) {
    if (Objects.isNull(caracteristica)) {
      throw new FaltanDatosException("No ha aportado caracteristica");
    }
    if (this.caracteristicaExistente(caracteristica)) {
      this.posiblesCaracteristicas.remove(this.hallarPosibleCaracteristica(caracteristica));
    }
  }

  private PosibleCaracteristica hallarPosibleCaracteristica(String nombre) {
    List<PosibleCaracteristica> resultado = this.posiblesCaracteristicas.stream().filter(c -> c.seLlamaAsi(nombre)).collect(Collectors.toList());
    if(resultado.isEmpty()){return null;}
    else {return resultado.get(0);}
  }

  public boolean caracteristicaExistente(String nombre) {
    return this.hallarPosibleCaracteristica(nombre) == null;
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
      return new Caracteristica(nombre);
    } else {
      throw new DatosErroneosException("Caractetristica invalida");
    }

    //EntityManager..createQuery("select a from PosibleCaracteristica a",PosibleCaracteristica.class).getResultList()...;
    //return new Caracteristica<String>();
  }
}
