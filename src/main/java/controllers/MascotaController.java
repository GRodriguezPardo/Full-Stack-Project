package controllers;

import apis.JavaXMail;
import apis.Mailer;
import apis.Smser;
import apis.TwilioJava;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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

import javax.swing.text.View;
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
    return new ModelAndView(obtenerSesion(request, response), "mascotasRegistradas/nuevaMascota.html.hbs");
  }

  public ModelAndView perdidas(Request request, Response response) {
    return new ModelAndView(obtenerSesion(request, response), "mascotasPerdidas/perdidas.html.hbs");
  }

  public ModelAndView conChapita(Request request, Response response) {
    return new ModelAndView(obtenerSesion(request, response), "mascotasPerdidas/conChapita.html.hbs");
  }

  public ModelAndView sinChapita(Request request, Response response) {
    return new ModelAndView(obtenerSesion(request, response), "mascotasPerdidas/sinChapita.html.hbs");
  }

  public ModelAndView agradecer(Request request, Response response) {
    return new ModelAndView(obtenerSesion(request, response), "mascotasPerdidas/gracias.html.hbs");
  }

  public ModelAndView mascota(Request request, Response response) {
    return new ModelAndView(obtenerSesion(request, response), "mascotasRegistradas/mascota.html.hbs");
  }

  public ModelAndView nuevaAsociacion(Request request, Response response) {
    return new ModelAndView(obtenerSesion(request, response) ,"asociaciones/nueva-asociacion.html.hbs");
  }

  public Void registrarAsosiacion(Request request,Response response){
    Asociacion asociacion = new Asociacion(new Posicion(Double.parseDouble(request.queryParams("longitud")), Double.parseDouble(request.queryParams("latitud"))));
    withTransaction(() -> {
          RepositorioDeAsociaciones.getInstance().agregarAsociacion(asociacion);
    });
    response.status(200);
    response.body("OK");
    response.redirect("");
    return null;
  }

  public Void registrarMascotaSinChapita(Request request, Response response){

    List<Image> imagenes= new ArrayList<>();// TODO castear de url a Image
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    PersonaBuilder persona = new PersonaBuilder();
    Image image = null;
    imagenes.add(image);
    //try{

      persona.setNombreYApellido("nombre");
      persona.setFechaNacimiento(LocalDate.now());
      persona.agregarContacto(new Contacto("matias", "11", "mail"));

      if(request.queryParams("SMS") != null) {

        TwilioJava contacto = new TwilioJava(request.queryParams("telefono"), "a", "11");
        Smser smser = new Smser(contacto);
        persona.agregarMedioNotificacion(smser);
      }else if (request.queryParams("mail") != null){

        JavaXMail contactomail = new JavaXMail("mailDelservivionuestro@.com","contrasenia");
        Mailer mailer = new Mailer(contactomail);
        persona.agregarMedioNotificacion(mailer);
      }

      Posicion pos = new Posicion(Double.parseDouble(request.queryParams("longitud")),Double.parseDouble(request.queryParams("latitud")));
      LocalDate fecha = LocalDate.parse(request.queryParams("fecha"), formatter);

      MascotaPerdida mascota = new MascotaPerdida(request.queryParams("estado"),imagenes, pos);
      Rescatista rescate = new Rescatista(persona.crearPersona(), fecha , mascota);

      PublicacionMascotaPerdida publicacionAGenerear = new PublicacionMascotaPerdida(rescate);

      withTransaction(() -> {
        RepositorioDeRescates.getInstance().agregarRescate(rescate);
        RepositorioDeAsociaciones.getInstance().asociacionMasCercana(publicacionAGenerear).agregarPublicacionMascotaPerdida(publicacionAGenerear);
      });

    //}catch (RuntimeException e){
    //  response.redirect("/error");
    //}

    response.status(200);
    response.body("OK");
    response.redirect("/rescates/gracias");
    return null;
  }

  public Void registrarMascotaConChapita(Request request, Response response) {

    request.queryParams("qr");

    response.status(200);
    response.body("OK");
    response.redirect("/mascotas/perdidas/gracias");
    return null;

  }

  public Void crearMascota(Request request, Response response) {
    MascotaBuilder mascota = new MascotaBuilder();

    try {

      mascota.setApodo(request.queryParams("apodo"));
      mascota.setNombre(request.queryParams("nombre"));
      mascota.setTamanio(this.analizarTamanio(request.queryParams("tamanno")));
      mascota.setEspecie(Especie.valueOf(request.queryParams("especie")));

      mascota.setEdad(Short.parseShort("7"));
      mascota.setSexo(Sexo.valueOf(request.queryParams("sexo")));
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
