package repositorios;

import mascotas.Mascota;
import mascotas.MascotaPerdida;
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

  public Mascota obtenerMascota(String id){
    return entityManager().createQuery("SELECT a FROM Mascota a WHERE a.id = '" + id + "'",Mascota.class).getSingleResult(); // todo ver porque esta query da error }
     }
}
