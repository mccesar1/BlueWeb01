
package sesiones;


import clases.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import modelo.CiudadModelo;

@ManagedBean(name = "sesionBean")
public class Sesion {
     

    public void logueado() {
      
        String usuario;
        usuario = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");//guardamos lo uqe le mandamos de usuario
        try {
            
            if (usuario == null) {
                
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/faces/login.xhtml");
            }
        } catch (IOException iOException) {
             Logger.getLogger(CiudadModelo.class.getName()).log(Level.SEVERE, null, iOException);
        }
    }
    
    
    
      public static int sesionId(){
        int idUsuario;
        idUsuario = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("IdUsuario");//guardamos lo uqe le mandamos de usuario
        
        return idUsuario;
    
    
    }
     
    public static String sesionUsuario(){
        String usuario;
        usuario = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");//guardamos lo uqe le mandamos de usuario
        
        return usuario;
    }
    
     public static String sesionNombre(){
        String nombre;
        nombre = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Nombre");//guardamos lo uqe le mandamos de usuario
        
        return nombre;
    
    
    }
     
    
}
