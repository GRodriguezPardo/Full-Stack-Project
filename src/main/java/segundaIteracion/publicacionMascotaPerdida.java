package segundaIteracion;

import primeraIteracion.mascotas.MascotaPerdida;
import primeraIteracion.personas.Persona;
import primeraIteracion.personas.Rescatista;

;import java.time.LocalDate;

public class publicacionMascotaPerdida extends Rescatista {
  Boolean aprobado;

  public publicacionMascotaPerdida(Persona _persona,
                                   LocalDate _fecha,
                                   MascotaPerdida _mascota) {
    super(_persona,_fecha,_mascota);
    this.aprobado = false;
  }

  public void aprobar(){
    this.aprobado = true;
  }
}
