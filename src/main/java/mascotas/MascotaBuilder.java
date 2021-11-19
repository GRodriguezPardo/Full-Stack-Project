package mascotas;

import exceptions.FaltanDatosException;
import repositorios.RepositorioDeCaracteristicas;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * Builder de la clase Mascota.
 */
public class MascotaBuilder {
  private final List<Image> fotos = new ArrayList<>();
  private final Map<String, Caracteristica> caracteristicas = new HashMap<>();
  private Especie especie;
  private String nombre;
  private String apodo;
  private Short edad;
  private Sexo sexo;
  private String descripcion;
  private Tamanio tamanio;

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
   * Agrega una imagen a la lista de fotos de la mascota obtenida del url dado.
   *
   * @param url es el url de la foto.
   */
  public void agregarImagen(String url) {
    ImageIcon _foto = new ImageIcon(url);
    Image foto = _foto.getImage();
    fotos.add(foto);
  }

  /**
   * Agregauna caracteristica al hashMap de caracteristicas buscando la plantilla de
   * PosiblesCaracteristicas, cuyo valor sera el pasado por parametro.
   *
   * @param nombre es el nombre de la caracteristica a agregar.
   * @param valor  es el valor que tendra la caracteristica.
   */
  public void agregarNuevaCaracteristica(String nombre, String valor) {
    if (Objects.isNull(nombre) || Objects.isNull(valor)) {
      throw new FaltanDatosException(
              "Debe proveer un nombre y un valor validos"
      );
    }
    Caracteristica caracteristicaEnCreacion =
            RepositorioDeCaracteristicas.getInstance()
                    .definirCaracteristica(nombre);
    caracteristicaEnCreacion.setValor(valor);
    this.caracteristicas.put(nombre, caracteristicaEnCreacion);
  }

  public void setTamanio(Tamanio tamanio) {
    this.tamanio = tamanio;
  }


  /**
   * Finaliza la creacion de la mascota.
   *
   * @return retorna la mascota creada.
   */
  public Mascota finalizarMascota() {
    return new Mascota(
            this.especie,
            this.nombre,
            this.apodo,
            this.edad,
            this.sexo,
            this.descripcion,
            this.fotos,
            this.caracteristicas,
            this.tamanio
    );
  }
}
