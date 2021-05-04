package primeraIteracion.personas;

import primeraIteracion.mascotas.Mascota;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Dueño extends Persona {
  private List<Mascota> mascotas = new ArrayList<>();


  public Dueño (String _nombreYApellido,
                 LocalDate _fechaNacimiento,
                 List<Contacto> _contacto,
                 List<Mascota> _mascotas) {
    super(_nombreYApellido, _fechaNacimiento, _contacto);
    if(Objects.isNull(_mascotas)){
      //TODO
    }
    if(_mascotas.isEmpty()){
      //TODO
    }
    Collections.copy(mascotas,_mascotas);
  }

  public void agregarMascota(Mascota mascota) {
    this.mascotas.add(mascota);
  }
}
