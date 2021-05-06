package primeraIteracion.mascotas;

import java.util.HashMap;
import java.util.Objects;
import primeraIteracion.exceptions.FaltanDatosException;

/**
 * Builder de la clase Mascota.
 */
public class MascotaBuilder {
  private Especie especie;
  private String nombre;
  private String apodo;
  private Short edad;
  private Sexo sexo;
  private String descripcion;
  //private List<Foto> fotos;
  private HashMap<String, Caracteristica> caracteristicas = new HashMap<>();
  private Caracteristica caracteristicaEnCreacion;
  private String nombreCaracteristicaEnCreacion;

  /**
   * Constructor vacio propio de un builder sin variables.
   */
  public MascotaBuilder() {

  }

  /**
   * Setter de la especie que tendra la mascota a crear.
   *
   * @param especie es el valor que se le asignara a especie.
   */
  public void setEspecie(Especie especie) {
    this.especie = especie;
  }

  /**
   * Setter del nombre que tendra la mascota a crear.
   *
   * @param nombre es el valor que se le asignara a nombre.
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * Setter del apodo que tendra la mascota a crear.
   *
   * @param apodo es el valor que se le asignara a apodo.
   */
  public void setApodo(String apodo) {
    this.apodo = apodo;
  }

  /**
   * Setter de la edad aproximada que tendra la mascota a crear.
   *
   * @param edad es el valor que se le asignara a edad.
   */
  public void setEdad(Short edad) {
    this.edad = edad;
  }

  /**
   * Setter del sexo que tendra la mascota a crear.
   *
   * @param sexo es el valor que se le asignara a sexo.
   */
  public void setSexo(Sexo sexo) {
    this.sexo = sexo;
  }

  /**
   * Setter de la descripcion fisica que tendra la mascota a crear.
   *
   * @param descripcion es el valor que se le asignara a descripcion.
   */
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  /**
   * Empieza el proceso de agregado de una caracteristica obteniendo una plantilla
   * de la misma del repositorio de PosiblesCaracteristicas, y guardandola en un
   * atributo local.
   *
   * @param nombre es el nombre que referencia a la caracteristica en el repositorio.
   */
  public void agregarNuevaCaracteristica(String nombre) {
    this.nombreCaracteristicaEnCreacion = nombre;
    this.caracteristicaEnCreacion =
        PosiblesCaracteristicas.getInstance()
        .definirCaracteristica(nombre);
  }

  /**
   * Instancia uno de los parametros de la caracteristica en creacion guardada en
   * el atributo local, identificandolo por su nombre y asignandole un valor.
   *
   * @param nombre es el nombre del parametro a instanciar.
   * @param valor es el valor que tomara el parametro.
   */
  public void rellenarParametroCaracteristica(String nombre, Object valor) {
    this.caracteristicaEnCreacion.modificarParametro(nombre,valor);
  }

  /**
   * Finaliza la instanciacion de una nueva caracteristica que tendra la mascota,
   * agregando la caracteristica al hash de caracteristicas luego de haber chequeado
   * que se hayan instanciado todos sus parametros.
   * Luego resetea en null a los atributos locales que contenian a la creacion en progreso
   * de la caracteristica.
   */
  public void finalizarNuevaCaracteristica() {
    if(Objects.isNull(this.caracteristicaEnCreacion)){
      throw new FaltanDatosException(
              "Faltan caracteristicas"
      );
    }
    this.caracteristicas
        .put(this.nombreCaracteristicaEnCreacion, this.caracteristicaEnCreacion);
    this.nombreCaracteristicaEnCreacion = null;
    this.caracteristicaEnCreacion = null;
  }

  /**
   * Finaliza la creacion de la mascota luego de chequear que tenga los atributos
   * minimos instanciados.
   *
   * @return retorna la mascota creada.
   */
  public Mascota finalizarMascota() {
     if(Objects.isNull(this.nombre)
       || Objects.isNull(this.edad)
       || Objects.isNull(this.sexo)
       || Objects.isNull(this.descripcion)){
      throw new FaltanDatosException(
          "Se debe proveer un nombre, una edad aproximada, el sexo, y una descripcion"
      );
    }
    return new Mascota(
         this.especie,
         this.nombre,
         this.apodo,
         this.edad,
         this.sexo,
         this.descripcion,
         this.caracteristicas);
  }
}
