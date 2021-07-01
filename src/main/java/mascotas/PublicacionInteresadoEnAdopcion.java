package mascotas;

import personas.Interesado;
import personas.Respuesta;

import java.util.ArrayList;
import java.util.List;

public class PublicacionInteresadoEnAdopcion {

  private final List<Respuesta> respuestas = new ArrayList<>();
  private final Interesado interesado;
  private final List<Caracteristica> preferencias;


  public PublicacionInteresadoEnAdopcion(Interesado interesado) {
    this.interesado = interesado;
    this.preferencias = new ArrayList<>();
  }

  public boolean compatibleCon(Mascota mascota) {
    return this.preferencias.stream().allMatch(
            preferencias -> mascota.esCompatible(preferencias)
    );
  }

  public void agregarPreferencias(Caracteristica caracteristica) {
    this.preferencias.add(caracteristica);
  }

  public void quitarPreferencias(Caracteristica caracteristica) {
    this.preferencias.remove(caracteristica);
  }

  public void notificacionSemanal() {
    interesado.contactarDuenioPorSugerencia();
  }

  public void agregarRespuesta(Respuesta respuesta) {
    this.respuestas.add(respuesta);
  }
}
