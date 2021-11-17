import exceptions.EsContraseniaCortaException;
import exceptions.EsContraseniaDebilException;
import exceptions.NoEsContraseniaAlfanumericaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repositorios.RepositorioDeUsuarios;
import seguridad.*;

public class ContraseniasTest {


  Validacion validacionAlfamerica = new ContraseniaAlfanumerica();//lo puse afuera del BeforeAll xq no me agarra la variable
  Validacion validacionLargo = new ContraseniaLarga();//lo puse afuera del BeforeAll xq no me agarra la variable
  Validacion validacionFuerza = new ContraseniaFuerte();//lo puse afuera del BeforeAll xq no me agarra la variable


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

  /*
    @Test
    public void cambioClaveCorrectamente() {
      Admin adminPrueba = new Admin("Jose", "viVaLaPaTrIa_2021");
      RepositorioDeUsuarios.getInstance().agregarAdmin(adminPrueba);
      RepositorioDeUsuarios.getInstance().cambiarClave("Jose", "viVaLaPaTrIa_2021", "aguanteLaBolgnesa_2021");
      Assertions.assertTrue(RepositorioDeUsuarios.getInstance().comprobarClave("Jose", "aguanteLaBolgnesa_2021"));
      RepositorioDeUsuarios.getInstance().removerAdmin(adminPrueba);
    }
  */
  @Test
  public void rebotarComprobacionClaveVacia() {
    Assertions.assertFalse(RepositorioDeUsuarios.getInstance().comprobarClave("Jose", null));
  }


}
