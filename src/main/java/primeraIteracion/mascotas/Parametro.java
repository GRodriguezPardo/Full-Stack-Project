package primeraIteracion.mascotas;

public class Parametro<E> {
  private E valor = null;

  public Parametro() {

  }

  public void setValor(E _valor) {
    this.valor = _valor;
  }

  public E getValor() {
    return valor;
  }

  public Parametro clone() {
    return new Parametro<E>();
  }
}
