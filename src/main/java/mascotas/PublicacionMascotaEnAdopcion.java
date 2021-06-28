package mascotas;


import personas.Perfil;
import repositorios.RepositorioDeUsuarios;


public class PublicacionMascotaEnAdopcion {

    private Mascota mascota;

    public PublicacionMascotaEnAdopcion(Mascota mascota) {
        this.mascota = mascota;
    }

    public Perfil duenioDePublicacion(){
        return RepositorioDeUsuarios.getInstance().duenioDe(this.mascota);
    }

}
