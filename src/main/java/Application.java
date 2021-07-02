import mascotas.PublicacionInteresadoEnAdopcion;
import mascotas.PublicacionMascotaEnAdopcion;
import personas.Asociacion;
import repositorios.RepositorioDeAsociaciones;
import services.HogaresService;

import java.util.List;

public class Application {

  public static void main(String[] args) {

    //TODO New TareaSemanal que haga todo

    List<Asociacion> asociasiones = RepositorioDeAsociaciones.getInstance().getAsociaciones();

    asociasiones.forEach(unaAsociacion -> {
      List<PublicacionMascotaEnAdopcion> listaPublicacionesMascotasEnAdopcion =
              unaAsociacion.getPublicacionesEnAdopcion();

      List<PublicacionInteresadoEnAdopcion> listaPublicacionesInteresados =
              unaAsociacion.getPublicacionInteresadoEnAdopcion();

      //TODO asociacion.mandaNotificicaciones()ya qque mandamaos las cosas porseparados
      //TODO sacar acumulador, invertir el orden de las iteraciones. Iterar sobre interesados y mandar lista de mascotas compatibles

      listaPublicacionesMascotasEnAdopcion.forEach(
              publicacionAdopcion -> listaPublicacionesInteresados.stream().filter(
                      publicacionInteresado -> publicacionAdopcion.getRespuestas().stream().allMatch(
                              publicacionInteresado::coincideRespuesta))
                      .forEach(PublicacionInteresadoEnAdopcion::agregarRecomendacion)
      );


      listaPublicacionesInteresados.forEach(PublicacionInteresadoEnAdopcion::notificacionSemanal);
    });
  }

}
