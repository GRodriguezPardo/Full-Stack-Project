package controllers;

import mascotas.Especie;
import mascotas.MascotaBuilder;
import mascotas.Sexo;
import mascotas.Tamanio;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import repositorios.RepositorioDeMascotas;
import repositorios.RepositorioDeRescates;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.math.BigDecimal;

public class MascotaController implements WithGlobalEntityManager, TransactionalOps {
  private final static  MascotaController INSTANCE = new MascotaController();

  private MascotaController(){
  }

  public static MascotaController instance (){
    return INSTANCE;
  }

  public ModelAndView encontradas(Request request, Response response) {
    return new ModelAndView(null,"encontradas.html.hbs");
  }

  public ModelAndView registrar(Request request, Response response) {
    return new ModelAndView(null,"registrar.html.hbs");
  }

  public ModelAndView perdidas(Request request, Response response) {
    return new ModelAndView(null,"perdidas.html.hbs");
  }

  public ModelAndView conChapita(Request request, Response response) {
    return new ModelAndView(null,"conChapita.html.hbs");
  }

  public ModelAndView mascota(Request request, Response response) {
    return new ModelAndView(null,"mascota.html.hbs");
  }

  public Void crearMascota(Request request, Response response) {
    MascotaBuilder mascota = new MascotaBuilder();

    try {

      mascota.setApodo(request.queryParams("nombre"));
      mascota.setNombre(request.queryParams("nombre"));
      mascota.setTamanio((request.queryParams("mascota") == "chico")?Tamanio.CHICO:Tamanio.MEDIANO);
      mascota.setEspecie((request.queryParams("especie") == "gato")? Especie.GATO:Especie.PERRO);
      mascota.setEdad(Short.parseShort("7"));
      mascota.setSexo(Sexo.HEMBRA);
      mascota.agregarImagen("");
      withTransaction(() ->{
          RepositorioDeMascotas.instance().agregarMascota(mascota.finalizarMascota());
      });

    }catch (RuntimeException e){
      response.redirect("/error");
    }
    response.status(200);
    response.body("OK");
    response.redirect("/registrar");
    return null;
  }

  public Void listado(Request request, Response response) {
    response.status(200);
    response.body(RepositorioDeMascotas.instance().obtenerListado().toString());
    return null;
  }
}
