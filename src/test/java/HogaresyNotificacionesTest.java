import apis.JavaXMail;
import apis.TwilioJava;
import apis.dto.HogarDTO;
import mascotas.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import personas.Contacto;
import personas.Posicion;
import services.HogaresService;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class HogaresyNotificacionesTest {
  Fixture fixture = new Fixture();

  @BeforeAll
  public static void agregarPosiblesCaracteristicas() {
    PosiblesCaracteristicas.getInstance().agregarPosibleCaracteristica("Color principal", new Caracteristica<String>());
    PosiblesCaracteristicas.getInstance().agregarPosibleCaracteristica("Esta castrado", new Caracteristica<Boolean>());
  }

  @Test
  @Disabled
  public void enviarMailNoTiraErrorTESTMANUAL() {
    JavaXMail emailSender = new JavaXMail("unemailejemplar", "HolaComoEstas");// _________\/__________ aca pones el mailDestinatario y te fijas que llegue
    assertDoesNotThrow(() -> emailSender.sendEmail("unEjemplo@gmail.com", "Encontramos a tu mascota", "Ejemplo"));
  }

  @Test
  @Disabled
  public void enviarSmsNoTiraErrorTESTMANUAL() {
    TwilioJava twilio = new TwilioJava(null, null, null);//LEER comentarios en smsSender para probar posta con tu telefono
    Contacto contacto = new Contacto("Anonimo", "+541165919737", "messi@messi.com");
    assertDoesNotThrow(() -> twilio.notificarMascotaPerdida(contacto));
  }  /*Ahi pones un numero destinatario verificado en la pagina (ese lo esta pero no vas a ver el mensaje , es para mostrar el formato
  valido del numero) y te fijas que te llege el mensaje*/

  @Test
  public void obtenerHogaresDeTransito() {

    HogaresService service = new HogaresService();

    Posicion posicionHogar = new Posicion(1, 1);
    Posicion posicionRescatista = new Posicion(0, 0);
    Assertions.assertTrue(posicionRescatista.distanciaA(posicionHogar) <= 10);

    Assertions.assertFalse(
            service.getHogarMascota(fixture.mascota(false), posicionRescatista, 100).isEmpty()
    );
  }

  @Test
  public void obtenerHogaresAyudacan() {

    HogaresService service = new HogaresService();
    Posicion posicionRescatista = new Posicion(0, 0);
    HogarDTO hogarAyudacan =
            service.getHogarMascota(fixture.mascota(false), posicionRescatista, 100)
                    .stream()
                    .filter(x -> (x.getNombre().equals("Ayudacan")))
                    .findFirst()
                    .get();

    Assertions.assertEquals(hogarAyudacan.getNombre(), "Ayudacan");
    Assertions.assertEquals(hogarAyudacan.getTelefono(), "+541134586100");
    Assertions.assertTrue(hogarAyudacan.getAdmisiones().getPerros());
    Assertions.assertFalse(hogarAyudacan.getAdmisiones().getGatos());
    Assertions.assertEquals(hogarAyudacan.getCapacidad(), 150);
    Assertions.assertEquals(hogarAyudacan.getLugares_disponibles(), 49);
    Assertions.assertTrue(hogarAyudacan.getPatio());
    Assertions.assertTrue(hogarAyudacan.getCaracteristicas().isEmpty());
  }

  @Test
  public void obtenerHogaresMascotaGrandeYHogarSinPatio() {
    Mascota mascota = fixture.mascotaBuilder(Especie.GATO, Tamanio.GRANDE);
    HogaresService service = new HogaresService();
    //mascot GRANDE y Hogar sin Patio, por regla de admisión no debiera haber:
    Assertions.assertEquals(
            service.getHogarMascota(mascota, new Posicion(0, 0), 100)
                    .stream().filter(hogar -> !hogar.getPatio())
                    .count(), 0
    );
  }

  @Test
  public void obtenerHogaresMascotaGrandeYHogarConPatio() {
    Mascota mascota = fixture.mascotaBuilder(Especie.GATO, Tamanio.GRANDE);
    HogaresService service = new HogaresService();
    //Mascota GRANDE y Hogar con Patio, por regla de admisión no debiera haber:
    Assertions.assertTrue(
            service.getHogarMascota(mascota, new Posicion(0, 0), 100)
                    .stream()
                    .anyMatch(HogarDTO::getPatio)
    );
  }

  @Test
  public void obtenerHogaresMascotaChicaYHogarSinPatio() {
    Mascota mascota = fixture.mascotaBuilder(Especie.GATO, Tamanio.CHICO);
    HogaresService service = new HogaresService();
    //mascot GRANDE y Hogar sin Patio, por regla de admisión no debiera haber:
    Assertions.assertFalse(
            service.getHogarMascota(mascota, new Posicion(0, 0), 100)
                    .stream().anyMatch(hogar -> !hogar.getPatio())
    );
  }

  @Test
  public void obtenerHogaresMascotaChicaYHogarConPatio() {
    Mascota mascota = fixture.mascotaBuilder(Especie.GATO, Tamanio.CHICO);
    HogaresService service = new HogaresService();
    //mascot GRANDE y Hogar sin Patio, por regla de admisión no debiera haber:
    Assertions.assertFalse(
            service.getHogarMascota(mascota, new Posicion(0, 0), 100)
                    .stream().anyMatch(hogar -> !hogar.getPatio())
    );
  }


}
