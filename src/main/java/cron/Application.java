package cron;

import personas.Asociacion;
import repositorios.RepositorioDeAsociaciones;

import java.util.List;

public class Application {

  public static void main(String[] args) {

    List<Asociacion> asociasiones = RepositorioDeAsociaciones.getInstance().getAsociaciones();

    TareaSemanal.tareaSemanal(asociasiones);
  }

}
