
package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import sesiones.Sesion;


@ViewScoped
@ManagedBean(name="indexBean")
public class IndexBean {
    
     private List<String> images;    //declaramos las listas
     
     private int idUsuario;
    private String usuario;
     private String nombre;
     
    @PostConstruct
    public void verificarSesion(){
        
        try {
             idUsuario=Sesion.sesionId();//lo que vamos a traer de las funciones de sesion en clase sesion
             usuario=Sesion.sesionUsuario();
             nombre=Sesion.sesionNombre();
        } catch (Exception e) {
            try {
                //si no trae los datos de sesion, redirecciona al login
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+ "/faces/login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(IndexBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         //------------esto es para la galeria-------------------------------
        images = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            images.add("nature" + i + ".jpg");
        }
        //------------------------------------------------------------------     

}
  
     public List<String> getImages() {
        return images;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
     
}
