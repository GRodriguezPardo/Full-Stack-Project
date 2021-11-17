package repositorios;

import exceptions.DatosErroneosException;
import exceptions.FaltanDatosException;
import mascotas.Caracteristica;
import persistence.PersistenceId;
import personas.Contacto;

import javax.persistence.*;
import java.util.*;

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
  private final List<String> caracteristicas = new ArrayList<>();

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
   * @param nuevaCaracteristica nombre de la nueva psoible Caracteristica.
   */
  public void agregarPosibleCaracteristica(String nuevaCaracteristica) {
    if (Objects.isNull(nuevaCaracteristica)) {
      throw new FaltanDatosException("No ha aportado caracteristica");
    }
    this.caracteristicas.add(nuevaCaracteristica);
  }

  /**
   * Busca la caracteristica referenciada por el nombre pasado por parametro
   * y devulve un clon de la misma.
   *
   * @param nombre es el nombre de la Caracteristica que se busca.
   * @return retorna un clon de la caracteristica buscada.
   */
  public Caracteristica definirCaracteristica(String nombre) {
    if(this.caracteristicas.contains(nombre)){
      return new Caracteristica();
    } else {
      throw new DatosErroneosException("Caractetristica invalida");
    }
    //Caracteristica caracteristica = caracteristicas.get(nombre);   return caracteristica.clonar();

    //EntityManager..createQuery("select a from PosibleCaracteristica a",PosibleCaracteristica.class).getResultList()...;
    //return new Caracteristica<String>();
  }
}
