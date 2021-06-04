import apis.EmailSender;
import apis.JavaXMail;
import apis.SmsSender;
import apis.TwilioJava;
import exceptions.NoHayNingunaAsociasionException;
import mascotas.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import personas.*;
import repositorios.RepositorioDeAsociaciones;

import javax.mail.MessagingException;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SegundaIteracionTest {
  EmailSender emailSender = mock(JavaXMail.class);
  SmsSender smsSender = mock(TwilioJava.class);

  @BeforeAll
  public static void agregarPosiblesCaracteristicas() {
    PosiblesCaracteristicas.getInstance().agregarPosibleCaracteristica("Color principal", new Caracteristica<String>());
    PosiblesCaracteristicas.getInstance().agregarPosibleCaracteristica("Esta castrado", new Caracteristica<Boolean>());
  }

  @Test
  public void puedoContactarAUnDuenio() throws MessagingException {
    this.duenio().contactarDuenio("messirve", "Encontre a tu mascota");
    verify(emailSender).sendEmail("messi@messi.com", "messirve", "Encontre a tu mascota");
    verify(smsSender).sendSMS("112222333", "Encontre a tu mascota");
  }

  @Test
  @Disabled
  public void enviarMailNoTiraErrorTESTMANUAL() {
    EmailSender emailSender = new JavaXMail();// _________\/__________ aca pones el mailDestinatario y te fijas que llegue
    assertDoesNotThrow(() -> emailSender.sendEmail("unEjemplo@gmail.com", "hola", "un mensaje de prueba"));
  }

  @Test
  @Disabled
  public void enviarSmsNoTiraErrorTESTMANUAL() {
    TwilioJava twilio = new TwilioJava();
    twilio.setAccountSidAndAuthToken(null, null);//LEER comentarios en smsSender para probar posta con tu telefono
    twilio.setSenderNumber(null);
    assertDoesNotThrow(() -> smsSender.sendSMS("+541165919737", "Mensaje de prueba"));
  }  /*Ahi pones un numero destinatario verificado en la pagina (ese lo esta pero no vas a ver el mensaje , es para mostrar el formato
  valido del numero) y te fijas que te llege el mensaje*/

  @Test
  public void puedoAgregarAsociacionesAlRepo() {
    Asociacion asociacion = new Asociacion(10, 20);
    RepositorioDeAsociaciones repo = RepositorioDeAsociaciones.getInstance();
    repo.agregarAsociacion(asociacion);

    assertTrue(repo.getAsociaciones().contains(asociacion));

    repo.removerAsociacion(asociacion);
  }

  @Test
  public void lasPublicacionesSeAsignanCorrectamente() {
    Asociacion asociacion1 = new Asociacion(10, 10);
    Asociacion asociacion2 = new Asociacion(20, 20);
    PublicacionMascotaPerdida publicacion = new PublicacionMascotaPerdida(this.rescatista());
    RepositorioDeAsociaciones repo = RepositorioDeAsociaciones.getInstance();
    repo.agregarAsociacion(asociacion1);
    repo.agregarAsociacion(asociacion2);
    repo.agregarPublicacion(publicacion);

    assertTrue(asociacion1.publicacionesACargo().contains(publicacion));
    assertFalse(asociacion2.publicacionesACargo().contains(publicacion));
    assertTrue(repo.publicacionesMascotas().contains(publicacion));

    repo.removerAsociacion(asociacion1);
    repo.removerAsociacion(asociacion2);
  }

  @Test
  public void dameLasPublicacionesAprobadas() {
    PublicacionMascotaPerdida publicacionDesaprobada = new PublicacionMascotaPerdida(this.rescatista());
    PublicacionMascotaPerdida publicacionDesaprobada1 = new PublicacionMascotaPerdida(this.rescatista());

    Asociacion unaAsociacion = new Asociacion(10, 10);

    RepositorioDeAsociaciones repo = RepositorioDeAsociaciones.getInstance();
    repo.agregarAsociacion(unaAsociacion);

    repo.agregarPublicacion(publicacionDesaprobada1);
    repo.agregarPublicacion(publicacionDesaprobada);

    assertTrue(repo.publicacionesAprobadas().isEmpty());
    repo.removerAsociacion(unaAsociacion);
  }

  @Test
  public void tiraErrorSiNoHayAsociaciones() {
    RepositorioDeAsociaciones repo = RepositorioDeAsociaciones.getInstance();

    PublicacionMascotaPerdida unaPublicacion = new PublicacionMascotaPerdida(this.rescatista());

    assertThrows(NoHayNingunaAsociasionException.class, () -> repo.agregarPublicacion(unaPublicacion));
  }

  public void settearColorPrincipal(MascotaBuilder mascotaBuilder, String color) {
    mascotaBuilder.agregarNuevaCaracteristica("Color principal", color);
  }

  public void settearCastrado(MascotaBuilder mascotaBuilder, Boolean estado) {
    mascotaBuilder.agregarNuevaCaracteristica("Esta castrado", estado);
  }

  public Mascota mascota() {
    MascotaBuilder mascotaBuilder = new MascotaBuilder();
    mascotaBuilder.setNombre("Sergio Ramos");
    mascotaBuilder.setApodo("Noventa y ramos");
    mascotaBuilder.setEspecie(Especie.PERRO);
    mascotaBuilder.setDescripcion("Un jugador de futbol del real madrid");
    mascotaBuilder.setEdad((short) 35);
    mascotaBuilder.setSexo(Sexo.MACHO);
    mascotaBuilder.agregarImagen("https://upload.wikimedia.org/wikipedia/commons/4/43/Russia-Spain_2017_%286%29.jpg");
    this.settearColorPrincipal(mascotaBuilder, "Blanco");
    return mascotaBuilder.finalizarMascota();
  }

  public MascotaPerdida mascotaPerdida() {
    String descripcion = "Estaba intentando marcar a Messi";
    List<Image> fotos = new ArrayList<>();

    ImageIcon _foto1 = new ImageIcon("https://images.ole.com.ar/2020/12/31/SiSmW6L4M_340x340__1.jpg");
    Image foto1 = _foto1.getImage();
    fotos.add(foto1);

    ImageIcon _foto2 = new ImageIcon("https://www.diez.hn/csp/mediapool/sites/dt.common.streams.StreamServer.cls?STREAMOID=GCN_RhtV099DtwjsK8LR3c$daE2N3K4ZzOUsqbU5sYuLAvgewimt_YtK9twCcBxuWCsjLu883Ygn4B49Lvm9bPe2QeMKQdVeZmXF$9l$4uCZ8QDXhaHEp3rvzXRJFdy0KqPHLoMevcTLo3h8xh70Y6N_U_CryOsw6FTOdKL_jpQ-&CONTENTTYPE=image/jpeg");
    Image foto2 = _foto2.getImage();
    fotos.add(foto2);

    return new MascotaPerdida(descripcion, fotos, 0, 0);
  }

  public Rescatista rescatista() {
    PersonaBuilder personaBuilder = new PersonaBuilder();
    personaBuilder.setNombreYApellido("Cristiano Ronaldo");
    personaBuilder.setFechaNacimiento(LocalDate.of(1985, 2, 5));
    Contacto metodoContacto = new Contacto("CR7", 1211113333, "cristiano@ronaldo.com");
    personaBuilder.agregarContacto(metodoContacto);
    personaBuilder.agregarEmailSender(emailSender);
    personaBuilder.agregarSmsSender(smsSender);
    return new Rescatista(personaBuilder.crearPersona(), LocalDate.now(), this.mascotaPerdida());
  }

  public Duenio duenio() {
    PersonaBuilder personaBuilder = new PersonaBuilder();
    personaBuilder.setNombreYApellido("Lionel Andres Messi");
    personaBuilder.setFechaNacimiento(LocalDate.of(1987, 6, 24));
    Contacto metodoContacto = new Contacto("Lionel Messi", 112222333, "messi@messi.com");
    personaBuilder.agregarContacto(metodoContacto);
    personaBuilder.agregarEmailSender(emailSender);
    personaBuilder.agregarSmsSender(smsSender);
    Duenio duenio = new Duenio(personaBuilder.crearPersona());
    duenio.agregarMascota(this.mascota());
    return duenio;
  }
}
