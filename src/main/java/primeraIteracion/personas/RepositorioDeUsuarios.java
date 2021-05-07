package primeraIteracion.personas;

import primeraIteracion.exceptions.EsContraseniaDebilException;
import primeraIteracion.exceptions.FaltanDatosException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Clase singleton cuyo objetivo es guardar los usuarios que se registran.
 */
public class RepositorioDeUsuarios {
    private final static RepositorioDeUsuarios INSTANCE = new RepositorioDeUsuarios();
    private HashMap<String, String> usuarioYClave = new HashMap<>();

    /**
     * Contructor privado al ser singleton.
     */
    private RepositorioDeUsuarios() {
    }

    /**
     * Metodo estatico para obtener al singleton.
     *
     * @return retorna al singleton.
     */
    public static RepositorioDeUsuarios getInstance() {
        return INSTANCE;
    }


    /**
     * Permite agregar un usuario y clave a la lista del singleton
     */
    public void agregarUsuario(String usuario, String clave) throws IOException {
        if (Objects.isNull(usuario) || Objects.isNull(clave)) {
            throw new FaltanDatosException("Se debe proveer un usuario y una contraseña");
        }
        if (usuarioYClave.containsKey(usuario)) {
            //TODO.
            // throw new excpetion usuarioExistenteException("Nombre de usuario tomado, elegir otro");
        }
        this.comprobarSeguridadClave(clave);
        this.usuarioYClave.put(usuario, clave);
    }

    /**
     * Permite a comprobar las credenciales de un usuario.
     *
     * @param usuario es el nombre de usuario.
     * @param clave   es la contraseña del usuario.
     * @return es el resultado de la comprobacion.
     */
    public Boolean iniciarSesion(String usuario, String clave) {
        if(Objects.isNull(clave)){
            return false;
        }
        return this.usuarioYClave.get(usuario).equals(clave);
    }

    /**
     * Permite a un usuario cambiar su contraseña.
     *
     * @param usuario    es el usuario cuya contraseña cambiara.
     * @param claveVieja es la anterior clave del usuario, necesaria para
     *                   comprobar sus credenciales.
     * @param claveNueva es la nueva clave.
     */
    public void cambiarClave(String usuario, String claveVieja, String claveNueva) throws IOException {
        if (this.iniciarSesion(usuario, claveVieja)) {
                this.comprobarSeguridadClave(claveNueva);
                this.usuarioYClave.replace(usuario, claveNueva);
        } else {
            //Todo.
            // Throw new exception datosErroneosException("usuario o contraseña erroneos")
        }
    }

    /*
     * Comprueba que la clave no este en el top 10.000 claves mas comunes.
     * Dichas claves se encuentran en un .txt en
     * /otherFiles/10-million-password-list-top-10000.txt
     * En caso de que el archivo desaparezca retorna false.
     *
     * @param clave es la clave cuya seguridad se validara.
     * @return retorna si la clave es segura, es decir, si no la encontro en el archivo.
     */
    /*
    public Boolean comprobarSeguridadClave(String clave) {
        File peoresClaves = new File("../../../../../otherFiles/10-million-password-list-top-10000.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(peoresClaves);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (clave.equals(line)) {
                return false;
            }
        }
        return true;
    }
     */
    public void comprobarSeguridadClave(String contrasenia) throws IOException {
    Stream<String> top = Files.lines(Paths.get("recursos\\xato-net-10-million-passwords-10000.txt"));
    List<Boolean> temp= top.map(p -> p.contentEquals(contrasenia)).collect(Collectors.toList()) ;
    if(temp.contains(true)) {
     throw new EsContraseniaDebilException("Es una contrasenia debil , piense otra");
    }
  }

}
