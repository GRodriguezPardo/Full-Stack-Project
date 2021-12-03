package apis;

import persistence.PersistenceId;
import personas.Contacto;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class MedioNotificacion extends PersistenceId {
  public abstract void notificarMascotaPerdida(Contacto contacto);

  public abstract void notificarInteresEnAdopcion(Contacto contacto);

  public abstract void notificarSugerenciaSemanal(Contacto contacto, Integer cantidad);

  public abstract void notificarMailDeBaja(Contacto unContacto);
}
