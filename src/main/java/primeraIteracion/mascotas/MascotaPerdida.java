package primeraIteracion.mascotas;

public class MascotaPerdida {
  //private List<Foto> fotos;
  private String descripcionEstado;
  private Integer posicionX;
  private Integer posicionY;


  public MascotaPerdida(String descripcionEstado,
                        Integer posicionX,
                        Integer posicionY) {
    this.descripcionEstado = descripcionEstado;
    this.posicionX = posicionX;
    this.posicionY = posicionY;
  }
}
