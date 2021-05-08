package primeraIteracion.personas;

import primeraIteracion.exceptions.*;
import primeraIteracion.seguridad.Validacion;
import primeraIteracion.seguridad.VerificarQueEsContraseniaAlfanumerica;
import primeraIteracion.seguridad.VerificarQueEsContraseniaFuerte;
import primeraIteracion.seguridad.VerificarQueEsContraseniaLarga;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Clase singleton cuyo objetivo es guardar los usuarios que se registran.
 */
public class RepositorioDeUsuarios {
    private final static RepositorioDeUsuarios INSTANCE = new RepositorioDeUsuarios();
    private HashMap<String, Perfil> usuarioYClave = new HashMap<>();
    private List<Validacion> validaciones = new ArrayList<>();

    /**
     * Contructor privado al ser singleton.
     */
    private RepositorioDeUsuarios() {  /*Agrege las validaciones a la lista para que salga un test donde se corren todas*/
        validaciones.add(new VerificarQueEsContraseniaAlfanumerica());
        validaciones.add(new VerificarQueEsContraseniaFuerte());
        validaciones.add(new VerificarQueEsContraseniaLarga());
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
     * Permite agregar un Usuario y clave a la lista del singleton
     */
    public void agregarUsuario(String usuario, Perfil perfil) throws IOException {
        if (Objects.isNull(usuario) || Objects.isNull(perfil) || Objects.isNull(perfil.getClave())) {
            throw new FaltanDatosException("Se debe proveer un Usuario y una contraseña");
        }
        if (usuarioYClave.containsKey(usuario)) {
            throw new DatosErroneosException("Nombre de Usuario tomado, elegir otro");
        }
        this.comprobarSeguridadClave(perfil.getClave());
        this.usuarioYClave.put(usuario, perfil);
    }

    /**
     * Permite a comprobar las credenciales de un Usuario.
     *
     * @param usuario es el nombre de Usuario.
     * @param clave   es la contraseña del Usuario.
     * @return es el resultado de la comprobacion.
     */
    public Boolean iniciarSesion(String usuario, String clave) {
        if(Objects.isNull(clave)){
            return false;
        }
        return this.usuarioYClave.get(usuario).getClave().equals(clave);
    }

    /**
     * Permite a un Usuario cambiar su contraseña.
     *
     * @param usuario    es el Usuario cuya contraseña cambiara.
     * @param claveVieja es la anterior clave del Usuario, necesaria para
     *                   comprobar sus credenciales.
     * @param claveNueva es la nueva clave.
     */
    public void cambiarClave(String usuario, String claveVieja, String claveNueva) throws IOException {
        if (this.iniciarSesion(usuario, claveVieja)) {
                this.comprobarSeguridadClave(claveNueva);
                this.usuarioYClave.get(usuario).setClave(claveNueva);
        } else {
            throw new DatosErroneosException("Usuario o contraseña erroneos");
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
     */                               /*El try catch esta solo xq el intelliJ no me obligaba a ponerlo , despues habria
     que encontrar una forma de hacer la validacion sin usar cosas que tengan esa excepcion chequeada , SALVO que esto no moleste*/
    public void comprobarSeguridadClave(String contrasenia) throws IOException {
    this.validaciones.forEach(v -> {
        try {
            v.validar(contrasenia);
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
  }
  public void agregarValidacion(Validacion validacion){this.validaciones.add(validacion);}
  public void quitarValidacion(Validacion validacion) {
      if (this.validaciones.contains(validacion)) {
          this.validaciones.remove(validacion);
      }
  }
}
