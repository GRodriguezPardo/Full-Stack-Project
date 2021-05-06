package primeraIteracion.personas;

import primeraIteracion.exceptions.FaltanDatosException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;


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
    public void agregarUsuario(String usuario, String clave) {
        if (Objects.isNull(usuario) || Objects.isNull(clave)) {
            throw new FaltanDatosException("Se debe proveer un usuario y una contraseña");
        }
        if (usuarioYClave.containsKey(usuario)) {
            //TODO.
            // throw new excpetion usuarioExistenteException("Nombre de usuario tomado, elegir otro");
        }
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
    public void cambiarClave(String usuario, String claveVieja, String claveNueva) {
        if (this.iniciarSesion(usuario, claveVieja)) {
                if(this.comprobarSeguridadClave(claveNueva)){
                this.usuarioYClave.replace(usuario, claveNueva);
            }
        } else {
            //Todo.
            // Throw new exception datosErroneosException("usuario o contraseña erroneos")
        }
    }

    /**
     * Comprueba que la clave no este en el top 10.000 claves mas comunes.
     * Dichas claves se encuentran en un .txt en
     * /otherFiles/10-million-password-list-top-10000.txt
     * En caso de que el archivo desaparezca retorna false.
     *
     * @param clave es la clave cuya seguridad se validara.
     * @return retorna si la clave es segura, es decir, si no la encontro en el archivo.
     */
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

}
