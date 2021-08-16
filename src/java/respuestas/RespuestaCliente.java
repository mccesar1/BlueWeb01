
package respuestas;

import clases.Clientes;
import clases.Respuesta;
import java.util.List;


public class RespuestaCliente {
    
    private Respuesta respuesta;
    private List<Clientes> listaClientes;

    public RespuestaCliente() {
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }

    public List<Clientes> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Clientes> listaClientes) {
        this.listaClientes = listaClientes;
    }
    
     
}
