package primeraIteracion.seguridad;

import primeraIteracion.exceptions.EsContraseniaDebilException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VerificarQueEsContraseniaFuerte implements Validacion {
  public void validar(String contrasenia) throws IOException {
    Stream<String> top = Files.lines(Paths.get("recursos\\xato-net-10-million-passwords-10000.txt"));
    List<Boolean> temp = top.map(p -> p.contentEquals(contrasenia)).collect(Collectors.toList());
    if (temp.contains(true)) {
      throw new EsContraseniaDebilException("Es una contraseña debil , piense otra");
    }
  }
}
