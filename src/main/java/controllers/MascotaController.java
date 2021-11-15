package controllers;

import apis.JavaXMail;
import apis.Mailer;
import apis.Smser;
import apis.TwilioJava;
import mascotas.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import personas.*;
import repositorios.RepositorioDeAsociaciones;
import repositorios.RepositorioDeMascotas;
import repositorios.RepositorioDeRescates;
import repositorios.RepositorioDeUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;

public class MascotaController implements WithGlobalEntityManager, TransactionalOps {
  private final static  MascotaController INSTANCE = new MascotaController();

  private MascotaController(){
  }

  public static MascotaController instance (){
    return INSTANCE;
  }

  public Map<String, Object> obtenerSesion(Request request, Response response){
    Map<String, Object> model = new HashMap<>();
    model.put("sesioniniciada", Objects.isNull(request.session().attribute("user_id")));
    return model;
  }

  public ModelAndView encontradas(Request request, Response response) {
    return new ModelAndView(obtenerSesion(request, response), "mascotasPerdidas/encontradas.html.hbs");
  }

  public ModelAndView nuevaMascota(Request request, Response response) {
    if(Objects.isNull(request.session().attribute("user_id"))) {
      //request.session().attribute("origen", "");
      response.redirect("/login");
      return null;
    }
    return new ModelAndView(obtenerSesion(request, response), "mascotasRegistradas/nueva.html.hbs");
  }


  public ModelAndView mascota(Request request, Response response) {
    return new ModelAndView(obtenerSesion(request, response), "mascotasRegistradas/mascota.html.hbs");
  }

  public ModelAndView nuevaAsociacion(Request request, Response response) {
    return new ModelAndView(obtenerSesion(request, response) ,"asociaciones/nueva-asociacion.html.hbs");
  }


  public Void registrarAsosiacion(Request request,Response response){
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
      mascota.setEdad(Short.parseShort("7"));
      mascota.setSexo(Sexo.valueOf(request.queryParams("sexo")));
      mascota.agregarImagen("");
      mascota.setDescripcion(request.queryParams("descripcion"));
      //TODO: Estoy guardando en un repo por separado las mascotas, hay que ver de asociarlo con los usuarios
      withTransaction(() -> RepositorioDeMascotas.instance().agregarMascota(mascota.finalizarMascota()));

    } catch (RuntimeException e) {
      System.out.println(e.toString());
      response.redirect("/error");
    }
    response.status(200);
    response.body("OK");
    response.redirect("/mascotas/nueva");
    return null;
  }

  //TODO: Esta reventando el Listar Mascotas
  public ModelAndView listarMascotas(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();
    Optional<Usuario> optionalUsuario = RepositorioDeUsuarios.getInstance()
        .usuarios().stream()
        .filter(unUsuario -> unUsuario.getUsuario()
            .equals(req.session().attribute("usuario"))).findFirst();
    Usuario usuario;
    if(optionalUsuario.isPresent()) {
      usuario = optionalUsuario.get();
    } else {
      res.redirect("/error");
      return null;
    }

		List<Mascota> mascotas = usuario.getDuenio().getMascotas();

		model.put("mascotas", mascotas);
    model.put("sesioniniciada", Objects.isNull(req.session().attribute("user_id")));
		return new ModelAndView(model, "mascotasRegistradas/misMascotas.html.hbs");
	}


}