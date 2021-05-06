package primeraIteracion.seguridad;

/*
Es una clase sin estado (sin atributos) , al menos inicialmente , por lo que no se requiere usar un
singleton en ella.No importa que se pueden crear varias instancias.
*/


import java.io.FileNotFoundException;
import java.io.FileReader;

public class ValidacionesDeSeguridad {



/*El txt de las contrasenias es bajado de aca https://github.com/danielmiessler/SecLists/tree/master/Passwords, esta linkeado en el
* pdf de owasp.Hay dos archivos que podian ser , por el nombre.El de darkweb me parecio que no y baje el otro.*/
  void verificarQueEsContraseniaFuerte(String contrasenia) throws FileNotFoundException {
    FileReader input = new FileReader("recursos\\darkweb2017-top10000.txt");


  }



}
