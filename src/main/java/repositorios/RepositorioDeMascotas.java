package repositorios;

import mascotas.Mascota;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioDeMascotas implements WithGlobalEntityManager {
  private final static RepositorioDeMascotas INSTANCE = new RepositorioDeMascotas();

  private RepositorioDeMascotas(){
  }

  public static RepositorioDeMascotas instance(){
    return INSTANCE;
  }

  public void agregarMascota(Mascota mascota){
    entityManager().persist(mascota);
  }

  public List<Mascota> obtenerListado() {
    return entityManager().createQuery("select a from Mascota a",Mascota.class).getResultList();
  }
}
