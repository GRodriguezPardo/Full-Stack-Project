package mascotas;

import personas.Persona;
import personas.Rescatista;

;import java.time.LocalDate;

public class PublicacionMascotaPerdida extends Rescatista {
  Boolean aprobado;

  public PublicacionMascotaPerdida(Persona _persona,
                                   LocalDate _fecha,
                                   MascotaPerdida _mascota) {
    super(_persona,_fecha,_mascota);
    this.aprobado = false;
  }

  public void aprobar(){
    this.aprobado = true;
  }

  public Boolean aprobado() {
    return this.aprobado;
  }
}
