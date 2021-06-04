import apis.dto.RequestEmailDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ConsummingRestAppTest {
  private static RequestEmailDTO requestEmailDTO;

  @BeforeAll
  public static void setUp(){
    requestEmailDTO = new RequestEmailDTO("test@gmail.com");
  }

  @Test
  public void testRequestEmailDTO(){
    Assertions.assertEquals(requestEmailDTO.toString(),
        "{"+'"'+"email"+'"'+':'+'"'+requestEmailDTO.getEmail()+'"'+"}");
  }

}
