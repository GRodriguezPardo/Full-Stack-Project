package primeraIteracion.personas;

import primeraIteracion.mascotas.Mascota;

import java.util.ArrayList;
import java.util.List;

public class DueñoBuilder extends PersonaBuilder {
  private List<Mascota> mascotas = new ArrayList<>();


  public DueñoBuilder(){

  }


  public void agregarMascota(Mascota mascota) {
    this.mascotas.add(mascota);
  }

  @Override
  public Dueño creacionEspecifica() {
    if(mascotas.isEmpty()){
      //TODO
    }
    return new Dueño(
        this.nombreYApellido,
        this.fechaNacimiento,
        this.contactos,
        this.mascotas);
  }
}
