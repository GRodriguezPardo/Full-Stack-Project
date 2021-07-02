package repositorios;

import exceptions.NoHayNingunaAsociasionException;
import mascotas.PublicacionMascotaEnAdopcion;
import mascotas.PublicacionMascotaPerdida;
import personas.Asociacion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioDeAsociaciones {
  private final static RepositorioDeAsociaciones INSTANCE = new RepositorioDeAsociaciones();
  private final List<Asociacion> asociaciones = new ArrayList<>();

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
    this.asociaciones.add(asociacion);
  }

  public void removerAsociacion(Asociacion asociacion) {
    this.asociaciones.remove(asociacion);
  }

  public List<PublicacionMascotaPerdida> publicacionesMascotas() {
    return this.asociaciones.stream()
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

  public List<Asociacion> getAsociaciones() {
    return this.asociaciones;
  }

  //TODO : HECHO Capaz devolver la asociacion mas cercana y asignar por consola.
  //TODO : HECHO Encapsular latitud y longitud en una clase

  public Asociacion asociacionMasCercana(PublicacionMascotaPerdida publicacion) {
    if (this.asociaciones.isEmpty()) {
      throw new NoHayNingunaAsociasionException("No se pueden agregar publicaciones porque no hay asociasiones");
    }
    return this.asociaciones.stream().min(Comparator.comparingInt(asociacion -> asociacion.distanciaA(publicacion.getPosicion()))).get();
  }

  public List<PublicacionMascotaEnAdopcion> publicacionesDeMascotasEnAdopcion() {
    return this.asociaciones.stream()
            .flatMap(asociacion -> asociacion.getPublicacionesEnAdopcion().stream())
            .collect(Collectors.toList());
  }

}
