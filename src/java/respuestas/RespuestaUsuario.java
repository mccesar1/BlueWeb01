
package respuestas;

import clases.Respuesta;
import clases.Usuario;
import java.util.ArrayList;


public class RespuestaUsuario {
    
    private Respuesta respuesta;
    private Usuario login;//aqui login es de tipo usuario que tiene el id y el nombre 

    public RespuestaUsuario() {
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }


    public Usuario getLogin() {
        return login;
    }

    public void setLogin(Usuario login) {
        this.login = login;
    }
    
    
    
}
