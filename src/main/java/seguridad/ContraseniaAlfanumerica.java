package seguridad;

import exceptions.NoEsContraseniaAlfanumericaException;

public class ContraseniaAlfanumerica implements Validacion {
  public void validar(String contrasenia) {
    boolean tieneNumeros = contrasenia.matches(".*[0-9].*");
    boolean tieneLetras = contrasenia.matches(".*[a-z].*") || contrasenia.matches(".*[A-Z].*");
    if (!tieneLetras || !tieneNumeros) {
      throw new NoEsContraseniaAlfanumericaException("La contrasenia debe ser alfanumerica");
    }
  }
}

