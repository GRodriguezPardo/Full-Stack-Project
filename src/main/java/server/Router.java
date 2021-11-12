package server;

import controllers.HomeController;
import controllers.LoginController;
import controllers.MascotaController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

  public static void configure() {
    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
    HomeController home = new HomeController();
    MascotaController mascotaController = MascotaController.instance();
    LoginController loginController = new LoginController();

    Spark.get("/",home::index,engine);
    Spark.get("/login", loginController::show, engine);
    Spark.get("/error",home::error,engine);
    Spark.post("/login", loginController::login, engine);
    Spark.get("/signup", loginController::showSignUp, engine);
    Spark.post("/signup", loginController::signUp, engine);

    //Rutas de Mascotas
    Spark.get("/mascotas/mismascotas", mascotaController::listarMascotas);
    Spark.get("/mascotas/encontradas", mascotaController::encontradas,engine);
    Spark.get("/mascotas/registrar",mascotaController::registrar,engine);
    Spark.get("/mascotas/perdidas",mascotaController::perdidas,engine);
    Spark.get("/mascotas/mismascotas/:mascota",mascotaController::mascota,engine);
    Spark.get("/mascotas/perdidas/conChapita", mascotaController::conChapita,engine);
    Spark.get("mascotas/perdidas/sinChapita",mascotaController::sinChapita,engine);
    Spark.post("mascotas/perdidas/conchapita/registrar",mascotaController::registrarMascotaConChapita);
    Spark.post("mascotas/perdidas/sinchapita/registrar",mascotaController::registrarMascotaSinChapita);

    Spark.get("mascotas/perdidas/gracias", mascotaController::agradecer,engine);

    Spark.get("/mascotas",mascotaController::listado);
    Spark.post("/mascotas/crear",mascotaController::crearMascota);

    Spark.get("/ejemploclase",home::ejemploClase);
  }
}
