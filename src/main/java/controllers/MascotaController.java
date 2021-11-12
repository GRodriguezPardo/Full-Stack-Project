package controllers;

import apis.*;
import mascotas.*;
import org.apache.commons.lang3.ObjectUtils;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import personas.PersonaBuilder;
import personas.Posicion;
import personas.Rescatista;
import repositorios.RepositorioDeMascotas;
import repositorios.RepositorioDeRescates;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;

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

  public ModelAndView mascota(Request request, Response response) {
    return new ModelAndView(null, "mascotasRegistradas/mascota.html.hbs");
  }

  public Void registrarMascotaSinChapita(Request request, Response response){

    Posicion pos = new Posicion();
    //List<Image> imagenes= new ArrayList<Image>("url1" , "url2", "url3");


    PersonaBuilder persona = new PersonaBuilder();
    try{

      persona.setNombreYApellido("nombre");
      if("MedioDeNotificacion" == "SMS") {
        TwilioJava contacto = new TwilioJava("telefono");
        Smser smser = new Smser(contacto);
        persona.agregarMedioNotificacion(smser);
      }else if ("MedioDeNotificacion" == "email"){
        JavaXMail contactomail = new JavaXMail("mimail@.com","contrasenia");//TODO
        Mailer mailer = new Mailer(contactomail);
        persona.agregarMedioNotificacion(mailer);
      }

      MascotaPerdida mascota = new MascotaPerdida("estado",null, pos);
      Rescatista rescate = new Rescatista(persona.crearPersona(), null/*"fecha"*/, mascota);

      PublicacionMascotaPerdida publicacionAGnerear = new PublicacionMascotaPerdida(rescate);

      withTransaction(() -> {
        RepositorioDeRescates.getInstance().agregarRescate(rescate);
      });

    }catch (RuntimeException e){
      response.redirect("/error");
    }

    response.status(200);
    response.body("OK");
    response.redirect("/mascotas/sinChapita");
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


  public Void registrarMascotaConChapita(Request request, Response response) {

    return null;
  }
}
