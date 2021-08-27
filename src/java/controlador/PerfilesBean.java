package controlador;

import controllers.SAccesosJpaController;
import controllers.SPerfilesAccesosJpaController;
import controllers.SPerfilesJpaController;
import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import entidades.SAccesos;
import entidades.SPerfiles;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;
import sesiones.Sesion;
/**
 *
 * @author Erick Chavarria
 */
@ManagedBean(name = "perfilesBean")
public class PerfilesBean {

    private List<SPerfiles> listaPerfiles;//inicialisa la lista;
    private SPerfiles perfiles;
    private String nombre;
    private String descripcion;
    private DualListModel<SAccesos> dualListAccesos;
    private List<SAccesos> listaAccesosDisponibles;
    private List<SAccesos> listaAccesosActuales;

    public PerfilesBean() {

        perfiles = new SPerfiles();

    }

    @PostConstruct
    public void cargarPickList() {//inicializa la pickList 
        SAccesosJpaController Modelo = new SAccesosJpaController();
        try {
            listaAccesosDisponibles = Modelo.findSAccesosEntities();
            listaAccesosActuales = new ArrayList<>();
            dualListAccesos = new DualListModel<>(listaAccesosDisponibles, listaAccesosActuales);
            loadProfileData();//para que cargue la lista del combo desde que inica la pagina

        } catch (Exception ex) {
                Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void loadProfileData() {
        SPerfilesJpaController perfilModelo = new SPerfilesJpaController();
        listaPerfiles = perfilModelo.findSPerfilesEntities();//lista para el combo 
    }
    //-------------------------funcion para mostrar la lista --------------------------------------------------------------
    public void listarPerfiles() {

        try {
            SPerfilesJpaController perfilModelo = new SPerfilesJpaController();
            // combo = telefoniaModelo.findCTelefoniaEntities();//lista para los datos que se van a mostrar en el combo 
            listaPerfiles = perfilModelo.findSPerfilesEntities();
        } catch (Exception ex) {
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //-------------------------funcion para borrar----------------------------------------------------------------------------
    public void eliminarPerfiles(int id) {

        try {
            SPerfilesJpaController perfilesModelo = new SPerfilesJpaController();
            perfilesModelo.destroy(id);
            listarPerfiles();
            addMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Perfil eliminado");
        } catch (Exception ex) {
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Perfil no eliminado");
        }

    }

    //------------------------funcion para agregar-----------------------------------------------------------------------------
    public void agregarPerfiles() {
        
         SPerfilesJpaController perfilModelo = new SPerfilesJpaController();
            SPerfilesAccesosJpaController Modelo = new SPerfilesAccesosJpaController();
        List<SAccesos> listaAccesos = new ArrayList<>();  
        
          listaAccesosActuales= perfilModelo.AccesosAsignados(perfiles);
          listaAccesosDisponibles = perfilModelo.AccesosDisponibles(perfiles);
          
        dualListAccesos = new DualListModel<>(listaAccesosDisponibles, listaAccesosActuales);
        listaAccesos = dualListAccesos.getTarget();
        try {
                     
            perfiles.setFechaAlta(new Date());
            perfiles.setFechaServidor(new Date());
            perfiles.setActivo(true);
            perfiles.setIdUsuarioModifica(Sesion.sesionId());
            
  
            perfilModelo.create(perfiles);
            
         
            listarPerfiles();
            addMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Perfil agregado");

        } catch (Exception e) {
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Exitoso", "Perfil no agregado");
        }

    }
    //------------------------funcion para editar-----------------------------------------------------------------------------

    public void editarPerfiles(RowEditEvent event) {

        SPerfilesJpaController perfilModelo = new SPerfilesJpaController();
        SPerfiles perfilesEdit = (SPerfiles) event.getObject();

        if (!nombre.equals("")) {
            perfilesEdit.setNombrePerfil(nombre);
        }
        if (!descripcion.equals("")) {
            perfilesEdit.setDescripcion(descripcion);
        }
        perfilesEdit.setIdUsuarioModifica(Sesion.sesionId());
        perfilesEdit.setFechaServidor(new Date());
        try {            
            perfilModelo.edit(perfilesEdit);
            listarPerfiles();
            addMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Perfil editado");

        } catch (Exception e) {
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Perfil no editado");
        }
        
    }
  
   
     public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public List<SPerfiles> getListaPerfiles() {
        return listaPerfiles;
    }

    public void setListaPerfiles(List<SPerfiles> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

    public SPerfiles getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(SPerfiles perfiles) {
        this.perfiles = perfiles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public DualListModel<SAccesos> getDualListAccesos() {
        return dualListAccesos;
    }

    public void setDualListAccesos(DualListModel<SAccesos> dualListAccesos) {
        this.dualListAccesos = dualListAccesos;
    }

    public List<SAccesos> getListaAccesosDisponibles() {
        return listaAccesosDisponibles;
    }

    public void setListaAccesosDisponibles(List<SAccesos> listaAccesosDisponibles) {
        this.listaAccesosDisponibles = listaAccesosDisponibles;
    }

    public List<SAccesos> getListaAccesosActuales() {
        return listaAccesosActuales;
    }

    public void setListaAccesosActuales(List<SAccesos> listaAccesosActuales) {
        this.listaAccesosActuales = listaAccesosActuales;
    }
    

}
