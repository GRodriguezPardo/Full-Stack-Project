package server;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {

  public static void configure() {
    HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
        .create()
				.withDefaultHelpers()
        .withHelper("isTrue", BooleanHelper.isTrue)
        .build();
    Spark.staticFiles.location("/public");

    //ProyectosController proyectosController = new ProyectosController();

    //Spark.get("/", HomeController::home, engine);
    // Spark.get("/login", LoginController::show, engine);
    // Spark.post("/login", LoginController::login, engine);
  }
}
