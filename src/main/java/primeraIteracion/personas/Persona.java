package primeraIteracion.personas;

import primeraIteracion.exceptions.FaltanDatosException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Persona {
  private String nombreYApellido;
  private LocalDate fechaNacimiento;
  private List<Contacto> contactos = new ArrayList<>();


  public Persona(String _nombreYApellido,
                 LocalDate _fechaNacimiento,
                 List<Contacto> _contacto) {
    if(Objects.isNull(_nombreYApellido)
       || Objects.isNull(_fechaNacimiento)
       || Objects.isNull(_contacto)){
      throw new FaltanDatosException(
          "Se debe proveer un nombre, una fecha de nacimiento y un contacto"
      );
    }
    if(_contacto.isEmpty()){
      throw new FaltanDatosException(
          "Se debe proveer minimo un contacto"
      );
    }
    this.nombreYApellido = _nombreYApellido;
    this.fechaNacimiento = _fechaNacimiento;
    Collections.copy(this.contactos, _contacto);
  }


  public void agregarContacto(Contacto contacto){
    this.contactos.add(contacto);
  }
}
