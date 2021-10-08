import apis.Mailer;
import apis.MedioNotificacion;
import apis.Smser;
import mascotas.*;
import personas.*;
import repositorios.RepositorioDePreguntas;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class Fixture {
  MedioNotificacion emailSender = mock(Mailer.class);
  MedioNotificacion smsSender = mock(Smser.class);

  MedioNotificacion getEmailSenderMock() {
    return this.emailSender;
  }

  MedioNotificacion getSmsSenderMock() {
    return this.smsSender;
  }

  public void settearColorPrincipal(MascotaBuilder mascotaBuilder, String color) {
    mascotaBuilder.agregarNuevaCaracteristica("Color principal", color);
  }

  public void settearCastrado(MascotaBuilder mascotaBuilder, Boolean estado) {
    mascotaBuilder.agregarNuevaCaracteristica("Esta castrado", estado);
  }

  public Mascota mascota(boolean castrada) {
    MascotaBuilder mascotaBuilder = new MascotaBuilder();
    mascotaBuilder.setNombre("Sergio Ramos");
    mascotaBuilder.setApodo("Noventa y ramos");
    mascotaBuilder.setEspecie(Especie.PERRO);
    mascotaBuilder.setDescripcion("Un jugador de futbol del real madrid");
    mascotaBuilder.setEdad((short) 35);
    mascotaBuilder.setSexo(Sexo.MACHO);
    mascotaBuilder.setTamanio(Tamanio.GRANDE);
    mascotaBuilder.agregarImagen("https://upload.wikimedia.org/wikipedia/commons/4/43/Russia-Spain_2017_%286%29.jpg");
    this.settearColorPrincipal(mascotaBuilder, "Blanco");
    this.settearCastrado(mascotaBuilder, castrada);
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
    this.settearColorPrincipal(mascotaBuilder, "Blanco");
    return mascotaBuilder.finalizarMascota();
  }


  public MascotaPerdida mascotaPerdida(double longitud, double latitud) {
    String descripcion = "Estaba intentando marcar a Messi";
    List<Image> fotos = new ArrayList<>();

    ImageIcon _foto1 = new ImageIcon("https://images.ole.com.ar/2020/12/31/SiSmW6L4M_340x340__1.jpg");
    Image foto1 = _foto1.getImage();
    fotos.add(foto1);

    ImageIcon _foto2 = new ImageIcon("https://www.diez.hn/csp/mediapool/sites/dt.common.streams.StreamServer.cls?STREAMOID=GCN_RhtV099DtwjsK8LR3c$daE2N3K4ZzOUsqbU5sYuLAvgewimt_YtK9twCcBxuWCsjLu883Ygn4B49Lvm9bPe2QeMKQdVeZmXF$9l$4uCZ8QDXhaHEp3rvzXRJFdy0KqPHLoMevcTLo3h8xh70Y6N_U_CryOsw6FTOdKL_jpQ-&CONTENTTYPE=image/jpeg");
    Image foto2 = _foto2.getImage();
    fotos.add(foto2);
    // new Posicion(12345, 54321)
    return new MascotaPerdida(descripcion, fotos, new Posicion(longitud, latitud));
  }

  public Rescatista rescatista(double longitud, double latitud) {
    PersonaBuilder personaBuilder = new PersonaBuilder();
    personaBuilder.setNombreYApellido("Cristiano Ronaldo");
    personaBuilder.setFechaNacimiento(LocalDate.of(1985, 2, 5));
    Contacto metodoContacto = new Contacto("CR7", "1211113333", "cristiano@ronaldo.com");
    personaBuilder.agregarContacto(metodoContacto);
    personaBuilder.agregarMedioNotificacion(emailSender);
    Rescatista rescatista = new Rescatista(personaBuilder.crearPersona(), LocalDate.now(), this.mascotaPerdida(longitud, latitud));
    rescatista.getPersona().agregarMedioNotificacion(smsSender);
    return rescatista;
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
    duenio.agregarMascota(this.mascota(false));
    duenio.getPersona().agregarContacto(new Contacto("Anto", "222", "Anto@Anto.com"));
    return duenio;
  }

  public PublicacionMascotaEnAdopcion publicacionMascotaEnAdopcion(Asociacion asociacion) {
    PublicacionMascotaEnAdopcion publicacionMascotaEnAdopcion = new PublicacionMascotaEnAdopcion(this.mascota(false));

    RepositorioDePreguntas.getInstance().getPreguntas().forEach(
            pregunta -> publicacionMascotaEnAdopcion.agregarRespuesta(new Respuesta("Si", pregunta))
    );

    asociacion.getPreguntas().forEach(
            pregunta -> publicacionMascotaEnAdopcion.agregarRespuesta(new Respuesta("Si", pregunta)));

    return publicacionMascotaEnAdopcion;
  }

  public PublicacionInteresadoEnAdopcion publicacionInteresadoEnAdopcion(Asociacion asociacion) {
    PersonaBuilder personaBuilder = new PersonaBuilder();
    personaBuilder.setNombreYApellido("Lionel Andres Messi");
    personaBuilder.setFechaNacimiento(LocalDate.of(1987, 6, 24));
    Contacto metodoContacto = new Contacto("Lionel Messi", "112222333", "messi@messi.com");
    personaBuilder.agregarContacto(metodoContacto);
    personaBuilder.agregarMedioNotificacion(emailSender);
    Persona persona = personaBuilder.crearPersona();
    persona.agregarMedioNotificacion(smsSender);

    PublicacionInteresadoEnAdopcion publicacionInteresadoEnAdopcion =
            new PublicacionInteresadoEnAdopcion(persona);

    List<Pregunta> preguntas = new ArrayList<>();
    preguntas.addAll(RepositorioDePreguntas.getInstance().getPreguntas());
    preguntas.addAll(asociacion.getPreguntas());

    preguntas.forEach(
            pregunta -> publicacionInteresadoEnAdopcion.agregarRespuesta(new Respuesta("Si", pregunta))
    );

    return publicacionInteresadoEnAdopcion;
  }

  public Usuario unUsuario(String usuario , String clave ,Duenio duenio){
    return new Usuario(usuario, clave, duenio);
  }

}
