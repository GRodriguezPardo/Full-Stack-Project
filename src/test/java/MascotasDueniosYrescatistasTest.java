import apis.MedioNotificacion;
import exceptions.FaltanDatosException;
import mascotas.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import personas.Duenio;
import personas.Usuario;
import repositorios.RepositorioDeRescates;
import repositorios.RepositorioDeUsuarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class MascotasDueniosYrescatistasTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
  Fixture fixture = new Fixture();

  MedioNotificacion emailSender = fixture.getEmailSenderMock();
  MedioNotificacion smsSender = fixture.getSmsSenderMock();


  @BeforeAll
  public static void agregarPosiblesCaracteristicas() {
    PosiblesCaracteristicas.getInstance().agregarPosibleCaracteristica("Color principal", new Caracteristica<String>());
    PosiblesCaracteristicas.getInstance().agregarPosibleCaracteristica("Esta castrado", new Caracteristica<Boolean>());
  }

  @Test
  public void unaMascotaVaciaFalla() {
    Assertions.assertThrows(FaltanDatosException.class, () -> new Mascota(null, null, null, null, null, null, null, null, null));
  }

  @Test
  public void puedoCrearUnaMascotaSinProblema() {
    Assertions.assertNotNull(fixture.mascota(false));
  }

  @Test
  public void losDatosDeUnaMascotaSeAsignanCorrectamente() {
    Mascota mascota = fixture.mascota(false);
    Assertions.assertEquals("Sergio Ramos", mascota.getNombre());
    Assertions.assertEquals("Noventa y ramos", mascota.getApodo());
    Assertions.assertEquals(Especie.PERRO, mascota.getEspecie());
    Assertions.assertEquals("Un jugador de futbol del real madrid", mascota.getDescripcion());
    Assertions.assertEquals((short) 35, mascota.getEdad());
    Assertions.assertEquals(Sexo.MACHO, mascota.getSexo());
  }

  @Test
  public void puedoCrearUnDuenioSinProblemas() {
    Assertions.assertNotNull(fixture.duenio());
  }

  @Test
  public void duenioSeAsignaSinProblemaAUsuario() {
    Duenio duenio = fixture.duenio();
    Assertions.assertEquals(new Usuario("Jose", "sito", duenio).getDuenio(), duenio);
  }

  @Test
  public void puedoContactarAUnDuenio() {
    Duenio duenio = fixture.duenio();
    duenio.contactarDuenioPorMascota();
    verify(emailSender).notificarMascotaPerdida(duenio.getPersona().getContactos().get(0));
    verify(smsSender).notificarMascotaPerdida(duenio.getPersona().getContactos().get(0));
  }

  @Test
  @Disabled
  public void sePuedeEncontrarElDuenioDeUnaMascota() {
    this.beginTransaction();
    Duenio duenio = fixture.duenio();
    Mascota mascota = fixture.mascota(false);
    Usuario usuario = new Usuario("unusario ", "hola 123", duenio);
    duenio.agregarMascota(mascota);
    RepositorioDeUsuarios repo = RepositorioDeUsuarios.getInstance();
    repo.agregarUsuario(usuario);

    assertEquals(repo.usuarioDuenioDe(mascota), duenio);

    this.rollbackTransaction();
  }

  @Test
  public void puedoCrearUnaMascotaPerdidaSinProblema() {
    Assertions.assertNotNull(fixture.mascotaPerdida(12345, 54321));
  }

  @Test
  public void laDescripcionDeUnaMascotaPerdidaSeAsignaCorrectamente() {
    MascotaPerdida mascotaPerdida = fixture.mascotaPerdida(12345, 54321);
    Assertions.assertEquals("Estaba intentando marcar a Messi", mascotaPerdida.getDescripcionEstado());
  }

  @Test
  public void agregarCarateristicaVaciaDaError() {
    MascotaBuilder mascotaBuilder = new MascotaBuilder();
    Assertions.assertThrows(FaltanDatosException.class, () -> mascotaBuilder.agregarNuevaCaracteristica(null, null));
  }

  @Test
  public void puedoCrearUnRescatistaSinProblema() {
    Assertions.assertNotNull(fixture.rescatista(12345, 54321));
  }

  @Test
  public void puedoAgregarUnRescateAlRepositorioDeRescatesYVerloEnLosUltimos10Dias() {
    RepositorioDeRescates.getInstance().agregarRescate(fixture.rescatista(12345, 54321));
    Assertions.assertFalse(RepositorioDeRescates.getInstance().mascotasEncontradaEnLosDias(10).isEmpty());
  }
}
