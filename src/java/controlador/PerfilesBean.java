package controlador;

import controllers.SPerfilesJpaController;
import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import entidades.SPerfiles;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

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

    public PerfilesBean() {

        perfiles = new SPerfiles();

    }

    @PostConstruct
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

        try {
            SPerfilesJpaController perfilModelo = new SPerfilesJpaController();

            perfiles.setFechaAlta(new Date());
            perfiles.setFechaServidor(new Date());
            perfiles.setActivo(true);

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

}
