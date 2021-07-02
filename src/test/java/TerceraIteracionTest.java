import apis.JavaXMail;
import apis.MedioNotificacion;
import apis.TwilioJava;
import mascotas.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import personas.*;
import repositorios.RepositorioDeAsociaciones;
import repositorios.RepositorioDePreguntas;
import repositorios.RepositorioDeUsuarios;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.mock;

public class TerceraIteracionTest {

  MedioNotificacion emailSender = mock(JavaXMail.class);
  MedioNotificacion smsSender = mock(TwilioJava.class);
  static Asociacion asociacion;


  @BeforeAll
  public static void initRepositorios() {
    asociacion = new Asociacion(new Posicion(0,0));
    RepositorioDeAsociaciones repoAsociaciones = RepositorioDeAsociaciones.getInstance();
    asociacion.agregarPregunta(new Pregunta("¿Necesita patio?", "¿Tenes patio?"));
    asociacion.agregarPregunta(new Pregunta("¿Necesita jaula?", "¿Tenes jaula?"));
    repoAsociaciones.agregarAsociacion(asociacion);

    RepositorioDePreguntas repoPreguntas = RepositorioDePreguntas.getInstance();
    repoPreguntas.agregarPregunta(new Pregunta("¿Necesita mucho espacio?", "¿Tenes mucho espacio?"));
    repoPreguntas.agregarPregunta(new Pregunta("¿Necesita correa?", "¿Tenes correa?"));
  }


  @Test
  public void sePuedeEncontrarElDuenioDeUnaMascota() {
    Duenio duenio = duenio();
    Mascota mascota = mascota();
    Usuario usuario = new Usuario("unusario ", "hola 123", duenio);
    duenio.agregarMascota(mascota);
    RepositorioDeUsuarios repo = RepositorioDeUsuarios.getInstance();
    repo.agregarUsuario(usuario);

    assertEquals(repo.usuarioDuenioDe(mascota), duenio);

    repo.removerUsuario(usuario);
  }

  @Test
  public void sePuedeGenerarUnaPublicacionDeInteresDeAdopcion() {
    PublicacionInteresadoEnAdopcion publicacionInteresadoEnAdopcion = this.publicacionInteresadoEnAdopcion(asociacion);
    asociacion.agregarPublicacionInteresadoEnAdopcion(publicacionInteresadoEnAdopcion);

    assertEquals(Arrays.asList(publicacionInteresadoEnAdopcion), asociacion.getPublicacionInteresadoEnAdopcion());

    asociacion.removerPublicacionInteresadoEnAdopcion(publicacionInteresadoEnAdopcion);
  }


  public Duenio duenio() {
    PersonaBuilder personaBuilder = new PersonaBuilder();
    personaBuilder.setNombreYApellido("Lionel Andres Messi");
    personaBuilder.setFechaNacimiento(LocalDate.of(1987, 6, 24));
    Contacto metodoContacto = new Contacto("Lionel Messi", "112222333", "messi@messi.com");
    personaBuilder.agregarContacto(metodoContacto);
    personaBuilder.agregarMedioNotificacion(emailSender);
    Duenio duenio = new Duenio(personaBuilder.crearPersona());
    duenio.getPersona().agregarMedioNotificacion(smsSender);
    duenio.agregarMascota(this.mascota());
    return duenio;
  }


  public Mascota mascota() {
    MascotaBuilder mascotaBuilder = new MascotaBuilder();
    mascotaBuilder.setNombre("Sergio Ramos");
    mascotaBuilder.setApodo("Noventa y ramos");
    mascotaBuilder.setEspecie(Especie.PERRO);
    mascotaBuilder.setDescripcion("Un jugador de futbol del real madrid");
    mascotaBuilder.setEdad((short) 35);
    mascotaBuilder.setSexo(Sexo.MACHO);
    mascotaBuilder.setTamanio(Tamanio.GRANDE);
    mascotaBuilder.agregarImagen("https://upload.wikimedia.org/wikipedia/commons/4/43/Russia-Spain_2017_%286%29.jpg");

    return mascotaBuilder.finalizarMascota();
  }

  public Mascota mascotaBuilder(Especie especie, Tamanio tamanio) {
    MascotaBuilder mascotaBuilder = new MascotaBuilder();
    mascotaBuilder.setNombre("Sergio Ramos");
    mascotaBuilder.setApodo("Noventa y ramos");
    mascotaBuilder.setEspecie(especie);
    mascotaBuilder.setDescripcion("Un jugador de futbol del real madrid");
    mascotaBuilder.setEdad((short) 35);
    mascotaBuilder.setSexo(Sexo.MACHO);
    mascotaBuilder.setTamanio(tamanio);
    mascotaBuilder.agregarImagen("https://upload.wikimedia.org/wikipedia/commons/4/43/Russia-Spain_2017_%286%29.jpg");
    return mascotaBuilder.finalizarMascota();
  }

  public PublicacionMascotaEnAdopcion publicacionMascotaEnAdopcion() {
    PublicacionMascotaEnAdopcion publicacionMascotaEnAdopcion = new PublicacionMascotaEnAdopcion(this.mascota());

    RepositorioDePreguntas.getInstance().getPreguntas().forEach(
        pregunta -> publicacionMascotaEnAdopcion.agregarRespuesta(new Respuesta(true, pregunta))
    );

    asociacion.getPreguntas().forEach(
        pregunta -> publicacionMascotaEnAdopcion.agregarRespuesta(new Respuesta(true, pregunta)));

    return publicacionMascotaEnAdopcion;
  }

  public PublicacionInteresadoEnAdopcion publicacionInteresadoEnAdopcion(Asociacion asociacion) {
    PersonaBuilder personaBuilder = new PersonaBuilder();
    personaBuilder.setNombreYApellido("Lionel Andres Messi");
    personaBuilder.setFechaNacimiento(LocalDate.of(1987, 6, 24));
    Contacto metodoContacto = new Contacto("Lionel Messi", "112222333", "messi@messi.com");
    personaBuilder.agregarContacto(metodoContacto);
    personaBuilder.agregarMedioNotificacion(emailSender);

    PublicacionInteresadoEnAdopcion publicacionInteresadoEnAdopcion =
        new PublicacionInteresadoEnAdopcion(new Interesado(personaBuilder.crearPersona()));

    RepositorioDePreguntas.getInstance().getPreguntas().forEach(
        pregunta -> publicacionInteresadoEnAdopcion.agregarRespuesta(new Respuesta(true, pregunta))
    );

    asociacion.getPreguntas().forEach(
        pregunta -> publicacionInteresadoEnAdopcion.agregarRespuesta(new Respuesta(true, pregunta)));

    return publicacionInteresadoEnAdopcion;
  }
}
