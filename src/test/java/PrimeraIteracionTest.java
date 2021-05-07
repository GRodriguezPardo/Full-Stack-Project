
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import primeraIteracion.seguridad.ValidacionesDeSeguridad;

import primeraIteracion.mascotas.*;
import primeraIteracion.personas.Contacto;
import primeraIteracion.personas.DuenioBuilder;
import primeraIteracion.personas.RescatistaBuilder;

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
  public void puedoCrearUnaMascotaSinProblema() {
    Assertions.assertNotNull(this.sergioRamos());
  }

  @Test
  public void puedoCrearUnDuenioSinProblemas() {
    DuenioBuilder duenioBuilder = new DuenioBuilder();
    duenioBuilder.setNombreYApellido("Lionel Andres Messi");
    duenioBuilder.setFechaNacimiento(LocalDate.of(1987,6,24));
    Contacto metodoContacto = new Contacto("Lionel Messi", 112222333,"messi@messi.com");
    duenioBuilder.agregarContacto(metodoContacto);
    duenioBuilder.agregarMascota(this.sergioRamos());
    Assertions.assertNotNull(duenioBuilder.crearPersona());
  }

  @Test
  public void puedoCrearUnaMascotaPerdidaSinProblema() {
    Assertions.assertNotNull(this.mascotaPerdida());
  }

  @Test
  public void puedoCrearUnRescatistaSinProblema() {
    RescatistaBuilder rescatistaBuilder = new RescatistaBuilder();
    rescatistaBuilder.setNombreYApellido("Cristiano Ronaldo");
    rescatistaBuilder.setFechaNacimiento(LocalDate.of(1985,2,5));
    Contacto metodoContacto = new Contacto("CR7", 1211113333,"cristiano@ronaldo.com");
    rescatistaBuilder.agregarContacto(metodoContacto);
    rescatistaBuilder.setFecha(LocalDate.now());
    MascotaPerdida unaMascota = this.mascotaPerdida();
    rescatistaBuilder.setMascota(unaMascota);
    Assertions.assertNotNull(rescatistaBuilder.crearPersona());
  }


  public void settearColorPrincipal(MascotaBuilder mascotaBuilder, int red, int green, int blue) {
    mascotaBuilder.agregarNuevaCaracteristica("Color principal");
    mascotaBuilder.rellenarParametroCaracteristica("Red", red);
    mascotaBuilder.rellenarParametroCaracteristica("Green", green);
    mascotaBuilder.rellenarParametroCaracteristica("Blue", blue);
  }

  public void settearCastrado(MascotaBuilder mascotaBuilder, Boolean estado) {
    mascotaBuilder.agregarNuevaCaracteristica("Esta castrado");
    mascotaBuilder.rellenarParametroCaracteristica("Esta castrado", estado);
  }

  public Mascota sergioRamos() {
    MascotaBuilder mascotaBuilder = new MascotaBuilder();
    mascotaBuilder.setNombre("Sergio Ramos");
    mascotaBuilder.setEspecie(Especie.PERRO);
    mascotaBuilder.setDescripcion("Un jugador de futbol del real madrid");
    mascotaBuilder.setEdad((short) 35);
    mascotaBuilder.setSexo(Sexo.MASCULINO);
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

    MascotaPerdida unSergioRamosPerdido = new MascotaPerdida(descripcion, fotos, 12345,54321);
    return unSergioRamosPerdido;
  }


  @Test
  public void rebotarContraseniaDebil() throws IOException {
    Assertions.assertFalse(validaciones.verificarQueEsContraseniaFuerte("blitz")); //Esa es la ultima del txt
  }

  @Test
  public void noRebotarContraseniaFuerte() throws IOException {
    Assertions.assertTrue(validaciones.verificarQueEsContraseniaFuerte("2021/05/06_PNW")); //Esa es una que no esta en el txt
  }

}
