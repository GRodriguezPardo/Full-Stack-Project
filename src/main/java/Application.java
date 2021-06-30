import mascotas.PublicacionInteresadoEnAdopcion;
import mascotas.PublicacionMascotaEnAdopcion;
import repositorios.RepositorioDeAsociaciones;
import repositorios.RepositorioDeInteresados;
import services.HogaresService;

import java.util.List;

public class Application {

  public static void main(String[] args) {
    try {
      HogaresService api = new HogaresService();
    } catch ( Exception e){
      System.out.println(e.toString());
    }

    List<PublicacionMascotaEnAdopcion> listaPublicacionesMascotasEnAdopcion=
    RepositorioDeAsociaciones.getInstance().publicacionesDeMascotasEnAdopcion();


    List<PublicacionInteresadoEnAdopcion> listaPublicacionesInteresados=
        RepositorioDeInteresados.getInstance().getPublicacionesDeInteresados();

    listaPublicacionesMascotasEnAdopcion.forEach(
        publicacion -> listaPublicacionesInteresados.stream().filter(
            intesados -> intesados.compatibleCon(publicacion.getMascota())
        ).forEach(interado -> interado.notificacionSemanal())
    );

  }

}
