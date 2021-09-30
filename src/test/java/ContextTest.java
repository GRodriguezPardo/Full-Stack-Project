import mascotas.Caracteristica;
import mascotas.PosiblesCaracteristicas;
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
    PosiblesCaracteristicas.getInstance().agregarPosibleCaracteristica("Color principal", new Caracteristica<String>());
    PosiblesCaracteristicas.getInstance().agregarPosibleCaracteristica("Esta castrado", new Caracteristica<Boolean>());
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
}
