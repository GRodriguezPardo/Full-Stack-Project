import apis.dto.AdmisionesDTO;
import apis.dto.HogarDTO;
import mascotas.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import personas.Posicion;
import services.HogaresService;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class HogaresServiceTest {

  private HogaresService service;
  private Posicion posicionRescatista = posicionRescatista = new Posicion(0, 0);

  @BeforeEach
  public void setup(){
    service = Mockito.mock(HogaresService.class);
    Mockito.doReturn(Arrays.asList()).when(service).getHogarMascota(getMascota(Especie.PERRO, Tamanio.GRANDE),
        posicionRescatista, 100);

  }

  @Test
  public void obtenerHogaresDeTransito() {

    when(service.getHogarMascota(getMascota(Especie.PERRO, Tamanio.GRANDE),
        posicionRescatista, 100)).thenReturn(Arrays.asList());

    Assertions.assertTrue(
        service.getHogarMascota(getMascota(Especie.PERRO, Tamanio.GRANDE),
            posicionRescatista, 100).isEmpty()
    );
  }

  /**
   * mascot GRANDE y Hogar sin Patio, por regla de admisión no debiera haber
   */
  @Test
  public void obtenerHogaresMascotaGrandeYHogarSinPatio() {
    Assertions.assertEquals(
        service.getHogarMascota(getMascota(Especie.GATO, Tamanio.GRANDE),
            posicionRescatista,
            100)
            .stream().filter(hogar -> !hogar.getPatio())
            .count(), 0
    );
  }

  /**
   * Mascota GRANDE y Hogar con Patio, por regla de admisión no debiera haber:
   */
  @Test
  public void obtenerHogaresMascotaGrandeYHogarConPatio() {

    Mockito.doReturn(Arrays.asList()).when(service).getHogarMascota(getMascota(Especie.GATO, Tamanio.GRANDE),
        posicionRescatista, 100);

    Assertions.assertFalse(
        service.getHogarMascota(getMascota(Especie.GATO, Tamanio.GRANDE), posicionRescatista, 100)
            .stream()
            .anyMatch(HogarDTO::getPatio)
    );
  }

  /**
   * Mascota GRANDE y Hogar sin Patio, por regla de admisión no debiera haber
   */
  @Test
  public void obtenerHogaresMascotaChicaYHogarSinPatio() {
    Assertions.assertFalse(
        service.getHogarMascota(getMascota(Especie.GATO, Tamanio.CHICO),
            posicionRescatista, 100)
            .stream().anyMatch(hogar -> !hogar.getPatio())
    );
  }

  /**
   * Mascota GRANDE y Hogar con Patio, por regla de admisión debiera haber:
   */
  @Test
  public void obtenerHogaresMascotaChicaYHogarConPatio() {
    Assertions.assertFalse(
        service.getHogarMascota(getMascota(Especie.GATO, Tamanio.CHICO),
            posicionRescatista, 100)
            .stream().anyMatch(hogar -> !hogar.getPatio())
    );
  }

  public Mascota getMascota(Especie especie, Tamanio tamanio){

    MascotaBuilder builder = new MascotaBuilder();

    builder.setNombre("Teodoro");
    builder.setApodo("El Grande");
    builder.setEspecie(especie);
    builder.setDescripcion("Un jugador de futbol del real madrid");
    builder.setEdad((short) 35);
    builder.setSexo(Sexo.MACHO);
    builder.setTamanio(tamanio);
    builder.agregarImagen("https://upload.wikimedia.org/wikipedia/commons/4/43/Russia-Spain_2017_%286%29.jpg");

    return builder.finalizarMascota();
  }

  public HogarDTO getHogarDTO(){
    HogarDTO hogarDTO = new HogarDTO();

    hogarDTO.setNombre("Ayudacan");
    hogarDTO.setTelefono("+541134586100");
    hogarDTO.setPatio(true);
    hogarDTO.setCapacidad(150);
    hogarDTO.setLugares_disponibles(49);
    hogarDTO.setCaracteristicas(Arrays.asList());
    hogarDTO.setAdmisiones(Mockito.mock(AdmisionesDTO.class));

    return hogarDTO;
  }

}
