package mascotas;

public class PosibleCaracteristica {
  public String nombreCaracteristica;

  public PosibleCaracteristica(String nombre) {
    this.nombreCaracteristica = nombre;
  }

  public boolean seLlamaAsi(String nombre) {
    return this.nombreCaracteristica.equals(nombre);
  }
}
