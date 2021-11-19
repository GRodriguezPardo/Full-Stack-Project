package server;

import apis.*;

import java.awt.Image;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

import mascotas.*;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import personas.*;
import repositorios.RepositorioDeCaracteristicas;
import repositorios.RepositorioDePreguntas;



public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
  public static void main(String[] args) {
    new Bootstrap().init();
  }

  private void init() {
    withTransaction(() -> {
      entityManager().persist(this.unUsuario("UsuarioComun","clave", this.duenio()));
      entityManager().persist(new Admin("admin","root"));
      entityManager().persist(new Asociacion(new Posicion(44.00,55.00)));
      entityManager().persist(new PublicacionMascotaPerdida(this.rescatista(55,66)));

      RepositorioDeCaracteristicas.getInstance().agregarPosibleCaracteristica("Raza");
      RepositorioDeCaracteristicas.getInstance().agregarPosibleCaracteristica("Numero de estremidades");
    });
  }

  MedioNotificacion emailSender = new Mailer(new JavaXMail("A","A"));
  MedioNotificacion smsSender = new Smser(new TwilioJava("1", "a", "11"));

  MedioNotificacion getEmailSenderMock() {
    return this.emailSender;
  }

  MedioNotificacion getSmsSenderMock() {
    return this.smsSender;
  }

  public void settearColorPrincipal(MascotaBuilder mascotaBuilder, String color) {
    mascotaBuilder.agregarNuevaCaracteristica("Color principal", color);
  }

  public void settearCastrado(MascotaBuilder mascotaBuilder, String estado) {
    mascotaBuilder.agregarNuevaCaracteristica("Esta castrado", estado);
  }

  public Mascota mascota(String castrada) {
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
    List<Image> fotos = new ArrayList<Image>();

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
    duenio.agregarMascota(this.mascota("FALSE"));
    duenio.getPersona().agregarContacto(new Contacto("Anto", "222", "Anto@Anto.com"));
    return duenio;
  }

  public PublicacionMascotaEnAdopcion publicacionMascotaEnAdopcion(Asociacion asociacion) {
    PublicacionMascotaEnAdopcion publicacionMascotaEnAdopcion = new PublicacionMascotaEnAdopcion(this.mascota("FALSE"));

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
