package controllers;

import apis.JavaXMail;
import apis.Mailer;
import apis.Smser;
import apis.TwilioJava;
import mascotas.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import personas.PersonaBuilder;
import personas.Posicion;
import personas.Rescatista;
import personas.Usuario;
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

  public ModelAndView sinChapita(Request request, Response response) {
    return new ModelAndView(null, "mascotasPerdidas/sinChapita.html.hbs");
  }

  public ModelAndView agradecer(Request request, Response response) {
    return new ModelAndView(null, "mascotasPerdidas/gracias.html.hbs");
  }

  public ModelAndView mascota(Request request, Response response) {
    return new ModelAndView(null, "mascotasRegistradas/mascota.html.hbs");
  }

  public Void registrarMascotaSinChapita(Request request, Response response){

    Posicion pos = new Posicion();//todo castear el string o lo que venga de la posicion de google

    List<Image> imagenes= new ArrayList<Image>();// TODO castear de url a Image
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    PersonaBuilder persona = new PersonaBuilder();
    try{

      persona.setNombreYApellido("nombre");

      if(request.queryParams("MedioDeNotificacion") == "SMS") {

        TwilioJava contacto = new TwilioJava(request.queryParams("telefono"), "a", "11");
        Smser smser = new Smser(contacto);
        persona.agregarMedioNotificacion(smser);
      }else if (request.queryParams("MedioDeNotificacion") == "email"){

        JavaXMail contactomail = new JavaXMail("mailDelservivionuestro@.com","contrasenia");
        Mailer mailer = new Mailer(contactomail);
        persona.agregarMedioNotificacion(mailer);
      }

      LocalDate fecha = LocalDate.parse(request.queryParams("fecha"), formatter);

      MascotaPerdida mascota = new MascotaPerdida(request.queryParams("estado"),imagenes, pos);
      Rescatista rescate = new Rescatista(persona.crearPersona(), fecha, mascota);

      PublicacionMascotaPerdida publicacionAGenerear = new PublicacionMascotaPerdida(rescate);

      withTransaction(() -> {
        RepositorioDeRescates.getInstance().agregarRescate(rescate);
      });

    }catch (RuntimeException e){
      response.redirect("/error");
    }

    response.status(200);
    response.body("OK");
    response.redirect("/mascotas/perdidas/gracias");
    return null;
  }

  public Void registrarMascotaConChapita(Request request, Response response) {

    response.status(200);
    response.body("OK");
    response.redirect("/gracias");
    return null;

  }

  public Void crearMascota(Request request, Response response) {
    MascotaBuilder mascota = new MascotaBuilder();

    try {

      mascota.setApodo(request.queryParams("apodo"));
      mascota.setNombre(request.queryParams("nombre"));
      mascota.setTamanio(this.analizarTamanio(request.queryParams("tamanno")));
      mascota.setEspecie((request.queryParams("especie").equals("gato")) ? Especie.GATO : Especie.PERRO);
      mascota.setEdad(Short.parseShort("7"));
      mascota.setSexo((request.queryParams("sexo").equals("hembra")) ? Sexo.HEMBRA : Sexo.MACHO);
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
		Map<String, List<Mascota>> model = new HashMap<>();
    Optional<Usuario> optionalUsuario = RepositorioDeUsuarios.getInstance()
        .usuarios().stream()
        .filter(unUsuario -> unUsuario.getUsuario()
            .equals(req.session().attribute("usuario"))).findFirst();
    Usuario usuario;
    if(optionalUsuario.isPresent()) {
      usuario = optionalUsuario.get();
    } else {
      throw new RuntimeException();
    }

		List<Mascota> mascotas = usuario.getDuenio().getMascotas();

		model.put("mascotas", mascotas);
		return new ModelAndView(model, "mascotasRegistradas/misMascotas.html.hbs");
	}


}
