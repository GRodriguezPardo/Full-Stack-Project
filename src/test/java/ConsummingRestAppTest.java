import apis.dto.EmailDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ConsummingRestAppTest {
  private static EmailDTO emailDTO;

  @BeforeAll
  public static void setUp(){
    emailDTO = new EmailDTO("test@gmail.com");
  }

  @Test
  public void testRequestEmailDTO(){
    Assertions.assertEquals(emailDTO.toString(),
        "{"+'"'+"email"+'"'+':'+'"'+ emailDTO.getEmail()+'"'+"}");
  }

}
