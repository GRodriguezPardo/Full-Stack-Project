package controllers;

import exceptions.FaltanDatosException;
import mascotas.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import personas.Asociacion;
import personas.Posicion;
import personas.Usuario;
import repositorios.RepositorioDeAsociaciones;
import repositorios.RepositorioDeMascotas;
import repositorios.RepositorioDeUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

public class MascotaController implements WithGlobalEntityManager, TransactionalOps {
  private final static MascotaController INSTANCE = new MascotaController();

  private MascotaController() {
  }

  public static MascotaController instance() {
    return INSTANCE;
  }

  public Map<String, Object> obtenerSesion(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("sesioniniciada", !Objects.isNull(request.session().attribute("user_id")));
    return model;
  }

  public ModelAndView nuevaMascota(Request request, Response response) {
    if (Objects.isNull(request.session().attribute("user_id"))) {
      //request.session().attribute("origen", "");
      response.redirect("/login");
      return null;
    }
    return new ModelAndView(obtenerSesion(request, response), "mascotasRegistradas/nueva.html.hbs");
  }


  public ModelAndView mascota(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    Usuario usuario = getUsuario(request);

    Mascota mascota = RepositorioDeMascotas.instance().obtenerMascota(request.params("mascotaId"));

    if (RepositorioDeUsuarios.getInstance().usuarioDuenioDe(mascota).getId() != usuario.getId()) {
      request.session().attribute("errorMessage", "Usted no es dueño de esta mascota")
      response.redirect("/error");
      return null;
    }

    model.put("mascota", mascota);
    model.put("sesioniniciada", !Objects.isNull(request.session().attribute("user_id")));
    return new ModelAndView(model, "mascotasRegistradas/mascota.html.hbs");
  }

  public ModelAndView nuevaAsociacion(Request request, Response response) {
    return new ModelAndView(obtenerSesion(request, response), "asociaciones/nueva-asociacion.html.hbs");
  }


  public Void registrarAsosiacion(Request request, Response response) {
    Asociacion asociacion = new Asociacion(new Posicion(Double.parseDouble(request.queryParams("longitud")), Double.parseDouble(request.queryParams("latitud"))));
    withTransaction(() -> RepositorioDeAsociaciones.getInstance().agregarAsociacion(asociacion));
    response.status(200);
    response.body("OK");
    response.redirect("");
    return null;
  }


  public Void crearMascota(Request request, Response response) {
    MascotaBuilder mascota = new MascotaBuilder();

    try {
      mascota.setApodo(request.queryParams("apodo"));
      mascota.setNombre(request.queryParams("nombre"));
      mascota.setTamanio(Tamanio.valueOf(request.queryParams("tamanno")));
      mascota.setEspecie(Especie.valueOf(request.queryParams("especie")));
      mascota.setEdad(Short.parseShort(request.queryParams("edad")));
      mascota.setSexo(Sexo.valueOf(request.queryParams("sexo")));
      mascota.agregarImagen("");
      mascota.setDescripcion(request.queryParams("descripcion"));
      withTransaction(() -> {
            getUsuario(request).getDuenio().agregarMascota(mascota.finalizarMascota());
          }
      );

    } catch (FaltanDatosException e) {
      request.session().attribute("errorMessage", e.getMessage());
      response.redirect("/error");
    } catch (Exception e) {
      request.session().attribute("errorMessage", "Algo salio mal");
      response.redirect("/error");
    }
    response.status(200);
    response.body("OK");
    response.redirect("/mascotas");
    return null;
  }

  public ModelAndView listarMascotas(Request req, Response res) {
    Map<String, Object> model = new HashMap<>();
    Usuario usuario = getUsuario(req);

    List<Mascota> mascotas = usuario.getDuenio().getMascotas();

    model.put("mascotas", mascotas);
    model.put("sesioniniciada", !Objects.isNull(req.session().attribute("user_id")));
    return new ModelAndView(model, "mascotasRegistradas/misMascotas.html.hbs");
  }

  private Usuario getUsuario(Request req) {
    Optional<Usuario> optionalUsuario = RepositorioDeUsuarios.getInstance()
        .usuarios().stream()
        .filter(unUsuario -> ((Object) unUsuario.getId()).equals(
            req.session().attribute("user_id"))).findFirst();
    Usuario usuario;
    if (optionalUsuario.isPresent()) {
      usuario = optionalUsuario.get();
    } else {
      throw new RuntimeException("Usted no existe");
    }
    return usuario;
  }


}