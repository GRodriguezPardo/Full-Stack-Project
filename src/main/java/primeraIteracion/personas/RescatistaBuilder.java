package primeraIteracion.personas;

import primeraIteracion.mascotas.MascotaPerdida;

import java.util.Objects;

public class RescatistaBuilder extends PersonaBuilder {
  private MascotaPerdida mascota;


  public RescatistaBuilder() {

  }

  public void setMascota(MascotaPerdida _mascota) {
    this.mascota = _mascota;
  }

  @Override
  public Rescatista creacionEspecifica() {
    if(Objects.isNull(this.mascota)){
      //TODO
    }
    return new Rescatista(
        this.nombreYApellido,
        this.fechaNacimiento,
        this.contactos,
        this.mascota);
  }
}
