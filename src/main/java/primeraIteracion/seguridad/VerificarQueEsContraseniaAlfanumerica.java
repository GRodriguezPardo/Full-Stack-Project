package primeraIteracion.seguridad;

import primeraIteracion.exceptions.NoEsContraseniaAlfanumericaException;

public class VerificarQueEsContraseniaAlfanumerica implements Validacion {
  public void validar(String contrasenia) {
    boolean tieneNumeros = contrasenia.matches(".*[0-9].*");
    boolean tieneLetras = contrasenia.matches(".*[a-z].*") || contrasenia.matches(".*[A-Z].*");
    if (tieneLetras == false || tieneNumeros == false) {
      throw new NoEsContraseniaAlfanumericaException("La contrasenia debe ser alfanumerica");
    }
  }
}

