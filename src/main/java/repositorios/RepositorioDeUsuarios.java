package repositorios;

import exceptions.DatosErroneosException;
import exceptions.FaltanDatosException;
import exceptions.NoExisteDuenioDeMascotaException;
import mascotas.Mascota;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import personas.Admin;
import personas.Duenio;
import personas.Usuario;
import personas.Voluntario;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * Clase singleton cuyo objetivo es guardar los usuarios que se registran.
 */
public class RepositorioDeUsuarios implements WithGlobalEntityManager {
  private final static RepositorioDeUsuarios INSTANCE = new RepositorioDeUsuarios();
  /*private final List<Admin> administradores = new ArrayList<>();
  private final List<Usuario> usuarios = new ArrayList<>();
  private final List<Voluntario> voluntarios = new ArrayList<>();
 */
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
    boolean condition = !entityManager()
				.createQuery("select a from Admin a where usuario = :nuevoUsuario")
				.setParameter("nuevoUsuario", admin.getUsuario())
				.getResultList().isEmpty();
    //this.administradores.stream().anyMatch(unPerfil -> unPerfil.getUsuario().equals(admin.getUsuario()))
    if (condition) {
      throw new DatosErroneosException("Nombre de Usuario tomado, elegir otro");
    }
    //this.administradores.add(admin);
    entityManager().persist(admin);
  }

  /**
   * Permite agregar un Usuario y clave a la lista del singleton
   */
  public void agregarVoluntario(Voluntario voluntario) {
    if (Objects.isNull(voluntario) || Objects.isNull(voluntario.getUsuario()) || Objects.isNull(voluntario.getClave())) {
      throw new FaltanDatosException("Se debe proveer un Usuario y una contraseña");
    }
    boolean condition = !entityManager()
				.createQuery("select a from Voluntario a where usuario = :nuevoUsuario")
				.setParameter("nuevoUsuario", voluntario.getUsuario())
				.getResultList().isEmpty();
    //this.voluntarios.stream().anyMatch(unPerfil -> unPerfil.getUsuario().equals(voluntario.getUsuario()))
    if (condition) {
      throw new DatosErroneosException("Nombre de Usuario tomado, elegir otro");
    }
    //this.voluntarios.add(voluntario);
    entityManager().persist(voluntario);
  }

  /**
   * Permite agregar un Usuario y clave a la lista del singleton
   */
  public void agregarUsuario(Usuario usuario) {
    if (Objects.isNull(usuario) || Objects.isNull(usuario.getUsuario()) || Objects.isNull(usuario.getClave())) {
      throw new FaltanDatosException("Se debe proveer un Usuario y una contraseña");
    }
    boolean condition = !entityManager()
				.createQuery("select a from Usuario a where usuario = :nuevoUsuario")
				.setParameter("nuevoUsuario", usuario.getUsuario())
				.getResultList().isEmpty();
    //this.usuarios.stream().anyMatch(unPerfil -> unPerfil.getUsuario().equals(usuario.getUsuario()))
    if (condition) {
      throw new DatosErroneosException("Nombre de Usuario tomado, elegir otro");
    }
    //this.usuarios.add(usuario);
    entityManager().persist(usuario);
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
    return !entityManager()
				.createQuery("select a from Admin a where usuario = :usuario and clave =:clave")
				.setParameter("usuario", usuario)
        .setParameter("clave", clave)
				.getResultList().isEmpty();
  }

  public void removerAdmin(Admin perfil) {
    //this.administradores.remove(perfil);
    Admin perfilBorrar = entityManager().find(Admin.class, perfil.getId());
    entityManager().remove(perfilBorrar);
  }

  public void removerUsuario(Usuario perfil) {
    //this.usuarios.remove(perfil);
    Usuario perfilBorrar = entityManager().find(Usuario.class, perfil.getId());
    entityManager().remove(perfilBorrar);
  }

  public void removerVoluntario(Voluntario perfil) {
    Voluntario perfilBorrar = entityManager().find(Voluntario.class, perfil.getId());
    entityManager().remove(perfilBorrar);
  }

  public List<Admin> administradores() {

    return entityManager()
        .createQuery("from Admin").getResultList();
  }

  public List<Usuario> usuarios() {
    //return this.usuarios;
    return entityManager()
        .createQuery("from Usuario").getResultList();
  }

  public List<Voluntario> voluntarios() {
    //return this.voluntarios;
    return entityManager()
        .createQuery("from Admin").getResultList();
  }

  public Duenio usuarioDuenioDe(Mascota mascota) {
    Mascota sessionMascota = entityManager().find(Mascota.class, mascota.getId());
    List<Usuario> usuariosDuenio = usuarios().stream().filter(usuario -> usuario.duenioDe(sessionMascota)).collect(Collectors.toList());

    if (!usuariosDuenio.isEmpty()) {
      return usuariosDuenio.stream().findFirst().get().getDuenio();
    } else
      throw new NoExisteDuenioDeMascotaException("La mascota no tiene duenio.");
  }
}
