package controllers;

import apis.JavaXMail;
import apis.Mailer;
import apis.MedioNotificacion;
import apis.Smser;
import apis.TwilioJava;
import java.time.LocalDate;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import personas.Contacto;
import personas.Duenio;
import personas.Persona;
import personas.PersonaBuilder;
import personas.Usuario;
import repositorios.RepositorioDeUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController implements WithGlobalEntityManager, TransactionalOps {

  public ModelAndView login(Request request, Response response) {
    if(RepositorioDeUsuarios.getInstance()
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

  public ModelAndView show(Request request, Response response) {
    return new ModelAndView(null, "login/login.html.hbs");
  }

  public ModelAndView showSignUp(Request request, Response response) {
    return new ModelAndView(null, "login/signUp.html.hbs");
  }

  public ModelAndView signUp(Request request, Response response) {
    PersonaBuilder personaBuilder = new PersonaBuilder();
    personaBuilder.setNombreYApellido(request.queryParams("nombre") + " " + request.queryParams("apellido"));
    personaBuilder.setFechaNacimiento(
         LocalDate.of(
             Integer.parseInt(request.queryParams("anno")),
             Integer.parseInt(request.queryParams("mes")),
             Integer.parseInt(request.queryParams("dia"))
         )
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
}
