package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.CiudadModelo;

import sesiones.Sesion;

@ManagedBean(name = "indexBean")
public class IndexBean {
   private String usuario;

    private List<String> images;    //declaramos las listas

    private String nombre;

    @PostConstruct
             
    public void init() {
        
         
        try {
             usuario = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");//guardamos lo uqe le mandamos de usuario
            
        } catch (Exception e) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/faces/login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(IndexBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
      
        
        nombre = Sesion.sesionNombre();

        //------------esto es para la galeria-------------------------------
        images = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            images.add("nature" + i + ".jpg");
        }
        //------------------------------------------------------------------     

    }

//<editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public List<String> getImages() {
        return images;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
//</editor-fold>

}
