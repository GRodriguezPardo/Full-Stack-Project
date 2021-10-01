import exceptions.NoHayNingunaAsociasionException;
import mascotas.Caracteristica;
import mascotas.PosiblesCaracteristicas;
import mascotas.PublicacionMascotaPerdida;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import personas.*;
import repositorios.RepositorioDeAsociaciones;
import repositorios.RepositorioDeUsuarios;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


public class AsociacionesYpublicacionesTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
  Fixture fixture = new Fixture();

  /*
    MedioNotificacion emailSender = fixture.getEmailSenderMock();
    MedioNotificacion smsSender = fixture.getSmsSenderMock();
  */
  @BeforeAll
  public static void agregarPosiblesCaracteristicas() {
    PosiblesCaracteristicas.getInstance().agregarPosibleCaracteristica("Color principal", new Caracteristica<String>());
    PosiblesCaracteristicas.getInstance().agregarPosibleCaracteristica("Esta castrado", new Caracteristica<Boolean>());
  }

  @Test
  public void puedoAgregarAsociacionesAlRepo() {
    this.beginTransaction();

    Asociacion asociacion = new Asociacion(new Posicion(10, 20));
    RepositorioDeAsociaciones repo = RepositorioDeAsociaciones.getInstance();
    repo.agregarAsociacion(asociacion);
    assertTrue(repo.getAsociaciones().stream().anyMatch(unaAsociacion -> unaAsociacion.getId() == asociacion.getId()));

    this.rollbackTransaction();
  }

  @Test
  public void lasPublicacionesSeAsignanCorrectamente() {
    this.beginTransaction();

    Asociacion asociacion1 = new Asociacion(new Posicion(10, 10));
    Asociacion asociacion2 = new Asociacion(new Posicion(20, 20));
    PublicacionMascotaPerdida publicacion = new PublicacionMascotaPerdida(fixture.rescatista(0, 0));
    RepositorioDeAsociaciones repo = RepositorioDeAsociaciones.getInstance();
    repo.agregarAsociacion(asociacion1);
    repo.agregarAsociacion(asociacion2);

    assertEquals(repo.asociacionMasCercana(publicacion), asociacion1);

    this.rollbackTransaction();
  }

  @Test
  public void lasPublicacionesSeObtienenCorrectamenteSegunCriterio() {
    this.beginTransaction();
    PublicacionMascotaPerdida publicacionDesaprobada1 = new PublicacionMascotaPerdida(fixture.rescatista(0, 0));
    PublicacionMascotaPerdida publicacionDesaprobada2 = new PublicacionMascotaPerdida(fixture.rescatista(0, 0));
    Asociacion unaAsociacion = new Asociacion(new Posicion(10, 10));
    RepositorioDeAsociaciones repo = RepositorioDeAsociaciones.getInstance();
    repo.agregarAsociacion(unaAsociacion);
    unaAsociacion.agregarPublicacionMascotaPerdida(publicacionDesaprobada1);
    unaAsociacion.agregarPublicacionMascotaPerdida(publicacionDesaprobada2);

    assertTrue(repo.publicacionesAprobadas().isEmpty());
    assertTrue(repo.publicacionesNoAprobadas().contains(publicacionDesaprobada1));
    assertTrue(repo.publicacionesNoAprobadas().contains(publicacionDesaprobada2));

    this.rollbackTransaction();
  }

  @Test
  public void seTiraExceptionSiNoHayAsociacionesAlAgregarPublicacion() {
    this.beginTransaction();
    RepositorioDeAsociaciones repo = RepositorioDeAsociaciones.getInstance();
    PublicacionMascotaPerdida unaPublicacion = new PublicacionMascotaPerdida(fixture.rescatista(0, 0));

    assertThrows(NoHayNingunaAsociasionException.class, () -> repo.asociacionMasCercana(unaPublicacion));
    this.rollbackTransaction();
  }

  @Test
  public void puedoObtenerLasPublicacionesManejablesDeVoluntario() {
    this.beginTransaction();
    PublicacionMascotaPerdida publicacionDesaprobada = new PublicacionMascotaPerdida(fixture.rescatista(0, 0));
    Asociacion unaAsociacion = new Asociacion(new Posicion(10, 10));
    RepositorioDeAsociaciones repo = RepositorioDeAsociaciones.getInstance();
    repo.agregarAsociacion(unaAsociacion);
    unaAsociacion.agregarPublicacionMascotaPerdida(publicacionDesaprobada);
    Voluntario voluntario = new Voluntario("Jose", "AyudoMucho", unaAsociacion);

    assertTrue(voluntario.publicacionesGestionables().contains(publicacionDesaprobada));

    this.rollbackTransaction();
  }

  @Test
  public void puedoAgregarTodosLosTiposDePerfilesAlRepo() {
    this.beginTransaction();
    RepositorioDeUsuarios repo = RepositorioDeUsuarios.getInstance();
    Usuario perfil1 = new Usuario("Luis I", "Soy primero", fixture.duenio());
    Admin perfil2 = new Admin("Luis II", "Soy segundo");
    Voluntario perfil3 = new Voluntario("Luis III", "Soy tercero", new Asociacion(new Posicion(33, 33)));

    repo.agregarUsuario(perfil1);
    repo.agregarAdmin(perfil2);
    repo.agregarVoluntario(perfil3);

    assertTrue(Objects.nonNull(entityManager().find(Usuario.class, perfil1.getId())));
    assertTrue(Objects.nonNull(entityManager().find(Admin.class, perfil2.getId())));
    assertTrue(Objects.nonNull(entityManager().find(Voluntario.class, perfil3.getId())));

    this.rollbackTransaction();
  }

}
