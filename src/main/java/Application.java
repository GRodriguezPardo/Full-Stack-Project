import services.HogaresService;

public class Application {

  public static void main(String[] args) {
    try {
      HogaresService api = new HogaresService();
      //System.out.println(api.getClientResponse("1"));
      System.out.println(api.getHogares("1"));
    } catch ( Exception e){
      System.out.println(e.toString());
    }
  }

}
