package controllers;

import mascotas.Especie;
import mascotas.MascotaBuilder;
import mascotas.Sexo;
import mascotas.Tamanio;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import repositorios.RepositorioDeMascotas;
import repositorios.RepositorioDeRescates;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.math.BigDecimal;

public class MascotaController implements WithGlobalEntityManager, TransactionalOps {
  private final static  MascotaController INSTANCE = new MascotaController();

  private MascotaController(){
  }

  public static MascotaController instance (){
    return INSTANCE;
  }

  public ModelAndView encontradas(Request request, Response response) {
    return new ModelAndView(null, "mascotasPerdidas/encontradas.html.hbs");
  }

  public ModelAndView registrar(Request request, Response response) {
    return new ModelAndView(null, "mascotasRegistradas/registrar.html.hbs");
  }

  public ModelAndView perdidas(Request request, Response response) {
    return new ModelAndView(null, "mascotasPerdidas/perdidas.html.hbs");
  }

  public ModelAndView conChapita(Request request, Response response) {
    return new ModelAndView(null, "mascotasPerdidas/conChapita.html.hbs");
  }

  public ModelAndView mascota(Request request, Response response) {
    return new ModelAndView(null, "mascotasRegistradas/mascota.html.hbs");
  }

  public Void crearMascota(Request request, Response response) {
    MascotaBuilder mascota = new MascotaBuilder();

    try {

      mascota.setApodo(request.queryParams("apodo"));
      mascota.setNombre(request.queryParams("nombre"));
      mascota.setTamanio(this.analizarTamanio(request.queryParams("tamanno")));
      mascota.setEspecie((request.queryParams("especie").equals("gato")) ? Especie.GATO : Especie.PERRO);
      mascota.setEdad(Short.parseShort("7"));
      mascota.setSexo(Sexo.HEMBRA);
      mascota.agregarImagen("");
      withTransaction(() -> {
        RepositorioDeMascotas.instance().agregarMascota(mascota.finalizarMascota());
      });

    } catch (RuntimeException e) {
      response.redirect("/error");
    }
    response.status(200);
    response.body("OK");
    response.redirect("/registrar");
    return null;
  }

  public Void listado(Request request, Response response) {
    response.status(200);
    response.body(RepositorioDeMascotas.instance().obtenerListado().toString());
    return null;
  }

  public Tamanio analizarTamanio(String param) {
    if(param.equals("chico")) {
      return Tamanio.CHICO;
    }
    if(param.equals("mediano")) {
      return Tamanio.MEDIANO;
    }
    if(param.equals("grande")) {
      return Tamanio.GRANDE;
    }
    throw new RuntimeException();
  }
}
