package controllers;

import apis.*;
import personas.*;
import repositorios.RepositorioDeUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;

public class LoginController {

  public static ModelAndView login(Request request, Response response) {
    request.session().attribute("user", 1);
    response.redirect("/");
    return null;
  }

  public static ModelAndView show(Request request, Response response) {
    return new ModelAndView(null, "login/login.html.hbs");
  }

  public static ModelAndView showSignUp(Request request, Response response) {
    return new ModelAndView(null, "login/signUp.html.hbs");
  }

  public static ModelAndView signUp(Request request, Response response) {
    PersonaBuilder personaBuilder = new PersonaBuilder();
    personaBuilder.setNombreYApellido(request.queryParams("nombre") + " " + request.queryParams("apellido"));
    personaBuilder.setFechaNacimiento(
         LocalDate.of(
             Integer.valueOf(request.queryParams("anno")),
             Integer.valueOf(request.queryParams("mes")),
             Integer.valueOf(request.queryParams("dia"))
         )
    );
    Contacto contacto = new Contacto(
        request.queryParams("nombreContacto"),
        request.queryParams("telefonoContacto"),
        request.queryParams("emailContacto")
    );
    personaBuilder.agregarContacto(contacto);
    MedioNotificacion medio = LoginController.analizarMedioNotificacion(request,response);
    personaBuilder.agregarMedioNotificacion(medio);

    Persona persona = personaBuilder.crearPersona();
    try {
      RepositorioDeUsuarios
          .getInstance()
          .agregarUsuario(
              new Usuario(
                  request.queryParams("usuario"),
                  request.queryParams("contrasenna"),
                  new Duenio(persona)));
    } catch (Exception e) {
      response.redirect("/error");
      return null;
    }
    request.session().attribute("user", 1);
    response.redirect("/");
    return null;
  }

  static MedioNotificacion analizarMedioNotificacion(Request req, Response res) {
    if(req.queryParams("medioNotificacion").equals("email")) {
      return new Mailer(new JavaXMail("usuario", "contraseña"));
    }
    if(req.queryParams("medioNotificacion").equals("telefono")) {
      return new Smser(new TwilioJava("id", "token", "number"));
    }
    throw new RuntimeException();
  }
}
