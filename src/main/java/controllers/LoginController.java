package controllers;

import apis.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import personas.*;
import repositorios.RepositorioDeUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//TODO: Clase que contenga el obtener sesion, del cual el resto herede. En vez de hacer "new ModelAndView" delegar en un metodo.
public class LoginController implements WithGlobalEntityManager, TransactionalOps {

  public Map<String, Object> obtenerSesion(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("sesioniniciada", Objects.isNull(request.session().attribute("user_id")));
    return model;
  }

  public ModelAndView login(Request request, Response response) {
    if (RepositorioDeUsuarios.getInstance()
            .comprobarClave(
                    request.queryParams("usuario"),
                    request.queryParams("contrasenna")
            )) {
      Usuario usuario = RepositorioDeUsuarios.getInstance()
              .usuarios().stream()
              .filter(unUsuario -> unUsuario.getUsuario().equals(request.queryParams("usuario")))
              .findFirst().get();
      request.session().attribute("user_id", usuario.getId());
      response.redirect("/");
    } else {
      response.redirect("/login");
    }
    return null;
  }

  //TODO: ver el tema de mostrar algo segun el origen
  public ModelAndView show(Request request, Response response) {
    String origen = request.session().attribute("origen");
    if (!Objects.isNull(origen)) {
      request.session().removeAttribute("origen");
    }
    return new ModelAndView(obtenerSesion(request, response), "login/login.html.hbs");
  }

  public ModelAndView showSignUp(Request request, Response response) {
    return new ModelAndView(obtenerSesion(request, response), "login/signUp.html.hbs");
  }

  public ModelAndView signUp(Request request, Response response) {
    PersonaBuilder personaBuilder = new PersonaBuilder();
    personaBuilder.setNombreYApellido(request.queryParams("nombre") + " " + request.queryParams("apellido"));
    personaBuilder.setFechaNacimiento(
            LocalDate.parse(request.queryParams("fecha-nacimiento"))
    );
    Contacto contacto = new Contacto(
            request.queryParams("nombreContacto"),
            request.queryParams("telefonoContacto"),
            request.queryParams("emailContacto")
    );
    personaBuilder.agregarContacto(contacto);
    MedioNotificacion medio = analizarMedioNotificacion(request, response);
    personaBuilder.agregarMedioNotificacion(medio);

    Persona persona = personaBuilder.crearPersona();
    this.withTransaction(() -> {
      try {
        RepositorioDeUsuarios
                .getInstance()
                .agregarUsuario(
                        new Usuario(
                                request.queryParams("usuario"),
                                request.queryParams("contrasenna"),
                                new Duenio(persona)));
        request.session().attribute("user", 1);
        response.redirect("/");
      } catch (Exception e) {
        response.redirect("/error");
      }
    });
    return null;
  }

  public MedioNotificacion analizarMedioNotificacion(Request req, Response res) {
    if (req.queryParams("medioNotificacion").equals("email")) {
      return new Mailer(new JavaXMail("usuario", "contraseña"));
    }
    if (req.queryParams("medioNotificacion").equals("telefono")) {
      return new Smser(new TwilioJava("id", "a", "11"));
    }
    throw new RuntimeException();
  }

  public ModelAndView manualSetSessionId(Request req, Response res) {
    req.session().attribute("user_id", 1);
    res.redirect("/");
    return null;
  }

  public ModelAndView logout(Request req, Response res) {
    req.session().attribute("user_id", null);
    res.redirect("/");
    return null;
  }
}
