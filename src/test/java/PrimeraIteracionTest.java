
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import primeraIteracion.exceptions.FaltanDatosException;
import primeraIteracion.personas.*;
import primeraIteracion.exceptions.*;
import primeraIteracion.seguridad.ValidacionesDeSeguridad;

import primeraIteracion.mascotas.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrimeraIteracionTest {

  @BeforeAll
  public static void agregarPosiblesCaracteristicas() {
    CaracteristicaBuilder caracteristicaBuilder1 = new CaracteristicaBuilder();
    caracteristicaBuilder1.agregarParametro("Red", new Parametro<Integer>());
    caracteristicaBuilder1.agregarParametro("Green", new Parametro<Integer>());
    caracteristicaBuilder1.agregarParametro("Blue", new Parametro<Integer>());
    Caracteristica color = caracteristicaBuilder1.finalizarCaracteristica();
    PosiblesCaracteristicas.getInstance().agregarPosibleCaracteristica("Color principal",color);

    CaracteristicaBuilder caracteristicaBuilder2 = new CaracteristicaBuilder();
    caracteristicaBuilder2.agregarParametro("Esta castrado", new Parametro<Boolean>());
    Caracteristica estaCastrado = caracteristicaBuilder2.finalizarCaracteristica();
    PosiblesCaracteristicas.getInstance().agregarPosibleCaracteristica("Esta castrado",estaCastrado);

  }

  ValidacionesDeSeguridad validaciones = new ValidacionesDeSeguridad();//lo puse afuera del BeforeAll xq no me agarra la variable

  @Test
  public void unaMascotaVaciaFalla() {
    Assertions.assertThrows(FaltanDatosException.class, () -> new Mascota(null,null,null,null, null, null, null, null));
  }

  @Test
  public void puedoCrearUnaMascotaSinProblema() {
    Assertions.assertNotNull(this.mascota());
  }

  @Test
  public void losDatosDeUnaMascotaSeAsignanCorrectamente(){
    Mascota mascota = this.mascota();
    Assertions.assertEquals("Sergio Ramos",mascota.getNombre());
    Assertions.assertEquals("Noventa y ramos",mascota.getApodo());
    Assertions.assertEquals(Especie.PERRO,mascota.getEspecie());
    Assertions.assertEquals("Un jugador de futbol del real madrid",mascota.getDescripcion());
    Assertions.assertEquals((short) 35,mascota.getEdad());
    Assertions.assertEquals(Sexo.MACHO,mascota.getSexo());
  }

  @Test
  public void puedoCrearUnDuenioSinProblemas() {
    Assertions.assertNotNull(this.duenio());
  }

  @Test
  public void puedoCrearUnaMascotaPerdidaSinProblema() {
    Assertions.assertNotNull(this.mascotaPerdida());
  }

  @Test
  public void laDescripcionDeUnaMascotaPerdidaSeAsignaCorrectamente() {
    MascotaPerdida mascotaPerdida = this.mascotaPerdida();
    Assertions.assertEquals("Estaba intentando marcar a Messi",mascotaPerdida.getDescripcionEstado());
  }

  @Test
  public void finalizarCarateristicaVaciaDaError() {
    MascotaBuilder mascotaBuilder = new MascotaBuilder();
    Assertions.assertThrows(FaltanDatosException.class, () -> mascotaBuilder.finalizarNuevaCaracteristica());
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

  @Test void rebotarPerfilVacio() {
    Assertions.assertThrows(FaltanDatosException.class, () -> RepositorioDeUsuarios.getInstance().agregarUsuario("Jose",null));
  }

  @Test
  public void rebotarContraseniaDebil() throws IOException {
    Assertions.assertThrows(EsContraseniaDebilException.class ,() ->  validaciones.verificarQueEsContraseniaFuerte("blitz")); //Esa es la ultima del txt
  }

  @Test
  public void rebotarContraseniaDebil2() throws IOException {
    Assertions.assertThrows(EsContraseniaDebilException.class,() -> RepositorioDeUsuarios.getInstance().agregarUsuario("Maria",new Usuario("blitz",this.duenio()))); //Esa es la ultima del txt
  }

  @Test
  public void noRebotarContraseniaFuerte() throws IOException {
    Assertions.assertDoesNotThrow(() ->validaciones.verificarQueEsContraseniaFuerte("2021/05/06_PNW")); //Esa es una que no esta en el txt
  }

  @Test
  public void noRebotarContraseniaFuerte2() throws IOException {
    Assertions.assertDoesNotThrow(() -> RepositorioDeUsuarios.getInstance().agregarUsuario("Jose",new Admin("viVaLaPaTrIa"))); //Esa es una que no esta en el txt
  }

  @Test
  public void rebotarContraseniaCorta() {
    Assertions.assertThrows(EsContraseniaCortaException.class ,() ->  validaciones.verificarQueEsContraseniaLarga("c_corta"));
  }

  @Test
  public void noRebotarContraseniaLarga() {
    Assertions.assertDoesNotThrow(() ->  validaciones.verificarQueEsContraseniaLarga("c_laaaaaaaaaaarga"));
  }

  @Test
  public void rebotarContraseniaNoAlfanumerica1() {
    Assertions.assertThrows(NoEsContraseniaAlfanumericaException.class ,() ->  validaciones.verificarQueEsContraseniaAlfanumerica("soloLetras"));
  }

  @Test
  public void rebotarContraseniaNoAlfanumerica2() {
    Assertions.assertThrows(NoEsContraseniaAlfanumericaException.class ,() ->  validaciones.verificarQueEsContraseniaAlfanumerica("soloNumeros"));
  }
  @Test
  public void rebotarContraseniaNoAlfanumerica3() {
    Assertions.assertThrows(NoEsContraseniaAlfanumericaException.class ,() ->  validaciones.verificarQueEsContraseniaAlfanumerica("!$%&/()=?¿+*-_:;{}[]"));
  }

  @Test
  public void noRebotarContraseniaAlfanumerica() {
    Assertions.assertDoesNotThrow(() ->  validaciones.verificarQueEsContraseniaAlfanumerica("letras_y_numeros(148)"));
  }

  @Test
  public void cambioClaveCorrectamente() throws IOException {
    RepositorioDeUsuarios.getInstance().cambiarClave("Jose","viVaLaPaTrIa", "aguanteLaBolgnesa");
    Assertions.assertTrue(RepositorioDeUsuarios.getInstance().iniciarSesion("Jose","aguanteLaBolgnesa"));
  }

  @Test
  public void rebotarInicioDeSesionVacio() {
    Assertions.assertFalse(RepositorioDeUsuarios.getInstance().iniciarSesion("Jose",null));
  }

  public void settearColorPrincipal(MascotaBuilder mascotaBuilder, int red, int green, int blue) {
    mascotaBuilder.agregarNuevaCaracteristica("Color principal");
    mascotaBuilder.rellenarParametroCaracteristica("Red", red);
    mascotaBuilder.rellenarParametroCaracteristica("Green", green);
    mascotaBuilder.rellenarParametroCaracteristica("Blue", blue);
    mascotaBuilder.finalizarNuevaCaracteristica();
  }

  public void settearCastrado(MascotaBuilder mascotaBuilder, Boolean estado) {
    mascotaBuilder.agregarNuevaCaracteristica("Esta castrado");
    mascotaBuilder.rellenarParametroCaracteristica("Esta castrado", estado);
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
    this.settearColorPrincipal(mascotaBuilder,1000,1000,1000);
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

    MascotaPerdida unaMascotaPerdida = new MascotaPerdida(descripcion, fotos, 12345,54321);
    return unaMascotaPerdida;
  }

  public Rescatista rescatista() {
    RescatistaBuilder rescatistaBuilder = new RescatistaBuilder();
    rescatistaBuilder.setNombreYApellido("Cristiano Ronaldo");
    rescatistaBuilder.setFechaNacimiento(LocalDate.of(1985,2,5));
    Contacto metodoContacto = new Contacto("CR7", 1211113333,"cristiano@ronaldo.com");
    rescatistaBuilder.agregarContacto(metodoContacto);
    rescatistaBuilder.setFecha(LocalDate.now());
    MascotaPerdida unaMascota = this.mascotaPerdida();
    rescatistaBuilder.setMascota(unaMascota);
    return rescatistaBuilder.crearPersona();
  }

  public Duenio duenio() {
    DuenioBuilder duenioBuilder = new DuenioBuilder();
    duenioBuilder.setNombreYApellido("Lionel Andres Messi");
    duenioBuilder.setFechaNacimiento(LocalDate.of(1987,6,24));
    Contacto metodoContacto = new Contacto("Lionel Messi", 112222333,"messi@messi.com");
    duenioBuilder.agregarContacto(metodoContacto);
    duenioBuilder.agregarMascota(this.mascota());
    return duenioBuilder.crearPersona();
  }



}
