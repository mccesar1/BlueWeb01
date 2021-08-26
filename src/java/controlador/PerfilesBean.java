
package controlador;
import controllers.SPerfilesJpaController;
import entidades.SPerfiles;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;



/**
 *
 * @author Erick Chavarria
 */

@ManagedBean(name="perfilesBean")
public class PerfilesBean {
    
    private List<SPerfiles> listaPerfiles;//inicialisa la lista;

   
     @PostConstruct
     //-------------------------funcion para mostrar la lista --------------------------------------------------------------
    public void listarPerfiles() {

        try {
           SPerfilesJpaController perfilModelo = new SPerfilesJpaController();
           // combo = telefoniaModelo.findCTelefoniaEntities();//lista para los datos que se van a mostrar en el combo 
           listaPerfiles= perfilModelo.findSPerfilesEntities();
        } catch (Exception ex) {
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

      //-------------------------funcion para borrar----------------------------------------------------------------------------
 
    
    
    public List<SPerfiles> getListaPerfiles() {
        return listaPerfiles;
    }

    public void setListaPerfiles(List<SPerfiles> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

    
    
    
    
}
