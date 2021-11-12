import apis.MedioNotificacion;
import cron.Application;
import mascotas.PublicacionInteresadoEnAdopcion;
import mascotas.PublicacionMascotaEnAdopcion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import personas.Asociacion;
import personas.Posicion;
import personas.Pregunta;
import repositorios.RepositorioDeAsociaciones;
import repositorios.RepositorioDePreguntas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class TerceraIteracionTest implements TransactionalOps, EntityManagerOps, WithGlobalEntityManager {
  static Asociacion asociacion;
  Fixture fixture = new Fixture();
  MedioNotificacion emailSender = fixture.getEmailSenderMock();
  MedioNotificacion smsSender = fixture.getSmsSenderMock();

  @BeforeEach
  public void init() {
    this.beginTransaction();
    List<String> opciones = new ArrayList<String>();
    opciones.add("Si");
    opciones.add("No");
    asociacion = new Asociacion(new Posicion(0, 0));
    RepositorioDeAsociaciones repoAsociaciones = RepositorioDeAsociaciones.getInstance();
    asociacion.agregarPregunta(new Pregunta("¿Necesita patio?", "¿Tenes patio?", opciones));
    asociacion.agregarPregunta(new Pregunta("¿Necesita jaula?", "¿Tenes jaula?", opciones));
    repoAsociaciones.agregarAsociacion(asociacion);

    RepositorioDePreguntas repoPreguntas = RepositorioDePreguntas.getInstance();
    repoPreguntas.agregarPregunta(new Pregunta("¿Necesita mucho espacio?", "¿Tenes mucho espacio?", opciones));
    repoPreguntas.agregarPregunta(new Pregunta("¿Necesita correa?", "¿Tenes correa?", opciones));
  }

  @AfterEach
  public void clean() {
    this.rollbackTransaction();
  }
  //remover cosas de los repositorios


  @Test
  public void sePuedeGenerarUnaPublicacionDeMascotaEnAdopcion() {
    PublicacionMascotaEnAdopcion publicacionMascotaEnAdopcion = fixture.publicacionMascotaEnAdopcion(asociacion);
    asociacion.agregarPublicacionMascotaEnAdopcion(publicacionMascotaEnAdopcion);

    assertEquals(Arrays.asList(publicacionMascotaEnAdopcion), asociacion.getPublicacionesEnAdopcion());
  }


  @Test
  public void sePuedeGenerarUnaPublicacionDeInteresDeAdopcion() {
    PublicacionInteresadoEnAdopcion publicacionInteresadoEnAdopcion = fixture.publicacionInteresadoEnAdopcion(asociacion);
    asociacion.agregarPublicacionInteresadoEnAdopcion(publicacionInteresadoEnAdopcion);

    assertEquals(Arrays.asList(publicacionInteresadoEnAdopcion), asociacion.getPublicacionInteresadoEnAdopcion());

    asociacion.removerPublicacionInteresadoEnAdopcion(publicacionInteresadoEnAdopcion);
  }

  @Test
  public void seMandanLasRecomendacionesSemanales() {
    PublicacionMascotaEnAdopcion publicacionMascotaEnAdopcion = fixture.publicacionMascotaEnAdopcion(asociacion);
    asociacion.agregarPublicacionMascotaEnAdopcion(publicacionMascotaEnAdopcion);
    PublicacionInteresadoEnAdopcion publicacionInteresadoEnAdopcion = fixture.publicacionInteresadoEnAdopcion(asociacion);
    asociacion.agregarPublicacionInteresadoEnAdopcion(publicacionInteresadoEnAdopcion);
    Application.main(null);

    verify(emailSender).notificarSugerenciaSemanal(any(), eq(1));
    verify(smsSender).notificarSugerenciaSemanal(any(), eq(1));

    asociacion.removerPublicacionMascotaEnAdopcion(publicacionMascotaEnAdopcion);
    asociacion.removerPublicacionInteresadoEnAdopcion(publicacionInteresadoEnAdopcion);
  }

}
