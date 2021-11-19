package server;

import controllers.*;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

  public static void configure() {
    Spark.staticFileLocation("/public");
    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
    HomeController home = new HomeController();
    MascotaController mascotaController = MascotaController.instance();
    MascotaPerdidaController mascotaPerdidaController = MascotaPerdidaController.instance();
    LoginController loginController = new LoginController();
    AdminController adminController = new AdminController();
    CaracteristicaController caracteristicaController = new CaracteristicaController();
    expectionHandling();

    //Rutas generales
    Spark.get("/", home::index, engine);
    Spark.get("/login", loginController::show, engine);
    Spark.post("/login", loginController::login, engine);
    Spark.get("/logout", loginController::logout, engine);
    Spark.get("/signup", loginController::showSignUp, engine);
    Spark.post("/signup", loginController::signUp, engine);
    Spark.get("/error", home::error, engine);

    Spark.get("/admin/login", adminController::showAdmin, engine);
    Spark.post("/admin/login", adminController::loginAdmin, engine);
    Spark.get("/admin/logout", adminController::logoutAdmin, engine);
    //lo de caracteristicas
    Spark.get("/caracteristicas",caracteristicaController::caracteristicas , engine);
    Spark.post("/caracteristicas",caracteristicaController::agregarCaracteristica , engine);
    Spark.delete("/caracteristicas",caracteristicaController::eliminarCaracteristica , engine);

    //------------------------------------------------------------------------------------//

    //Rutas de Mascotas

    Spark.get("/mascotas/nueva", mascotaController::nuevaMascota, engine);

    Spark.get("/mascotas", mascotaController::listarMascotas, engine);

    Spark.post("/mascotas", mascotaController::crearMascota);

    Spark.get("/mascotas/:mascotaId", mascotaController::mascota, engine);

    //------------------------------------------------------------------------------------//

    //Rutas de rescates

    Spark.get("/rescates", mascotaPerdidaController::listarRescatesSinChapita, engine);

    Spark.get("/rescates/todos" , mascotaPerdidaController:: listarRescatesPendientes, engine);

    Spark.get("/rescates/nueva", mascotaPerdidaController::perdidas, engine);

    Spark.get("/rescates/nueva/sinChapita", mascotaPerdidaController::sinChapita, engine);

    Spark.post("/rescates", mascotaPerdidaController::registrarMascotaSinChapita);

    Spark.get("/rescates/nueva/conChapita/detalles", mascotaPerdidaController::conChapita, engine);

    Spark.get("/mascotas/:id/rescates/nueva", mascotaPerdidaController::formularioRescatista, engine); //url que te llevaria el get del qr

    Spark.post("/mascotas/:id/rescatista", mascotaPerdidaController::registrarMascotaConChapita);

    Spark.get("/rescates/gracias", mascotaPerdidaController::agradecer, engine);

    Spark.get("/asociacion/nueva", mascotaController::nuevaAsociacion, engine);

    Spark.post("/asociacion", mascotaController::registrarAsosiacion);
    //------------------------------------------------------------------------------------//

    // Utilities
    Spark.get("/ejemploclase", home::ejemploClase, engine);
    Spark.get("/manualsetsession", loginController::manualSetSessionId, engine);

    Spark.after((request, response) -> {
      if(PerThreadEntityManagers.getEntityManager().isOpen()) {
        PerThreadEntityManagers.closeEntityManager();
      }
    });
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
