package primeraIteracion.personas;

import primeraIteracion.exceptions.FaltanDatosException;
import primeraIteracion.mascotas.MascotaPerdida;

import java.util.HashMap;
import java.util.Objects;


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
     * @return retorna al singleton.
     */
    public static RepositorioDeUsuarios getInstance() { return INSTANCE; }


    /**
     * Permite agregar un usuario y clave a la lista del singleton
     *
     */
    public void agregarUsuario(String usuario, String clave) {
        if(Objects.isNull(usuario) || Objects.isNull(clave)){
            throw new FaltanDatosException("Se debe proveer un usuario y una contraseña");
        }
        if(usuarioYClave.containsKey(usuario)) {
            //TODO.
            // throw new excpetion usuarioExistenteException("Nombre de usuario tomado, elegir otro");
        }
        this.usuarioYClave.put(usuario, clave);
    }

    /**
     * Permite a comprobar las credenciales de un usuario.
     *
     * @param usuario es el nombre de usuario.
     * @param clave es la contraseña del usuario.
     * @return es el resultado de la comprobacion.
     */
    public Boolean iniciarSesion(String usuario, String clave) {
        if(this.usuarioYClave.containsKey(usuario)
            && this.usuarioYClave.get(usuario).equals(clave)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Permite a un usuario cambiar su contraseña.
     *
     * @param usuario es el usuario cuya contraseña cambiara.
     * @param claveVieja es la anterior clave del usuario, necesaria para
     *                   comprobar sus credenciales.
     * @param claveNueva es la nueva clave.
     */
    public void cambiarClave(String usuario, String claveVieja, String claveNueva) {
        if(this.iniciarSesion(usuario,claveVieja)){
            this.usuarioYClave.replace(usuario,claveNueva);
        } else {
            //Todo.
            // Throw new exception datosErroneosException("usuario o contraseña erroneos")
        }
    }
}
