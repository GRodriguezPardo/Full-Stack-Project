package apis.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HogarDTO {

  private String id;
  private String nombre;
  private UbicacionDTO ubicacion;
  private String telefono;
  private AdimisionesDTO admisiones;
  private int capacidad;
  private int lugares_disponibles;
  private boolean patio = false;
  private CaracteristicaDTO caracteristica;



  public HogarDTO() {
  }

  @JsonGetter("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @JsonGetter("nombre")
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public UbicacionDTO getUbicacion() {
    return ubicacion;
  }

  @JsonGetter("ubicacion")
  public void setUbicacion(UbicacionDTO ubicacion) {
    this.ubicacion = ubicacion;
  }

  public String getTelefono() {
    return telefono;
  }

  @JsonGetter("telefono")
  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public AdimisionesDTO getAdmisiones() {
    return admisiones;
  }

  public void setAdmisiones(AdimisionesDTO adimisiones) {
    this.admisiones = adimisiones;
  }

  public int getCapacidad() {
    return capacidad;
  }

  public void setCapacidad(int capacidad) {
    this.capacidad = capacidad;
  }

  public int getLugares_disponibles() {
    return lugares_disponibles;
  }

  public void setLugares_disponibles(int lugares_disponibles) {
    this.lugares_disponibles = lugares_disponibles;
  }

  public boolean getPatio() {
    return patio;
  }

  public void setPatio(boolean patio) {
    this.patio = patio;
  }

  @JsonGetter("caracteristica")
  public CaracteristicaDTO getCaracteristica() {
    return caracteristica;
  }

  public void setCaracteristica(CaracteristicaDTO caracteristica) {
    this.caracteristica = caracteristica;
  }

  @Override
  public String toString() {
    return  "{"
        + '"' + "id"  + '"' + ": " + getId() + ','
        + '"' + "nombre"  + '"' + ": " + getNombre() + ','
        + '"' + "ubicacion" + '"' + ": " + getUbicacion() + ','
        + '"' + "telefono"  + '"' + ": " + getTelefono() + ','
        + '"' + "admisiones"  + '"' + ": " + getAdmisiones() + ','
        + '"' + "capacidad"  + '"' + ": " + getCapacidad() + ','
        + '"' + "lugares_disponibles"  + '"' + ": " + getLugares_disponibles() + ','
        + '"' + "patio"  + '"' + ": " + getPatio() + ','
        + '"' + "caracteristicas"  + '"' + ": " + getCaracteristica()
        + "}";
  }
}
