package repositorios;

import mascotas.PublicacionInteresadoEnAdopcion;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase singleton cuyo objetivo es guardar los avisos de rescate que se reportan.
 */
public class RepositorioDeInteresados {
  private final static RepositorioDeInteresados INSTANCE = new RepositorioDeInteresados();

  public List<PublicacionInteresadoEnAdopcion> getPublicacionesDeInteresados() {
    return publicacionesDeInteresados;
  }

  private final List<PublicacionInteresadoEnAdopcion> publicacionesDeInteresados = new ArrayList<>();

  /**
   * Contructor privado al ser singleton.
   */
  private RepositorioDeInteresados() {
  }

  /**
   * Metodo estatico para obtener al singleton.
   *
   * @return retorna al singleton.
   */
  public static RepositorioDeInteresados getInstance() {
    return INSTANCE;
  }


  /**
   * Permite agregar un nuevo aviso de rescate a la lista de rescates que posee
   * el singleton.
   *
   * @param nuevoInteresado es el rescate a ser agregado.
   */
  public void agregarRescate(PublicacionInteresadoEnAdopcion nuevoInteresado) {
    this.publicacionesDeInteresados.add(nuevoInteresado);
  }

}
