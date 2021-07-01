package apis;

import personas.Contacto;

public interface MedioNotificacion {
  void notificarMascotaPerdida(Contacto contacto);

  void notificarInteresEnAdopcion(Contacto contacto);

  void notificarSugerenciaSemanal(Contacto contacto);
}
