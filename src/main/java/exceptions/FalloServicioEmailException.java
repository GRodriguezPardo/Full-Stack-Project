package exceptions;

public class FalloServicioEmailException extends RuntimeException {
  public FalloServicioEmailException(Exception excepcion) {
    super(excepcion);
  }
}
