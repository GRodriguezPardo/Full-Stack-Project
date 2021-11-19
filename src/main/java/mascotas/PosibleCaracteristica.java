package mascotas;

import persistence.PersistenceId;

import javax.persistence.Entity;

@Entity
public class PosibleCaracteristica extends PersistenceId {
  public String nombreCaracteristica;

  public PosibleCaracteristica(String nombre) {
    this.nombreCaracteristica = nombre;
  }

  private PosibleCaracteristica(){ }

  public boolean seLlamaAsi(String nombre) {
    return this.nombreCaracteristica.equals(nombre);
  }

  public String getValor(){return this.nombreCaracteristica ;}
}
