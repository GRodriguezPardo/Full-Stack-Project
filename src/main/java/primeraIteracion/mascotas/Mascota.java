package primeraIteracion.mascotas;

import primeraIteracion.exceptions.FaltanDatosException;

import java.util.*;

public class Mascota {
  private Especie especie;
  private String nombre;
  private String apodo;
  private Short edad;
  private Sexo sexo;
  private String descripcion;
  //private List<Foto> fotos;
  private HashMap<String,Caracteristica> caracteristicas;


  public Mascota(Especie _especie,
                 String _nombre,
                 String _apodo,
                 Short _edad,
                 Sexo _sexo,
                 String _descripcion,
                 HashMap<String,Caracteristica> _caracteristicas) {
    if(Objects.isNull(_nombre)
       || Objects.isNull(_edad)
       || Objects.isNull(_sexo)
       || Objects.isNull(_descripcion)){
      throw new FaltanDatosException(
          "Se debe proveer un nombre, una edad aproximada, el sexo, y una descripcion"
      );
    }
    this.especie = _especie;
    this.nombre = _nombre;
    this.apodo = _apodo;
    this.edad = _edad;
    this.sexo = _sexo;
    this.descripcion = _descripcion;
    this.caracteristicas = new HashMap<>(_caracteristicas);
  }
}
