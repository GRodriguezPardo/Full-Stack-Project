package mascotas;

import personas.Contacto;
import personas.Posicion;
import personas.Rescatista;

import java.awt.*;
import java.util.List;

public class PublicacionMascotaPerdida {
  private final Rescatista rescatista;
  private Boolean aprobado;

  public PublicacionMascotaPerdida(Rescatista _rescatista) {
    this.rescatista = _rescatista;
    this.aprobado = false;
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

  public Posicion getPosicion() { return rescatista.getPosicion();
  }
}
