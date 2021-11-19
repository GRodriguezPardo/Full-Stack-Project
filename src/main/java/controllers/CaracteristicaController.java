package controllers;

import mascotas.PosibleCaracteristica;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import personas.Rescatista;
import repositorios.RepositorioDeCaracteristicas;
import repositorios.RepositorioDeMascotas;
import repositorios.RepositorioDeRescates;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;
import java.util.stream.Collectors;

public class CaracteristicaController implements WithGlobalEntityManager , TransactionalOps {

  public Map<String, Object> obtenerSesion(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("sesioniniciada", Objects.isNull(request.session().attribute("user_id")));
    return model;
  }

  public ModelAndView caracteristicas(Request request, Response response) {
    List<PosibleCaracteristica> caracteristicas = RepositorioDeCaracteristicas.getInstance().getPosiblesCaracteristicas();

    Map<String, Object> model = new HashMap<>();
    model.put("caracteristicas", caracteristicas);
    return new ModelAndView(model, "caracteristicas/caracteristicas.html.hbs");
  }

  public ModelAndView agregarCaracteristica(Request request, Response response) {
    RepositorioDeCaracteristicas.getInstance().agregarPosibleCaracteristica(request.queryParams("nombre"));

    //lo de abajo es porque no funciona haciendolo con el repositorio
    withTransaction(() -> {
               // RepositorioDeCaracteristicas.getInstance().agregarPosibleCaracteristica(request.queryParams("nombre"));
              entityManager().persist(new PosibleCaracteristica(request.queryParams("nombre")));
            });
    response.redirect("/caracteristicas"); return  null;
  }

  public ModelAndView eliminarCaracteristica(Request request, Response response) {
  RepositorioDeCaracteristicas.getInstance().eliminarPosibleCaracteristica(request.queryParams("nombre"));

   // response.redirect("/caracteristicas");
    response.redirect("/home/error.html.hbs");
    return  null;
  }

}
