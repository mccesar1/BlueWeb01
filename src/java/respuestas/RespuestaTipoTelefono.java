
package respuestas;

import clases.Respuesta;
import clases.TipoTelefono;
import java.util.List;


public class RespuestaTipoTelefono {
    
    private Respuesta respuesta;
    private List<TipoTelefono> listaTelefono;

    public RespuestaTipoTelefono() {
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }

    public List<TipoTelefono> getListaTelefono() {
        return listaTelefono;
    }

    public void setListaTelefono(List<TipoTelefono> listaTelefono) {
        this.listaTelefono = listaTelefono;
    }
    
    
    
}
