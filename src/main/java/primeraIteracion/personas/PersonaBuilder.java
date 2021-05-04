package primeraIteracion.personas;

import primeraIteracion.exceptions.FaltanDatosException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class PersonaBuilder {
  protected String nombreYApellido;
  protected LocalDate fechaNacimiento;
  protected List<Contacto> contactos = new ArrayList<>();

  public PersonaBuilder() {
  }


  public void setNombreYApellido(String _nombreYApellido) {
    this.nombreYApellido = _nombreYApellido;
  }

  public void setFechaNacimiento(LocalDate _fechaNacimiento) {
    this.fechaNacimiento = _fechaNacimiento;
  }

  public void agregarContacto(Contacto _contacto) {
    this.contactos.add(_contacto);
  }

  public Persona crearPersona() {
    if(Objects.isNull(this.nombreYApellido)
       || Objects.isNull(this.fechaNacimiento)
       || this.contactos.isEmpty()){
      throw new FaltanDatosException(
          "Se debe proveer un nombre, una fecha de nacimiento y un contacto"
      );
    }
    return this.creacionEspecifica();
  }

  public abstract Persona creacionEspecifica();
}
