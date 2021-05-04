package primeraIteracion.mascotas;

import primeraIteracion.exceptions.FaltanDatosException;

import java.util.HashMap;
import java.util.Objects;

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

  public MascotaBuilder() {

  }

  public void setEspecie(Especie especie) {
    this.especie = especie;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setApodo(String apodo) {
    this.apodo = apodo;
  }

  public void setEdad(Short edad) {
    this.edad = edad;
  }

  public void setSexo(Sexo sexo) {
    this.sexo = sexo;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public void agregarNuevaCaracteristica(String nombre) {
    this.nombreCaracteristicaEnCreacion = nombre;
    this.caracteristicaEnCreacion =
        PosiblesCaracteristicas.getInstance()
        .definirCaracteristica(nombre);
  }

  public void rellenarParametroCaracteristica(String nombre, Object valor) {
    this.caracteristicaEnCreacion.modificarParametro(nombre,valor);
  }

  public void finalizarNuevaCaracteristica() {
    if(Objects.isNull(this.caracteristicaEnCreacion)){
      //TODO
    }
    this.caracteristicas
        .put(this.nombreCaracteristicaEnCreacion, this.caracteristicaEnCreacion);
    this.nombreCaracteristicaEnCreacion = null;
    this.caracteristicaEnCreacion = null;
  }

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
