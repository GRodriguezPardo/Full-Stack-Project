package repositorios;

import personas.Pregunta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioDePreguntas {
  private final static RepositorioDePreguntas INSTANCE = new RepositorioDePreguntas();
  private final List<Pregunta> preguntas = new ArrayList<>();

  private RepositorioDePreguntas() {
  }

  public static RepositorioDePreguntas getInstance() {
    return INSTANCE;
  }

  public void agregarPregunta(Pregunta pregunta) {
    if (!this.preguntas.contains(pregunta)) {
      this.preguntas.add(pregunta);
    }
  }

  public void eliminarPregunta(Pregunta pregunta) {
    this.preguntas.remove(pregunta);
  }

  public void borrarPreguntas() {
    this.preguntas.clear();
  }

  public List<Pregunta> getPreguntas() {
    return this.preguntas;
  }
  /*El metodo de abajo es para que el que gestiona las preguntas globales vea si ya hizo preguntas
  que esta pensando o tiene mejores o si no. */

  public List<Pregunta> verPreguntasQueIncluyanCadena(String cadena) {
    return this.preguntas.stream().filter(p -> p.getCuerpoDuenio().contains(cadena)).collect(Collectors.toList());
  }
}