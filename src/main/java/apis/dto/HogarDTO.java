package apis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HogarDTO {

  private UUID id;
  private String nombre;
  private UbicacionDTO ubicacion;
  private String telefono;
  private AdimisionesDTO adimisiones;
  private int capcidad;
  private int lugares_disponibles;
  private boolean patio = false;
  private CaracteristicaDTO caracteristica;


  public HogarDTO(){
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public UbicacionDTO getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(UbicacionDTO ubicacion) {
    this.ubicacion = ubicacion;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public AdimisionesDTO getAdmisiones() {
    return adimisiones;
  }

  public void setAdimisiones(AdimisionesDTO adimisiones) {
    this.adimisiones = adimisiones;
  }

  public int getCapcidad() {
    return capcidad;
  }

  public void setCapcidad(int capcidad) {
    this.capcidad = capcidad;
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

  public CaracteristicaDTO getCaracteristica() {
    return caracteristica;
  }

  public void setCaracteristica(CaracteristicaDTO caracteristica) {
    this.caracteristica = caracteristica;
  }

  @Override
  public String toString(){
    return "{" +
        "id:" + getId() + '\'' +
        ", nombre:" + getNombre()+ '\'' +
        ", ubicación:" + getUbicacion() + '\'' +
        ", teléfono:" + getTelefono()+ '\'' +
        ", admisiones:" + getAdmisiones()+ '\'' +
        ", capacidad:" + getCapcidad()+ '\'' +
        ", lugares_disponibles:" + getLugares_disponibles()+ '\'' +
        ", patio:" + getPatio()+ '\'' +
        ", caracteristicas:" + getCaracteristica()+ '\'' +
        "'}'";
  }
}
