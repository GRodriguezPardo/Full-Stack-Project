package seguridad;

import exceptions.EsContraseniaCortaException;

public class VerificarQueEsContraseniaLarga implements Validacion {
  public void validar(String contrasenia) {
    if (contrasenia.length() < 8) {
      throw new EsContraseniaCortaException("La contraseña debe tener al menos 8 caracteres");
    }
  }
}