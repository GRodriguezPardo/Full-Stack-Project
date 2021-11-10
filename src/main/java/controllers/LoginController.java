package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {

  public static ModelAndView login(Request request, Response response) {
    return new ModelAndView("Hola", "login.html.hbs");
  }

  public static ModelAndView show(Request request, Response response) {
    return new ModelAndView("Hola","login.html.hbs");
  }

}
