package mascotas;


import personas.Duenio;
import repositorios.RepositorioDeUsuarios;


public class PublicacionMascotaEnAdopcion {

    public Mascota getMascota() {
        return mascota;
    }

    private Mascota mascota;

    public PublicacionMascotaEnAdopcion(Mascota mascota) {
        this.mascota = mascota;
    }


    public void notificarADuenoPorInteresado(){
        this.duenioDePublicacion().contactarDuenioPorInteresado();
    }

    public Duenio duenioDePublicacion(){
        return RepositorioDeUsuarios.getInstance().usuarioDuenioDe(this.mascota);
    }

}
