
package respuestas;


import clases.Ciudad;
import clases.Respuesta;
import java.util.List;


public class RespuestaCiudad {
    
   //esta clase va a guardar la lista ciudad y la respuesta
  
    private List<Ciudad> listaCiudad;
    private Respuesta respuesta;

    public RespuestaCiudad() {
    }

    public List<Ciudad> getListaCiudad() {
        return listaCiudad;
    }

    public void setListaCiudad(List<Ciudad> listaCiudad) {
        this.listaCiudad = listaCiudad;
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }
    
    
}