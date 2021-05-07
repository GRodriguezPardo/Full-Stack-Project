package primeraIteracion.seguridad;

/*
Es una clase sin estado (sin atributos) , al menos inicialmente , por lo que no se requiere usar un
singleton en ella.No importa que se pueden crear varias instancias.
*/


import primeraIteracion.exceptions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidacionesDeSeguridad {



  /*El txt de las contrasenias es bajado de aca https://github.com/danielmiessler/SecLists/tree/master/Passwords, esta linkeado en el
* pdf de owasp.Hay dos archivos que podian ser , por el nombre.El de darkweb me parecio que no y baje el otro.*/

  public void verificarQueEsContraseniaFuerte(String contrasenia) throws IOException {
    Stream<String> top = Files.lines(Paths.get("recursos\\xato-net-10-million-passwords-10000.txt"));
    List<Boolean> temp= top.map(p -> p.contentEquals(contrasenia)).collect(Collectors.toList()) ;
    if(temp.contains(true)) {
     throw new EsContraseniaDebilException("Es una contrasenia debil , piense otra");
    }
  }

  public void verificarQueEsContraseniaLarga(String contrasenia){
    if(contrasenia.length() < 8){
      throw  new EsContraseniaCortaException("La contrasenia debe tener al menos 8 caracteres");
    }
  }

}
