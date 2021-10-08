package repositorios;

import exceptions.NoHayNingunaAsociasionException;
import mascotas.PublicacionMascotaEnAdopcion;
import mascotas.PublicacionMascotaPerdida;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import personas.Asociacion;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//TODO: Reutilizar la logica de la persistencia en los repos

public class RepositorioDeAsociaciones implements WithGlobalEntityManager {
  private final static RepositorioDeAsociaciones INSTANCE = new RepositorioDeAsociaciones();
  //private final List<Asociacion> asociaciones = new ArrayList<>();

  /**
   * Contructor privado al ser singleton.
   */
  private RepositorioDeAsociaciones() {
  }

  /**
   * Metodo estatico para obtener al singleton.
   *
   * @return retorna al singleton.
   */
  public static RepositorioDeAsociaciones getInstance() {
    return INSTANCE;
  }

  public void agregarAsociacion(Asociacion asociacion) {
    //this.asociaciones.add(asociacion);
    entityManager().persist(asociacion);
  }

  public void removerAsociacion(Asociacion asociacion) {
    //this.asociaciones.remove(asociacion);
    entityManager().remove(asociacion);
  }

  @SuppressWarnings("unchecked")
  public List<PublicacionMascotaPerdida> publicacionesMascotas() {
    /* return this.asociaciones.stream()
            .flatMap(unaAsociacion -> unaAsociacion.publicacionesACargo().stream())
            .collect(Collectors.toList());
     */
    List<Asociacion> asociaciones = this.getAsociaciones();
    return asociaciones.stream()
        .flatMap(unaAsociacion -> unaAsociacion.publicacionesACargo().stream())
        .collect(Collectors.toList());
  }

  public List<PublicacionMascotaPerdida> publicacionesAprobadas() {
    return this.publicacionesSegun(true);
  }

  public List<PublicacionMascotaPerdida> publicacionesNoAprobadas() {
    return this.publicacionesSegun(false);
  }

  private List<PublicacionMascotaPerdida> publicacionesSegun(Boolean valor) {
    return this.publicacionesMascotas()
            .stream().filter(unaPublicacion -> unaPublicacion.aprobado().booleanValue() == valor)
            .collect(Collectors.toList());
  }

  @SuppressWarnings("unchecked")
  public List<Asociacion> getAsociaciones() {
    //return this.asociaciones;
    return entityManager()
        .createQuery("select a from Asociacion a", Asociacion.class).getResultList();
  }

  public Asociacion asociacionMasCercana(PublicacionMascotaPerdida publicacion) {
    if (this.getAsociaciones().isEmpty()) {
      throw new NoHayNingunaAsociasionException("No se pueden agregar publicaciones porque no hay asociasiones");
    }
    return this.getAsociaciones().stream().min(Comparator.comparingInt(asociacion -> asociacion.distanciaA(publicacion.getPosicion()))).get();
  }

  public List<PublicacionMascotaEnAdopcion> publicacionesDeMascotasEnAdopcion() {
    return this.getAsociaciones().stream()
            .flatMap(asociacion -> asociacion.getPublicacionesEnAdopcion().stream())
            .collect(Collectors.toList());
  }

}
