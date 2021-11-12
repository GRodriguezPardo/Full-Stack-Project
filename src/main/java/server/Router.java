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

    Spark.get("/",home::index,engine);
    Spark.get("/login", LoginController::show, engine);
    Spark.get("/error",home::error,engine);
    Spark.post("/login", LoginController::login, engine);

    //Rutas de Mascotas
    Spark.get("/mascotas/encontradas", mascotaController::encontradas,engine);
    Spark.get("/mascotas/registrar",mascotaController::registrar,engine);
    Spark.get("/mascotas/perdidas",mascotaController::perdidas,engine);
    Spark.get("/mascotas/:mascota",mascotaController::mascota,engine);

    Spark.get("/mascotas/perdidas/conChapita", mascotaController::conChapita,engine);
    Spark.get("mascotas/perdidas/sinChapita",mascotaController::sinChapita,engine);
    Spark.post("mascotas/perdidas/conChapita/registrar-mascota-perdida",mascotaController::registrarMascotaConChapita);
    Spark.post("mascotas/perdidas/sinChapita/registrar-mascota-perdida",mascotaController::registrarMascotaSinChapita);

    Spark.get("/mascotas",mascotaController::listado);
    Spark.post("/mascotas/crear",mascotaController::crearMascota);

    Spark.get("/ejemploclase",home::ejemploClase);


  }
}
