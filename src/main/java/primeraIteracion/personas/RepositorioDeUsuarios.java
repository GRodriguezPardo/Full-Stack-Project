package primeraIteracion.personas;

import primeraIteracion.mascotas.MascotaPerdida;

import java.util.HashMap;


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
        this.usuarioYClave.put(usuario, clave);
    }

}
