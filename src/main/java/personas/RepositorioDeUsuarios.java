package personas;

import exceptions.DatosErroneosException;
import exceptions.FaltanDatosException;
import seguridad.Validaciones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Clase singleton cuyo objetivo es guardar los usuarios que se registran.
 */
public class RepositorioDeUsuarios {
  private final static RepositorioDeUsuarios INSTANCE = new RepositorioDeUsuarios();
  private final List<Perfil> perfiles = new ArrayList<>();
  private final Validaciones validaciones;

  /**
   * Contructor privado al ser singleton.
   */
  private RepositorioDeUsuarios() {  /*Agrege las validaciones a la lista para que salga un test donde se corren todas*/
    validaciones = new Validaciones();
  }

  /**
   * Metodo estatico para obtener al singleton.
   *
   * @return retorna al singleton.
   */
  public static RepositorioDeUsuarios getInstance() {
    return INSTANCE;
  }


  /**
   * Permite agregar un Usuario y clave a la lista del singleton
   */
  public void agregarPerfil(Perfil perfil) throws IOException {
    if (Objects.isNull(perfil) || Objects.isNull(perfil.getUsuario()) || Objects.isNull(perfil.getClave())) {
      throw new FaltanDatosException("Se debe proveer un Usuario y una contraseña");
    }
    if (this.perfiles.stream().anyMatch(unPerfil -> unPerfil.getUsuario().equals(perfil.getUsuario()))) {
      throw new DatosErroneosException("Nombre de Usuario tomado, elegir otro");
    }
    this.perfiles.add(perfil);
  }

  /**
   * Permite a comprobar las credenciales de un Usuario.
   *
   * @param usuario es el nombre de Usuario.
   * @param clave   es la contraseña del Usuario.
   * @return es el resultado de la comprobacion.
   */
  public Boolean comprobarClave(String usuario, String clave) {
    if (Objects.isNull(clave)) {
      return false;
    }
    return this.perfiles.stream()
            .filter(unPerfil -> unPerfil.getUsuario().equals(usuario))
            .findFirst().get()
            .getClave().equals(clave);
  }

  /**
   * Permite a un Usuario cambiar su contraseña.
   *
   * @param usuario    es el Usuario cuya contraseña cambiara.
   * @param claveVieja es la anterior clave del Usuario, necesaria para
   *                   comprobar sus credenciales.
   * @param claveNueva es la nueva clave.
   */
  public void cambiarClave(String usuario, String claveVieja, String claveNueva) throws IOException {
    if (this.comprobarClave(usuario, claveVieja)) {
      this.perfiles.stream()
              .filter(unPerfil -> unPerfil.getUsuario().equals(usuario))
              .findFirst().get().setClave(claveNueva);
    } else {
      throw new DatosErroneosException("Usuario o contraseña erroneos");
    }
  }

  public void removerPerfil(Perfil perfil) {
    this.perfiles.remove(perfil);
  }
}
