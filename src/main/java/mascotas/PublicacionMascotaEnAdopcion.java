package mascotas;


import personas.Duenio;
import personas.Pregunta;
import personas.Respuesta;
import repositorios.RepositorioDeUsuarios;

import java.util.ArrayList;
import java.util.List;


public class PublicacionMascotaEnAdopcion {

    public Mascota getMascota() {
        return mascota;
    }

    private Mascota mascota;

    private final List<Respuesta> respuestas = new ArrayList<>();

    public PublicacionMascotaEnAdopcion(Mascota mascota) {
        this.mascota = mascota;
    }


    public void notificarADuenoPorInteresado(){
        this.duenioDePublicacion().contactarDuenioPorInteresado();
    }

    public Duenio duenioDePublicacion(){
        return RepositorioDeUsuarios.getInstance().usuarioDuenioDe(this.mascota);
    }


    public void agregarRespuesta(Respuesta respuesta) {
                 this.respuestas.add(respuesta);
        }
}
