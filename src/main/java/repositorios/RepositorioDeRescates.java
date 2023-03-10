package repositorios;

import mascotas.MascotaPerdida;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import personas.Asociacion;
import personas.Rescatista;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase singleton cuyo objetivo es guardar los avisos de rescate que se reportan.
 */
public class RepositorioDeRescates implements WithGlobalEntityManager {
  private final static RepositorioDeRescates INSTANCE = new RepositorioDeRescates();
  //private final List<Rescatista> rescates = new ArrayList<>();

  /**
   * Contructor privado al ser singleton.
   */
  private RepositorioDeRescates() {
  }

  /**
   * Metodo estatico para obtener al singleton.
   *
   * @return retorna al singleton.
   */
  public static RepositorioDeRescates getInstance() {
    return INSTANCE;
  }

  /**
   * Permite agregar un nuevo aviso de rescate a la lista de rescates que posee
   * el singleton.
   *
   * @param nuevoRescate es el rescate a ser agregado.
   */
  public void agregarRescate(Rescatista nuevoRescate) {
    entityManager().persist(nuevoRescate);
  }

  /**
   * Filtra la lista de rescates que tiene el singleton como atributo, dejando
   * aquellas cuya diferencia de dias con el momento en que se llamo al metodo
   * sea menor al valor pasado por parametro, y mapea la lista para dejar una lista
   * con las mascotas encontradas en cada rescate.
   *
   * @param dias es el limite de dias que pueden haber trasncurrido desde el momento
   *             del reporte hasta el momento en que se llama al metodo.
   * @return retorna una lista de todas las mascotas perdidas cuyo rescate haya sido
   * reportado en una cantidad de dias menor a la pasada por parametro desde el
   * momento en que se llama al metodo.
   */
  @SuppressWarnings("unchecked")
  public List<MascotaPerdida> mascotasEncontradaEnLosDias(Integer dias) {
    /*return this.rescates
            .stream()
            .filter(unRescate -> unRescate.getFecha().compareTo(LocalDate.now()) < dias)
            .map(Rescatista::getMascota)
            .collect(Collectors.toList());
     */
    List<Rescatista> rescatistas = entityManager()
            .createQuery("from Rescatista where fecha >= :haceXDias")
            .setParameter("haceXDias", LocalDate.now().minusDays((long) dias))
            .getResultList();
    return rescatistas.stream()
            .map(Rescatista::getMascota)
            .collect(Collectors.toList());
  }

  public List<Rescatista> getRescates(){
    return entityManager().createQuery("select a from Rescatista a", Rescatista.class).getResultList();
  }


}
