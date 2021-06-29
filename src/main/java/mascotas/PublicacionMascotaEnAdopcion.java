package mascotas;


import personas.Duenio;
import personas.Perfil;
import repositorios.RepositorioDeUsuarios;


public class PublicacionMascotaEnAdopcion {

    private Mascota mascota;

    public PublicacionMascotaEnAdopcion(Mascota mascota) {
        this.mascota = mascota;
    }

    public Duenio duenioDePublicacion(){
        return RepositorioDeUsuarios.getInstance().usuarioDuenioDe(this.mascota);
    }

}
