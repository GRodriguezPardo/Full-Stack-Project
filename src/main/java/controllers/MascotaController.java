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

  public ModelAndView formularioRescatista(Request request, Response response) {
    return new ModelAndView(obtenerSesion(request, response), "mascotasPerdidas/rescatista.html.hbs");
  }

  public Void registrarAsosiacion(Request request,Response response){
    Asociacion asociacion = new Asociacion(new Posicion(Double.parseDouble(request.queryParams("longitud")), Double.parseDouble(request.queryParams("latitud"))));
    withTransaction(() -> RepositorioDeAsociaciones.getInstance().agregarAsociacion(asociacion));
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

    response.status(200);
    response.body("OK");
    response.redirect("/rescates/gracias");
    return null;
  }

  public Void registrarRescatista(Request request, Response response){


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    PersonaBuilder persona = new PersonaBuilder();
    persona.setNombreYApellido("nombre");
    persona.setFechaNacimiento(LocalDate.parse(request.queryParams("nacimiento"), formatter));
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


    Mascota mascota = RepositorioDeMascotas.instance().obtenerMascota(request.session().attribute("id"));
    MascotaPerdida mascotaPerdida =  new MascotaPerdida();// todo Consultar como queda esta parte, porque el id es de cada mascota, pero es distinta la entidad a Mascota perdida

    Rescatista rescate = new Rescatista(persona.crearPersona(), LocalDate.now() ,mascotaPerdida /*todo ver como reconocer esta mascota que esta en :id */ );
    PublicacionMascotaPerdida nuevaPublicacion = new PublicacionMascotaPerdida(rescate);


    withTransaction(() -> RepositorioDeAsociaciones.getInstance().asociacionMasCercana(nuevaPublicacion).agregarPublicacionMascotaPerdida(nuevaPublicacion));
    withTransaction(() -> RepositorioDeRescates.getInstance().agregarRescate(rescate));

    response.status(200);
    response.body("OK");
    response.redirect("/rescates/gracias");
    return null;
  }

  public Void registrarMascotaConChapita(Request request, Response response) {



    response.status(200);
    response.body("OK");
    response.redirect("/mascotas/:id/rescates/nueva");
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