package server;

import controllers.HomeController;
import controllers.LoginController;
import controllers.MascotaController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

  public static void configure() {
    Spark.staticFileLocation("/public");
    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
    HomeController home = new HomeController();
    MascotaController mascotaController = MascotaController.instance();
    LoginController loginController = new LoginController();
    expectionHandling();

    //Rutas generales
    Spark.get("/", home::index, engine);
    Spark.get("/login", loginController::show, engine);
    Spark.post("/login", loginController::login, engine);
    Spark.get("/logout", loginController::logout, engine);
    Spark.get("/signup", loginController::showSignUp, engine);
    Spark.post("/signup", loginController::signUp, engine);
    Spark.get("/error", home::error, engine);

    //TODO: Spark.get("/admin/login", loginController::showAdmin, engine);
    //TODO: Spark.post("/admin/login", loginController:loginAdmin, engine);
    //TODO: Spark.get("/admin/logout", loginController::logoutAdmin, engine);

    //------------------------------------------------------------------------------------//

    //Rutas de Mascotas
    //TODO: arreglar las rutas de mascotas de usuario (quitar /misMascotas/)

    Spark.get("/mascotas/nueva", mascotaController::nuevaMascota, engine);

    Spark.get("/mascotas", mascotaController::listarMascotas, engine);

    Spark.post("/mascotas", mascotaController::crearMascota);

    //TODO: Spark.get("/mascotas/:mascotaID", mascotaController::mascota, engine);
    Spark.get("/mascotas/mismascotas/:mascota", mascotaController::mascota, engine);

    //------------------------------------------------------------------------------------//

    //Rutas de rescates
    //TODO: arreglar las rutas de mascotas perdidas, no agrupar bajo el mismo recurso que las del usuario

    //TODO: Spark.get("/rescates", mascotaController::encontradas, engine);
    Spark.get("/mascotas/encontradas", mascotaController::encontradas, engine);

    Spark.get("/rescates/nueva", mascotaController::perdidas, engine);

    Spark.get("/rescates/nueva/sinChapita", mascotaController::sinChapita, engine);

    Spark.post("/rescates", mascotaController::registrarMascotaSinChapita);

    Spark.get("/rescates/nueva/conChapita/detalles",  mascotaController::conChapita, engine);

    Spark.post("/mascotas/:id/rescates", mascotaController::registrarMascotaConChapita);

    Spark.get("/mascotas/:id/rescates/nueva", mascotaController::perdidas, engine); //TODO FORM MASCOTA CON CHAPITA

    Spark.get("/rescates/gracias", mascotaController::agradecer, engine);

    Spark.get("/asociacion/nueva",mascotaController::nuevaAsociacion, engine);

    Spark.post("/asociacion", mascotaController::registrarAsosiacion);
    //------------------------------------------------------------------------------------//

    // Utilities
    Spark.get("/ejemploclase", home::ejemploClase, engine);
    Spark.get("/manualsetsession", loginController::manualSetSessionId, engine);
    //todo dejar al menos una asociacion en la base de datos
    /*Spark.after((request, response) -> {
      if(PerThreadEntityManagers.getEntityManager().isOpen()) {
        PerThreadEntityManagers.closeEntityManager();
      }
    });*/
  }

  //TODO
  public static void expectionHandling() {
    // https://sparkjava.com/documentation#error-handling
    /*
    Spark.exception(YourCustomException.class, (exception, request, response) -> {
        // Handle the exception here
      });
    */
  }
}
