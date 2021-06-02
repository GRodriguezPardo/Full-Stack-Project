

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

import mascotas.*;
import personas.*;

import javax.swing.*;
import java.awt.Image;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SegundaIteracionTest {

  @BeforeAll
  public static void agregarPosiblesCaracteristicas() {
    PosiblesCaracteristicas.getInstance().agregarPosibleCaracteristica("Color principal",new Caracteristica<String>());
    PosiblesCaracteristicas.getInstance().agregarPosibleCaracteristica("Esta castrado",new Caracteristica<Boolean>());
  }

  @Test
  public void PuedoEncontrarAlDuenioDeUnaMascota() {
    Mascota mascota = this.mascota();
    Duenio duenio = this.duenio();
    RepositorioDeUsuarios repoUsuarios = RepositorioDeUsuarios.getInstance();
    duenio.agregarMascota(mascota);
    Usuario usuario = new Usuario("Mirtha", "CARAJOMIERDA", duenio);
    repoUsuarios.agregarPerfil(usuario);

    assertEquals(duenio, repoUsuarios.duenioDe(mascota));
    
    repoUsuarios.removerPerfil(usuario);
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
    this.settearColorPrincipal(mascotaBuilder,"Blanco");
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

    return new MascotaPerdida(descripcion, fotos, 12345,54321);
  }

  public Rescatista rescatista() {
    PersonaBuilder personaBuilder = new PersonaBuilder();
    personaBuilder.setNombreYApellido("Cristiano Ronaldo");
    personaBuilder.setFechaNacimiento(LocalDate.of(1985,2,5));
    Contacto metodoContacto = new Contacto("CR7", 1211113333,"cristiano@ronaldo.com");
    personaBuilder.agregarContacto(metodoContacto);
    return new Rescatista(personaBuilder.crearPersona(),LocalDate.now(),this.mascotaPerdida());
  }

  public Duenio duenio() {
    PersonaBuilder personaBuilder = new PersonaBuilder();
    personaBuilder.setNombreYApellido("Lionel Andres Messi");
    personaBuilder.setFechaNacimiento(LocalDate.of(1987,6,24));
    Contacto metodoContacto = new Contacto("Lionel Messi", 112222333,"messi@messi.com");
    personaBuilder.agregarContacto(metodoContacto);
    Duenio duenio = new Duenio(personaBuilder.crearPersona());
    duenio.agregarMascota(this.mascota());
    return duenio;
  }
}
