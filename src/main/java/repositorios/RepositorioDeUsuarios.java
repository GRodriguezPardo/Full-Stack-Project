package repositorios;

import exceptions.DatosErroneosException;
import exceptions.FaltanDatosException;
import exceptions.NoExisteDuenioDeMascotaException;
import mascotas.Mascota;
import personas.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * Clase singleton cuyo objetivo es guardar los usuarios que se registran.
 */
public class RepositorioDeUsuarios {
  private final static RepositorioDeUsuarios INSTANCE = new RepositorioDeUsuarios();
  private final List<Admin> administradores = new ArrayList<>();
  private final List<Usuario> usuarios = new ArrayList<>();
  private final List<Voluntario> voluntarios = new ArrayList<>();
  /**
   * Contructor privado al ser singleton.
   */
  private RepositorioDeUsuarios() {
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
  public void agregarAdmin(Admin admin) {
    if (Objects.isNull(admin) || Objects.isNull(admin.getUsuario()) || Objects.isNull(admin.getClave())) {
      throw new FaltanDatosException("Se debe proveer un Usuario y una contraseña");
    }
    if (this.administradores.stream().anyMatch(unPerfil -> unPerfil.getUsuario().equals(admin.getUsuario()))) {
      throw new DatosErroneosException("Nombre de Usuario tomado, elegir otro");
    }
    this.administradores.add(admin);
  }

  /**
   * Permite agregar un Usuario y clave a la lista del singleton
   */
  public void agregarVoluntario(Voluntario voluntario) {
    if (Objects.isNull(voluntario) || Objects.isNull(voluntario.getUsuario()) || Objects.isNull(voluntario.getClave())) {
      throw new FaltanDatosException("Se debe proveer un Usuario y una contraseña");
    }
    if (this.voluntarios.stream().anyMatch(unPerfil -> unPerfil.getUsuario().equals(voluntario.getUsuario()))) {
      throw new DatosErroneosException("Nombre de Usuario tomado, elegir otro");
    }
    this.voluntarios.add(voluntario);
  }

  /**
   * Permite agregar un Usuario y clave a la lista del singleton
   */
  public void agregarUsuario(Usuario usuario) {
    if (Objects.isNull(usuario) || Objects.isNull(usuario.getUsuario()) || Objects.isNull(usuario.getClave())) {
      throw new FaltanDatosException("Se debe proveer un Usuario y una contraseña");
    }
    if (this.usuarios.stream().anyMatch(unPerfil -> unPerfil.getUsuario().equals(usuario.getUsuario()))) {
      throw new DatosErroneosException("Nombre de Usuario tomado, elegir otro");
    }
    this.usuarios.add(usuario);
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
    return this.administradores.stream()
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
  public void cambiarClave(String usuario, String claveVieja, String claveNueva) {
    if (this.comprobarClave(usuario, claveVieja)) {
      this.administradores.stream()
              .filter(unPerfil -> unPerfil.getUsuario().equals(usuario))
              .findFirst().get().setClave(claveNueva);
    } else {
      throw new DatosErroneosException("Usuario o contraseña erroneos");
    }
  }

  public void removerAdmin(Admin perfil) {
    this.administradores.remove(perfil);
  }

  public void removerUsuario(Usuario perfil) {
    this.usuarios.remove(perfil);
  }
  public void removerVoluntario(Voluntario perfil) {
    this.voluntarios.remove(perfil);
  }


  public List<Admin> administradores() {
    return this.administradores;
  }

  public List<Usuario> usuarios() {
    return this.usuarios;
  }
  public List<Voluntario> voluntarios() {
    return this.voluntarios;
  }

  public Duenio usuarioDuenioDe(Mascota mascota) {
   List<Usuario> usuariosDuenio =  usuarios.stream().filter(usuario -> usuario.duenioDe(mascota)).collect(Collectors.toList());


    if (!usuariosDuenio.isEmpty()) {
      return usuariosDuenio.stream().findFirst().get().getDuenio();
    } else
      throw new NoExisteDuenioDeMascotaException("La mascota no tiene duenio.");
  }
}
