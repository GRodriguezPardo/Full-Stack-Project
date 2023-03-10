package controllers;

import apis.JavaXMail;
import apis.Mailer;
import apis.Smser;
import apis.TwilioJava;
import mascotas.Mascota;
import mascotas.MascotaPerdida;
import mascotas.PublicacionMascotaPerdida;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import personas.Contacto;
import personas.PersonaBuilder;
import personas.Posicion;
import personas.Rescatista;
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
import java.util.stream.Collectors;

public class MascotaPerdidaController implements WithGlobalEntityManager, TransactionalOps {
  private final static MascotaPerdidaController INSTANCE = new MascotaPerdidaController();

  private MascotaPerdidaController() {
  }

  public static MascotaPerdidaController instance() {
    return INSTANCE;
  }

  public Map<String, Object> obtenerSesion(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("sesioniniciada", !Objects.isNull(request.session().attribute("user_id")));
    return model;
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

  public ModelAndView formularioRescatista(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("idDeMascota", request.params("id"));
    return new ModelAndView(model, "mascotasPerdidas/rescatista.html.hbs");
  }


  public Void registrarMascotaSinChapita(Request request, Response response) {

    List<Image> imagenes = new ArrayList<>();// TODO castear de url a Image
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    PersonaBuilder persona = new PersonaBuilder();
    Image image = null;
    imagenes.add(image);

    persona.setNombreYApellido(request.queryParams("nombre"));
    persona.setFechaNacimiento(LocalDate.now());
    persona.agregarContacto(new Contacto("matias", "11", "mail"));

    if (request.queryParams("SMS") != null) {

      TwilioJava contacto = new TwilioJava(request.queryParams("telefono"), "a", "11");
      Smser smser = new Smser(contacto);
      persona.agregarMedioNotificacion(smser);
    } else if (request.queryParams("mail") != null) {

      JavaXMail contactomail = new JavaXMail("mailDelservivionuestro@.com", "contrasenia");
      Mailer mailer = new Mailer(contactomail);
      persona.agregarMedioNotificacion(mailer);
    }

    Posicion pos = new Posicion(Double.parseDouble(request.queryParams("longitud")), Double.parseDouble(request.queryParams("latitud")));
    LocalDate fecha = LocalDate.parse(request.queryParams("fecha"), formatter);

    MascotaPerdida mascota = new MascotaPerdida(request.queryParams("estado"), imagenes, pos);
    Rescatista rescate = new Rescatista(persona.crearPersona(), fecha, mascota);

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


  public Void registrarMascotaConChapita(Request request, Response response) {


    List<Image> imagenes = new ArrayList<>();// TODO castear de url a Image
    Image image = null;
    imagenes.add(image);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    PersonaBuilder persona = new PersonaBuilder();
    persona.setNombreYApellido("nombre");
    persona.setFechaNacimiento(LocalDate.parse(request.queryParams("nacimiento"), formatter));
    persona.agregarContacto(new Contacto("matias", "11", "mail"));

    if (request.queryParams("SMS") != null) {
      TwilioJava contacto = new TwilioJava(request.queryParams("telefono"), "a", "11");
      Smser smser = new Smser(contacto);
      persona.agregarMedioNotificacion(smser);
    } else if (request.queryParams("mail") != null) {
      JavaXMail contactomail = new JavaXMail("mailDelservivionuestro@.com", "contrasenia");
      Mailer mailer = new Mailer(contactomail);
      persona.agregarMedioNotificacion(mailer);
    }

    MascotaPerdida mascotaPerdida = new MascotaPerdida(request.queryParams("estado"), imagenes , new Posicion(Double.parseDouble(request.queryParams("longitud")), Double.parseDouble(request.queryParams("latitud"))));

    Rescatista rescatista = new Rescatista(persona.crearPersona(), LocalDate.now(), mascotaPerdida);
    Mascota mascota = RepositorioDeMascotas.instance().obtenerMascota(request.params("id"));

    withTransaction(() -> RepositorioDeRescates.getInstance().agregarRescate(rescatista));
    withTransaction(() -> RepositorioDeUsuarios.getInstance().usuarioDuenioDe(mascota).contactarDuenioPorMascota());


    response.status(200);
    response.body("OK");
    response.redirect("/rescates/gracias");
    return null;
  }

  public ModelAndView listarRescatesSinChapita(Request request, Response response) {

    Map<String, Object> model = new HashMap<>();
    List<Rescatista> rescates = RepositorioDeAsociaciones.getInstance().getAsociaciones().stream().map( asociacion -> asociacion.publicacionesACargo() ).flatMap(Collection::stream).map(publ -> publ.getRescatista()).collect(Collectors.toList());
    model.put("rescates", rescates);
    return new ModelAndView(model , "mascotasPerdidas/listado.html.hbs");
  }

  public ModelAndView listarRescatesPendientes(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    List<Rescatista> rescates = RepositorioDeRescates.getInstance().getRescates();
    model.put("rescates", rescates);
    return new ModelAndView(model , "mascotasPerdidas/listado.html.hbs");
  }
}