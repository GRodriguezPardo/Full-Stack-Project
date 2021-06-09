import apis.*;
import exceptions.EsContraseniaCortaException;
import exceptions.EsContraseniaDebilException;
import exceptions.FaltanDatosException;
import exceptions.NoEsContraseniaAlfanumericaException;
import mascotas.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import personas.*;
import repositorios.RepositorioDeRescates;
import repositorios.RepositorioDeUsuarios;
import seguridad.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class PrimeraIteracionTest {
  MedioNotificacion emailSender = mock(JavaXMail.class);
  MedioNotificacion smsSender = mock(TwilioJava.class);


  Validacion validacionAlfamerica = new ContraseniaAlfanumerica();//lo puse afuera del BeforeAll xq no me agarra la variable
  Validacion validacionLargo = new ContraseniaLarga();//lo puse afuera del BeforeAll xq no me agarra la variable
  Validacion validacionFuerza = new ContraseniaFuerte();//lo puse afuera del BeforeAll xq no me agarra la variable

  @BeforeAll
  public static void agregarPosiblesCaracteristicas() {
    PosiblesCaracteristicas.getInstance().agregarPosibleCaracteristica("Color principal", new Caracteristica<String>());
    PosiblesCaracteristicas.getInstance().agregarPosibleCaracteristica("Esta castrado", new Caracteristica<Boolean>());
  }

  @Test
  public void unaMascotaVaciaFalla() {
    Assertions.assertThrows(FaltanDatosException.class, () -> new Mascota(null, null, null, null, null, null, null, null));
  }

  @Test
  public void losContactosSeCreanSinProblema() {
    Contacto metodoContacto = new Contacto("Lionel Messi", "112222333", "messi@messi.com");
    Assertions.assertEquals("Lionel Messi",metodoContacto.getNombreApellido());
    Assertions.assertEquals("112222333",metodoContacto.getTelefono());
    Assertions.assertEquals("messi@messi.com",metodoContacto.getEmail());
  }

  @Test
  public void puedoCrearUnaMascotaSinProblema() {
    Assertions.assertNotNull(this.mascota());
  }

  @Test
  public void losDatosDeUnaMascotaSeAsignanCorrectamente() {
    Mascota mascota = this.mascota();
    Assertions.assertEquals("Sergio Ramos", mascota.getNombre());
    Assertions.assertEquals("Noventa y ramos", mascota.getApodo());
    Assertions.assertEquals(Especie.PERRO, mascota.getEspecie());
    Assertions.assertEquals("Un jugador de futbol del real madrid", mascota.getDescripcion());
    Assertions.assertEquals((short) 35, mascota.getEdad());
    Assertions.assertEquals(Sexo.MACHO, mascota.getSexo());
  }

  @Test
  public void personaSinNombreTiraException() {
    Assertions.assertThrows(FaltanDatosException.class, () -> new Persona(null,null,null, null));
  }

  @Test
  public void personaSinContactosTiraException() {
    Assertions.assertThrows(FaltanDatosException.class, () -> new Persona("jose",LocalDate.now(), new ArrayList<>(), null));
  }

  @Test
  public void personaSinDependenciasTiraException() {
    List<Contacto> contactos = new ArrayList<>();
    contactos.add(new Contacto("jose", "222", "jose"));
    Assertions.assertThrows(FaltanDatosException.class, () -> new Persona("jose",
        LocalDate.now(), contactos,
        null));
  }

  @Test
  public void puedoCrearUnDuenioSinProblemas() {
    Assertions.assertNotNull(this.duenio());
  }

  @Test
  public void duenioSeAsignaSinProblemaAUsuario() {
    Duenio duenio = this.duenio();
    Assertions.assertEquals(new Usuario("Jose", "sito", duenio).getDuenio(), duenio);
  }

  @Test
  public void puedoCrearUnaMascotaPerdidaSinProblema() {
    Assertions.assertNotNull(this.mascotaPerdida());
  }

  @Test
  public void laDescripcionDeUnaMascotaPerdidaSeAsignaCorrectamente() {
    MascotaPerdida mascotaPerdida = this.mascotaPerdida();
    Assertions.assertEquals("Estaba intentando marcar a Messi", mascotaPerdida.getDescripcionEstado());
  }

  @Test
  public void agregarCarateristicaVaciaDaError() {
    MascotaBuilder mascotaBuilder = new MascotaBuilder();
    Assertions.assertThrows(FaltanDatosException.class, () -> mascotaBuilder.agregarNuevaCaracteristica(null, null));
  }

  @Test
  public void puedoCrearUnRescatistaSinProblema() {
    Assertions.assertNotNull(this.rescatista());
  }

  @Test
  public void puedoAgregarUnRescateAlRepositorioDeRescatesYVerloEnLosUltimos10Dias() {
    RepositorioDeRescates.getInstance().agregarRescate(this.rescatista());
    Assertions.assertFalse(RepositorioDeRescates.getInstance().mascotasEncontradaEnLosDias(10).isEmpty());
  }

  @Test
  void rebotarPerfilVacio() {
    Assertions.assertThrows(FaltanDatosException.class, () -> RepositorioDeUsuarios.getInstance().agregarPerfil(null));
  }

  @Test
  public void rebotarContraseniaDebil() {
    Assertions.assertThrows(EsContraseniaDebilException.class, () -> validacionFuerza.validar("blitz")); //Esa es la ultima del txt
  }

  @Test
  public void noRebotarContraseniaFuerte() {
    Assertions.assertDoesNotThrow(() -> validacionFuerza.validar("2021/05/06_PNW")); //Esa es una que no esta en el txt
  }

  @Test
  public void rebotarContraseniaCorta() {
    Assertions.assertThrows(EsContraseniaCortaException.class, () -> validacionLargo.validar("c_corta"));
  }

  @Test
  public void noRebotarContraseniaLarga() {
    Assertions.assertDoesNotThrow(() -> validacionLargo.validar("c_laaaaaaaaaaarga"));
  }

  @Test
  public void rebotarContraseniaNoAlfanumerica1() {
    Assertions.assertThrows(NoEsContraseniaAlfanumericaException.class, () -> validacionAlfamerica.validar("soloLetras"));
  }

  @Test
  public void rebotarContraseniaNoAlfanumerica2() {
    Assertions.assertThrows(NoEsContraseniaAlfanumericaException.class, () -> validacionAlfamerica.validar("soloNumeros"));
  }

  @Test
  public void rebotarContraseniaNoAlfanumerica3() {
    Assertions.assertThrows(NoEsContraseniaAlfanumericaException.class, () -> validacionAlfamerica.validar("!$%&/()=?¿+*-_:;{}[]"));
  }

  @Test
  public void noRebotarContraseniaAlfanumerica() {
    Assertions.assertDoesNotThrow(() -> validacionAlfamerica.validar("letras_y_numeros(148)"));
  }

  @Test
  public void noRebotarContraseniaFuerteLargaYalfanumerica() {
    Validaciones validaciones = new Validaciones();
    Assertions.assertDoesNotThrow(() -> validaciones.hacerValidaciones("viVaLaPaTrIa_2021")); //Esa es una que no esta en el txt
  }

  @Test
  public void cambioClaveCorrectamente() {
    Perfil perfilPrueba = new Admin("Jose", "viVaLaPaTrIa_2021");
    RepositorioDeUsuarios.getInstance().agregarPerfil(perfilPrueba);
    RepositorioDeUsuarios.getInstance().cambiarClave("Jose", "viVaLaPaTrIa_2021", "aguanteLaBolgnesa_2021");
    Assertions.assertTrue(RepositorioDeUsuarios.getInstance().comprobarClave("Jose", "aguanteLaBolgnesa_2021"));
    RepositorioDeUsuarios.getInstance().removerPerfil(perfilPrueba);
  }

  @Test
  public void rebotarComprobacionClaveVacia() {
    Assertions.assertFalse(RepositorioDeUsuarios.getInstance().comprobarClave("Jose", null));
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
    this.settearCastrado(mascotaBuilder,false);
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

    return new MascotaPerdida(descripcion, fotos, new Posicion(12345,54321));
  }

  public Rescatista rescatista() {
    PersonaBuilder personaBuilder = new PersonaBuilder();
    personaBuilder.setNombreYApellido("Cristiano Ronaldo");
    personaBuilder.setFechaNacimiento(LocalDate.of(1985, 2, 5));
    Contacto metodoContacto = new Contacto("CR7", "1211113333", "cristiano@ronaldo.com");
    personaBuilder.agregarContacto(metodoContacto);
    personaBuilder.agregarMedioNotificacion(emailSender);
    Rescatista rescatista = new Rescatista(personaBuilder.crearPersona(), LocalDate.now(), this.mascotaPerdida());
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
    duenio.agregarMascota(this.mascota());
    duenio.getPersona().agregarContacto(new Contacto("Anto", "222", "Anto@Anto.com"));
    return duenio;
  }
}
