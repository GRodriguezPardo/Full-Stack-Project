package primeraIteracion.personas;

import primeraIteracion.mascotas.MascotaPerdida;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Rescatista extends Persona {
  private MascotaPerdida mascota;
  public Rescatista (String _nombreYApellido,
                 LocalDate _fechaNacimiento,
                 List<Contacto> _contacto,
                 MascotaPerdida _mascota) {
    super(_nombreYApellido, _fechaNacimiento, _contacto);
    if(Objects.isNull(_mascota)){
      //TODO
    }
    this.mascota = _mascota;
  }
}
