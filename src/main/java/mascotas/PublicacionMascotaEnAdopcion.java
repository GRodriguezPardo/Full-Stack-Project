package mascotas;


import personas.Contacto;
import personas.Duenio;
import repositorios.RepositorioDeUsuarios;


public class PublicacionMascotaEnAdopcion {

    private Mascota mascota;

    public PublicacionMascotaEnAdopcion(Mascota mascota) {
        this.mascota = mascota;
    }

    public void notificarADueno(){
        this.duenioDePublicacion().contactarDuenio();
    }

    public Duenio duenioDePublicacion(){
        return RepositorioDeUsuarios.getInstance().usuarioDuenioDe(this.mascota);
    }

}
