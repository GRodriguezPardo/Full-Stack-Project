package repositorios;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import personas.Pregunta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RepositorioDePreguntas implements WithGlobalEntityManager {
  private final static RepositorioDePreguntas INSTANCE = new RepositorioDePreguntas();
  //private final List<Pregunta> preguntas = new ArrayList<>();

  private RepositorioDePreguntas() {
  }

  public static RepositorioDePreguntas getInstance() {
    return INSTANCE;
  }

  public void agregarPregunta(Pregunta pregunta) {
    if (Objects.isNull(entityManager().find(Pregunta.class, pregunta.getId()))) {
      this.persisirPregunta(pregunta);
    }
  }

  private void persisirPregunta(Pregunta pregunta) {
    entityManager().persist(pregunta);
  }

  public void eliminarPregunta(Pregunta pregunta) {
    //this.preguntas.remove(pregunta);
    Pregunta preguntaBorrar = entityManager().find(Pregunta.class, pregunta.getId());
    entityManager().remove(preguntaBorrar);
  }

  public void borrarPreguntas() {
    //this.preguntas.clear();
    this.getPreguntas().forEach(unaPregunta -> this.eliminarPregunta(unaPregunta));
  }

  @SuppressWarnings("unchecked")
  public List<Pregunta> getPreguntas() {
    //return this.preguntas;
    return entityManager()
        .createQuery("from Pregunta").getResultList();
  }
  /*El metodo de abajo es para que el que gestiona las preguntas globales vea si ya hizo preguntas
  que esta pensando o tiene mejores o si no. */

  public List<Pregunta> verPreguntasQueIncluyanCadena(String cadena) {
    return this.getPreguntas().stream().filter(p -> p.getCuerpoDuenio().contains(cadena)).collect(Collectors.toList());
  }
}