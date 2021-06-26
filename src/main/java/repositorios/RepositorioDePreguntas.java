package repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioDePreguntas {
  private final static RepositorioDePreguntas INSTANCE = new RepositorioDePreguntas();
  private final List<String> preguntasCreadas = new ArrayList<>();
  private final List<String> preguntasElegidas = new ArrayList<>();

  private RepositorioDePreguntas() {
  }

  public static RepositorioDePreguntas getInstance() {
    return INSTANCE;
  }

  public void agregarPregunta(String pregunta) {
    if (!this.preguntasCreadas.contains(pregunta)) {
      this.preguntasCreadas.add(pregunta);
    }
  }

  public void elegirPregunta(String pregunta) {
    if (!this.preguntasElegidas.contains(pregunta)) {
      this.preguntasElegidas.add(pregunta);
    }
  }

  public void eliminarPregunta(String pregunta) {
    this.preguntasElegidas.remove(pregunta);
  }

  public void borrarPreguntas() {
    this.preguntasElegidas.clear();
  }
  public List<String> getPreguntasElegidas() {
    return this.preguntasElegidas;
  }
  public List<String> getPreguntas() {
    return this.preguntasCreadas;
  }
  /*Los 2 metodos de abajo son para que la organizacion vea si ya hizo preguntas
  que esta pensando o tiene mejores o si no. */

  public List<String> verPreguntasQueIncluyanCadena(String cadena) {
    return this.preguntasCreadas.stream().filter(p -> p.contains(cadena)).collect(Collectors.toList());
  }

  public List<String> verPreguntasElegidasQueIncluyanCadena(String cadena) {
    return this.preguntasElegidas.stream().filter(p -> p.contains(cadena)).collect(Collectors.toList());
  }
}
