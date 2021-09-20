package cron;

import personas.Asociacion;

import java.util.List;

public class TareaSemanal {
  static void tareaSemanal(List<Asociacion> asociasiones) {
    asociasiones.forEach(unaAsociacion -> unaAsociacion.realizarRecomendacionesSemanales());
  }
}