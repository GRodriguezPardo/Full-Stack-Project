import apis.HogaresDeTransitoAPI;
import services.HogaresService;

public class Application {

  public static void main(String[] args) {
    try {
      HogaresDeTransitoAPI hogaresDeTransitoAPI = new HogaresService();
      System.out.println(hogaresDeTransitoAPI.getHogares("1"));
    } catch ( Exception e){
      System.out.println(e);
    }
  }

}
