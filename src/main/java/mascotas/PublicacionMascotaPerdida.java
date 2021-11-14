package mascotas;

import persistence.PersistenceId;
import personas.Contacto;
import personas.Posicion;
import personas.Rescatista;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.awt.*;
import java.util.List;

@Entity
public class PublicacionMascotaPerdida extends PersistenceId {
  @ManyToOne
  private final Rescatista rescatista;
  private Boolean aprobado;

  public PublicacionMascotaPerdida(Rescatista _rescatista) {
    this.rescatista = _rescatista;
    this.aprobado = false;
  }

  public PublicacionMascotaPerdida(){//todo ver si queda este cronstructor
    this.rescatista = null;
  }
  public void aprobar() {
    this.aprobado = true;
  }

  public Boolean aprobado() {
    return this.aprobado;
  }

  public List<Contacto> getContactos() {
    return this.rescatista.getContactos();
  }

  public List<Image> getFotos() {
    return this.rescatista.getFotos();
  }

  public Posicion getPosicion() {
    return rescatista.getPosicion();
  }
}
