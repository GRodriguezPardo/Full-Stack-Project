package controllers;

import personas.Admin;
import personas.Usuario;
import repositorios.RepositorioDeUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AdminController {

  public Map<String, Object> obtenerSesion(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("sesioniniciada", !Objects.isNull(request.session().attribute("user_id")));
    return model;
  }

  public ModelAndView showAdmin(Request request, Response response) {
    String origen = request.session().attribute("origen");
    if (!Objects.isNull(origen)) {
      request.session().removeAttribute("origen");
    }
    return new ModelAndView(obtenerSesion(request, response), "login/admin.html.hbs");
  }

  public ModelAndView loginAdmin(Request request, Response response) {
    if (RepositorioDeUsuarios.getInstance()
        .comprobarClaveAdmin(
            request.queryParams("usuario"),
            request.queryParams("contrasenna")
        )) {
      Admin admin = RepositorioDeUsuarios.getInstance()
          .administradores().stream()
          .filter(unUsuario -> unUsuario.getUsuario().equals(request.queryParams("usuario")))
          .findFirst().get();
      request.session().attribute("admin_id", admin.getId());
      response.redirect("/caracteristicas");
    } else {
      response.redirect("/admin/login");
    }
    return null;
  }

  public ModelAndView logoutAdmin(Request request, Response response) {
    return null; //new ModelAndView(null , "login/admin.html.hbs");
  }
}
