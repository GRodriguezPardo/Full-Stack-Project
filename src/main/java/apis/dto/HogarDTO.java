package apis.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import mascotas.Especie;
import mascotas.Mascota;
import mascotas.Tamanio;
import personas.Posicion;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HogarDTO {

  private String id;
  private String nombre;
  private UbicacionDTO ubicacion;
  private String telefono;
  private AdmisionesDTO admisiones;
  private int capacidad;
  private int lugares_disponibles;
  private boolean patio = false;
  private List<String> caracteristicas;

  public HogarDTO() {
  }

  public List<String> getCaracteristicas() {
    return caracteristicas;
  }
  @JsonAnySetter
  public void setCaracteristicas(List<String> caracteristicas) {
    this.caracteristicas = caracteristicas;
  }

  public boolean validarAdmision(Mascota mascota){
    //valido por disponibilidad
    if (getLugares_disponibles() == 0) return false;
    //valido por tamaño
    if ((mascota.getTamanio() == Tamanio.MEDIANO) && (!getPatio())) return false;
    if ((mascota.getTamanio() == Tamanio.GRANDE) && (!getPatio())) return false;
    //valido por especie
    if (((getAdmisiones().getGatos() && (mascota.getEspecie() != Especie.GATO)))
        || ((getAdmisiones().getPerros() && (mascota.getEspecie() != Especie.PERRO)))) return false;
    return true;
  }

  public Posicion getPoscion(){
    return new Posicion(ubicacion.getLong(), ubicacion.getLat());
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

  public AdmisionesDTO getAdmisiones() {
    return admisiones;
  }

  public void setAdmisiones(AdmisionesDTO adimisiones) {
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
        + '"' + "caracteristicas"  + '"' + ": " + getCaracteristicas()
        + "}";
  }
}
