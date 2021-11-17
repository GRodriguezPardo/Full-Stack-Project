import mascotas.Caracteristica;
import repositorios.RepositorioDeCaracteristicas;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import personas.Asociacion;
import personas.Posicion;

import static org.junit.jupiter.api.Assertions.*;

public class ContextTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
  Fixture fixture = new Fixture();

  @BeforeAll
  public static void agregarPosiblesCaracteristicas() {
    RepositorioDeCaracteristicas.getInstance().agregarPosibleCaracteristica("Color principal");//, new Caracteristica<String>());
    RepositorioDeCaracteristicas.getInstance().agregarPosibleCaracteristica("Esta castrado");//), new Caracteristica<Boolean>());
  }
  @Test
  public void contextUp() {
    assertNotNull(entityManager());
  }

  @Test
  public void puedePersistirUnaMascota() {
    entityManager().persist(fixture.mascota(true));
  }

  @Test
  public void puedePersistirUnDuenio() {
    entityManager().persist(fixture.duenio());
  }

  @Test
  public void puedePersistirUnaMascotaPerdida() {
    entityManager().persist(fixture.mascotaPerdida(0,0));
  }

  @Test
  public void puedePersistirUnaPublicacionDeInteresado() {
    Asociacion asociacion = new Asociacion(new Posicion(0,0));
    entityManager().persist(fixture.publicacionInteresadoEnAdopcion(asociacion));
  }

  @Test
  public void puedePersistirUnRescatista() {
    entityManager().persist(fixture.rescatista(0,0));
  }

  @Test
  public void puedePersistirUnUsuario() {
    entityManager().persist(fixture.unUsuario("hola","chau", fixture.duenio()));
  }

  }
