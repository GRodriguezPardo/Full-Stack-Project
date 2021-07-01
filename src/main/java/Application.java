import java.util.List;
import mascotas.PublicacionInteresadoEnAdopcion;
import mascotas.PublicacionMascotaEnAdopcion;
import personas.Asociacion;
import repositorios.RepositorioDeAsociaciones;
import repositorios.RepositorioDeInteresados;
import services.HogaresService;

public class Application {

  public static void main(String[] args) {
    try {
      HogaresService api = new HogaresService();
    } catch (Exception e) {
      System.out.println(e);
    }

    List<Asociacion> asociasiones = RepositorioDeAsociaciones.getInstance().getAsociaciones();

    asociasiones.forEach(unaAsociacion -> {
      List<PublicacionMascotaEnAdopcion> listaPublicacionesMascotasEnAdopcion =
          unaAsociacion.getPublicacionesEnAdopcion();

      List<PublicacionInteresadoEnAdopcion> listaPublicacionesInteresados =
          unaAsociacion.getPublicacionInteresadoEnAdopcion();

      listaPublicacionesMascotasEnAdopcion.forEach(
          publicacionAdopcion -> listaPublicacionesInteresados.stream().filter(
              publicacionInteresado -> publicacionAdopcion.getRespuestas().stream().allMatch(
                  publicacionInteresado::coincideRespuesta))
          .forEach(PublicacionInteresadoEnAdopcion::notificacionSemanal)
      );
    });
  }

}
