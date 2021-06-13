import apis.*;
import apis.dto.AdmisionesDTO;
import apis.dto.HogarDTO;
import apis.dto.HogaresDTO;
import apis.dto.UbicacionDTO;
import exceptions.NoHayNingunaAsociasionException;
import mascotas.*;
import org.junit.jupiter.api.*;

import personas.*;
import repositorios.RepositorioDeAsociaciones;
import repositorios.RepositorioDeUsuarios;
import services.HogaresService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class SegundaIteracionTest {
  MedioNotificacion emailSender = mock(JavaXMail.class);
  MedioNotificacion smsSender = mock(TwilioJava.class);
  HogaresService hogaresService = mock(HogaresService.class);

  @BeforeAll
  public static void agregarPosiblesCaracteristicas() {
    PosiblesCaracteristicas.getInstance().agregarPosibleCaracteristica("Color principal", new Caracteristica<String>());
    PosiblesCaracteristicas.getInstance().agregarPosibleCaracteristica("Esta castrado", new Caracteristica<Boolean>());
  }

  @Test
  public void puedoContactarAUnDuenio() {
    Duenio duenio = this.duenio();
    duenio.contactarDuenio();
    verify(emailSender).notificar(duenio.getPersona().getContactos().get(0));
    verify(smsSender).notificar(duenio.getPersona().getContactos().get(0));
  }

  @Test
  @Disabled
  public void enviarMailNoTiraErrorTESTMANUAL() {
    JavaXMail emailSender = new JavaXMail("unemailejemplar","HolaComoEstas");// _________\/__________ aca pones el mailDestinatario y te fijas que llegue
    assertDoesNotThrow(() -> emailSender.sendEmail("unEjemplo@gmail.com"));
  }

  @Test
  @Disabled
  public void enviarSmsNoTiraErrorTESTMANUAL() {
    TwilioJava twilio = new TwilioJava(null , null , null);//LEER comentarios en smsSender para probar posta con tu telefono
    Contacto contacto = new Contacto("Anonimo" ,"+541165919737" ,"messi@messi.com");
    assertDoesNotThrow(() -> twilio.notificar(contacto));
  }  /*Ahi pones un numero destinatario verificado en la pagina (ese lo esta pero no vas a ver el mensaje , es para mostrar el formato
  valido del numero) y te fijas que te llege el mensaje*/

  @Test
  public void puedoAgregarAsociacionesAlRepo() {
    Asociacion asociacion = new Asociacion(new Posicion(10,20));
    RepositorioDeAsociaciones repo = RepositorioDeAsociaciones.getInstance();
    repo.agregarAsociacion(asociacion);

    assertTrue(repo.getAsociaciones().contains(asociacion));

    repo.removerAsociacion(asociacion);
  }

  @Test
  public void lasPublicacionesSeAsignanCorrectamente() {
    Asociacion asociacion1 = new Asociacion(new Posicion(10,10));
    Asociacion asociacion2 = new Asociacion(new Posicion(20,20));
    PublicacionMascotaPerdida publicacion = new PublicacionMascotaPerdida(this.rescatista());
    RepositorioDeAsociaciones repo = RepositorioDeAsociaciones.getInstance();
    repo.agregarAsociacion(asociacion1);
    repo.agregarAsociacion(asociacion2);


    assertEquals(repo.asociacionMasCercana(publicacion),asociacion1);

    repo.removerAsociacion(asociacion1);
    repo.removerAsociacion(asociacion2);
  }

  @Test
  public void lasPublicacionesSeObtienenCorrectamenteSegunCriterio() {
    PublicacionMascotaPerdida publicacionDesaprobada1 = new PublicacionMascotaPerdida(this.rescatista());
    PublicacionMascotaPerdida publicacionDesaprobada2 = new PublicacionMascotaPerdida(this.rescatista());
    Asociacion unaAsociacion = new Asociacion(new Posicion(10,10));
    RepositorioDeAsociaciones repo = RepositorioDeAsociaciones.getInstance();
    repo.agregarAsociacion(unaAsociacion);
    unaAsociacion.agregarPublicacion(publicacionDesaprobada1);
    unaAsociacion.agregarPublicacion(publicacionDesaprobada2);

    assertTrue(repo.publicacionesAprobadas().isEmpty());
    assertTrue(repo.publicacionesNoAprobadas().contains(publicacionDesaprobada1));
    assertTrue(repo.publicacionesNoAprobadas().contains(publicacionDesaprobada2));

    repo.removerAsociacion(unaAsociacion);
  }

  @Test
  public void seTiraExceptionSiNoHayAsociacionesAlAgregarPublicacion() {
    RepositorioDeAsociaciones repo = RepositorioDeAsociaciones.getInstance();
    PublicacionMascotaPerdida unaPublicacion = new PublicacionMascotaPerdida(this.rescatista());

    assertThrows(NoHayNingunaAsociasionException.class, () -> repo.asociacionMasCercana(unaPublicacion));
  }

  @Test
  public void puedoObtenerLasPublicacionesManejablesDeVoluntario() {
    PublicacionMascotaPerdida publicacionDesaprobada = new PublicacionMascotaPerdida(this.rescatista());
    Asociacion unaAsociacion = new Asociacion(new Posicion(10,10));
    RepositorioDeAsociaciones repo = RepositorioDeAsociaciones.getInstance();
    repo.agregarAsociacion(unaAsociacion);
    unaAsociacion.agregarPublicacion(publicacionDesaprobada);
    Voluntario voluntario = new Voluntario("Jose","AyudoMucho", unaAsociacion);

    assertTrue(voluntario.publicacionesGestionables().contains(publicacionDesaprobada));

    repo.removerAsociacion(unaAsociacion);
  }

  @Test
  public void puedoAgregarTodosLosTiposDePerfilesAlRepo() {
    RepositorioDeUsuarios repo = RepositorioDeUsuarios.getInstance();
    Usuario perfil1 = new Usuario("Luis I","Soy primero",this.duenio());
    Admin perfil2 = new Admin("Luis II", "Soy segundo");
    Voluntario perfil3 = new Voluntario("Luis III", "Soy tercero", new Asociacion(new Posicion(33,33)));

    repo.agregarPerfil(perfil1);
    repo.agregarPerfil(perfil2);
    repo.agregarPerfil(perfil3);

    List<Perfil> perfiles = repo.perfiles();

    assertTrue(perfiles.contains(perfil1));
    assertTrue(perfiles.contains(perfil2));
    assertTrue(perfiles.contains(perfil3));

    repo.removerPerfil(perfil1);
    repo.removerPerfil(perfil2);
    repo.removerPerfil(perfil3);
  }

  @Test
  public void obtenerHogaresDeTransito(){

    HogaresService service = new HogaresService();

    Posicion posicionHogar = new Posicion(1,1);
    Posicion posicionRescatista = new Posicion(0,0);
    Assertions.assertTrue(posicionRescatista.distanciaA(posicionHogar) <= 10);

  }

  public void settearColorPrincipal(MascotaBuilder mascotaBuilder, String color) {
    mascotaBuilder.agregarNuevaCaracteristica("Color principal", color);
  }

  public Mascota mascota() {
    MascotaBuilder mascotaBuilder = new MascotaBuilder();
    mascotaBuilder.setNombre("Sergio Ramos");
    mascotaBuilder.setApodo("Noventa y ramos");
    mascotaBuilder.setEspecie(Especie.PERRO);
    mascotaBuilder.setDescripcion("Un jugador de futbol del real madrid");
    mascotaBuilder.setEdad((short) 35);
    mascotaBuilder.setSexo(Sexo.MACHO);
    mascotaBuilder.setTamanio(Tamanio.CHICO);
    mascotaBuilder.agregarImagen("https://upload.wikimedia.org/wikipedia/commons/4/43/Russia-Spain_2017_%286%29.jpg");
    this.settearColorPrincipal(mascotaBuilder, "Blanco");
    return mascotaBuilder.finalizarMascota();
  }

  public MascotaPerdida mascotaPerdida() {
    String descripcion = "Estaba intentando marcar a Messi";
    List<Image> fotos = new ArrayList<>();

    ImageIcon _foto1 = new ImageIcon("https://images.ole.com.ar/2020/12/31/SiSmW6L4M_340x340__1.jpg");
    Image foto1 = _foto1.getImage();
    fotos.add(foto1);

    ImageIcon _foto2 = new ImageIcon("https://www.diez.hn/csp/mediapool/sites/dt.common.streams.StreamServer.cls?STREAMOID=GCN_RhtV099DtwjsK8LR3c$daE2N3K4ZzOUsqbU5sYuLAvgewimt_YtK9twCcBxuWCsjLu883Ygn4B49Lvm9bPe2QeMKQdVeZmXF$9l$4uCZ8QDXhaHEp3rvzXRJFdy0KqPHLoMevcTLo3h8xh70Y6N_U_CryOsw6FTOdKL_jpQ-&CONTENTTYPE=image/jpeg");
    Image foto2 = _foto2.getImage();
    fotos.add(foto2);

    return new MascotaPerdida(descripcion, fotos, new Posicion(0,0));
  }

  public Rescatista rescatista() {
    PersonaBuilder personaBuilder = new PersonaBuilder();
    personaBuilder.setNombreYApellido("Cristiano Ronaldo");
    personaBuilder.setFechaNacimiento(LocalDate.of(1985, 2, 5));
    Contacto metodoContacto = new Contacto("CR7", "1211113333", "cristiano@ronaldo.com");
    personaBuilder.agregarContacto(metodoContacto);
    personaBuilder.agregarMedioNotificacion(emailSender);
    Rescatista rescatista = new Rescatista(personaBuilder.crearPersona(), LocalDate.now(), this.mascotaPerdida());
    rescatista.getPersona().agregarMedioNotificacion(smsSender);
    return rescatista;
  }

  public Duenio duenio() {
    PersonaBuilder personaBuilder = new PersonaBuilder();
    personaBuilder.setNombreYApellido("Lionel Andres Messi");
    personaBuilder.setFechaNacimiento(LocalDate.of(1987, 6, 24));
    Contacto metodoContacto = new Contacto("Lionel Messi", "112222333", "messi@messi.com");
    personaBuilder.agregarContacto(metodoContacto);
    personaBuilder.agregarMedioNotificacion(emailSender);
    Duenio duenio = new Duenio(personaBuilder.crearPersona());
    duenio.getPersona().agregarMedioNotificacion(smsSender);
    duenio.agregarMascota(this.mascota());
    return duenio;
  }

  public HogarDTO getHogarDTO(){
    HogarDTO hogarDTO = new HogarDTO();
    AdmisionesDTO admisionesDTO = new AdmisionesDTO();
    UbicacionDTO ubicacionDTO = new UbicacionDTO();
    ubicacionDTO.setLat(1.00);
    ubicacionDTO.setLong(1.00);
    admisionesDTO.setPerros(true);
    hogarDTO.setAdmisiones(admisionesDTO);
    hogarDTO.setUbicacion(ubicacionDTO);
    hogarDTO.setPatio(true);
    hogarDTO.setLugares_disponibles(1);
    return hogarDTO;
  }

  public HogaresDTO getHogaresDTO(){
    HogaresDTO hogaresDTO = new HogaresDTO();
    hogaresDTO.setOffset("1");
    hogaresDTO.setTotal(1);
    ArrayList<HogarDTO> listHogarDTO = new ArrayList<>();
    listHogarDTO.add(getHogarDTO());
    hogaresDTO.setHogares(listHogarDTO);
    return hogaresDTO;
  }
}
