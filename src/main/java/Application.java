import services.HogaresService;

public class Application {

  public static void main(String[] args) {
    try {
      HogaresService api = new HogaresService();
    } catch ( Exception e){
      System.out.println(e.toString());
    }
  }

}
