
package sesiones;


import javax.faces.context.FacesContext;

public class Sesion {
    
    
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
