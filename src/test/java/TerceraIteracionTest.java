import apis.JavaXMail;
import apis.MedioNotificacion;
import apis.TwilioJava;
import mascotas.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import personas.*;
import repositorios.RepositorioDeUsuarios;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;

public class TerceraIteracionTest {

  MedioNotificacion emailSender = mock(JavaXMail.class);
  MedioNotificacion smsSender = mock(TwilioJava.class);



  @Test
  @Disabled // no funciona aun
  public void sePuedeEncontrarElDuenioDeUnaMascota(){
      Usuario usuario = new Usuario("unusario ", "hola 123", duenio());
      RepositorioDeUsuarios repo = RepositorioDeUsuarios.getInstance();
      repo.agregarUsuario(usuario);

      Assertions.assertEquals( repo.usuarioDuenioDe(mascota()) , duenio());

      repo.removerUsuario(usuario);
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


}
