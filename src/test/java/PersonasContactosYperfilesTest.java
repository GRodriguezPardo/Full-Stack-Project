import exceptions.FaltanDatosException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import personas.Contacto;
import personas.Persona;
import repositorios.RepositorioDeUsuarios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonasContactosYperfilesTest {

  @Test
  void rebotarPerfilVacio() {
    Assertions.assertThrows(FaltanDatosException.class, () -> RepositorioDeUsuarios.getInstance().agregarUsuario(null));
  }


  @Test
  public void losContactosSeCreanSinProblema() {
    Contacto metodoContacto = new Contacto("Lionel Messi", "112222333", "messi@messi.com");
    Assertions.assertEquals("Lionel Messi", metodoContacto.getNombreApellido());
    Assertions.assertEquals("112222333", metodoContacto.getTelefono());
    Assertions.assertEquals("messi@messi.com", metodoContacto.getEmail());
  }

  @Test
  public void personaSinNombreTiraException() {
    Assertions.assertThrows(FaltanDatosException.class, () -> new Persona(null, null, null, null));
  }

  @Test
  public void personaSinContactosTiraException() {
    Assertions.assertThrows(FaltanDatosException.class, () -> new Persona("jose", LocalDate.now(), new ArrayList<>(), null));
  }

  @Test
  public void personaSinDependenciasTiraException() {
    List<Contacto> contactos = new ArrayList<>();
    contactos.add(new Contacto("jose", "222", "jose"));
    Assertions.assertThrows(FaltanDatosException.class, () -> new Persona("jose",
            LocalDate.now(), contactos,
            null));
  }


}
