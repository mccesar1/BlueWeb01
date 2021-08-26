package controlador;

import controllers.CTelefoniaJpaController;
import controllers.CTipoTelefonoJpaController;
import entidades.CTelefonia;
import entidades.CTipoTelefono;
import java.io.IOException;
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
 * @author César Del Río
 */
@ManagedBean(name = "telefoniaBean")
public class TelefoniaBean {

    private List<CTelefonia> listaTelefonia;//inicialisa la lista;
    private List<CTipoTelefono> listaTipoTelefono;//
    private List<CTelefonia> filtroTelefonia;
    private List<CTelefonia> combo;
    private CTelefonia telefonia;
    private CTipoTelefono tipotelefono;
    private long id;
    private String descripcion;
    private String clave;
    private long idTelefonia;

    public TelefoniaBean() {

        telefonia = new CTelefonia();
        tipotelefono = new CTipoTelefono();
    }

    @PostConstruct

    //-------------------------funcion para mostrar la lista --------------------------------------------------------------
    public void listarTelefonia() {

        try {
            CTelefoniaJpaController telefoniaModelo = new CTelefoniaJpaController();//creamos un nuevo modelo de telefonia
            CTipoTelefonoJpaController tipoTelefonoModelo = new CTipoTelefonoJpaController(); //creamos un nuevo modelo de tipo telefono
            listaTipoTelefono = tipoTelefonoModelo.findCTipoTelefonoEntities(); //lista para llenar los datos 
            
            combo = telefoniaModelo.findCTelefoniaEntities();//lista para los datos que se van a mostrar en el combo 

        } catch (Exception ex) {
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //-------------------------------------para agregar------------------------------------------------------------
    public void insertarTelefonia() throws IOException {

        try {

            CTelefoniaJpaController telefoniaModelo = new CTelefoniaJpaController();
            CTipoTelefonoJpaController tipoTelefonoModelo = new CTipoTelefonoJpaController();

            CTelefonia objTelefonia = telefoniaModelo.findCTelefonia(idTelefonia);
            tipotelefono.setIdTelefonia(objTelefonia);
            tipotelefono.setActivo(true);
            tipotelefono.setFechaServidor(new Date());

            tipoTelefonoModelo.create(tipotelefono);
            listarTelefonia();
            addMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Telefonia agregada");

        } catch (Exception ex) {
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Telefonia no agregada");
        }
    }

    //-----------------------funcion para editar------------------------------------
    public void editarTelefonia(RowEditEvent event) {

        // CTelefoniaJpaController telefoniaModelo = new CTelefoniaJpaController();
        CTipoTelefonoJpaController tipoTelefonoModelo = new CTipoTelefonoJpaController();

        //CTelefonia telefoniaEdit = (CTelefonia) event.getObject();
        CTipoTelefono telefoniaEdit = (CTipoTelefono) event.getObject();
        CTelefoniaJpaController telefoniaModelo = new CTelefoniaJpaController();

        if (!descripcion.equals("")) {
            telefoniaEdit.setDescripcion(descripcion);
        }
        if (!clave.equals("")) {
            telefoniaEdit.setClave(clave);

        }
        if (idTelefonia > 0) {
            CTelefonia objTelefonia = telefoniaModelo.findCTelefonia(idTelefonia);
            telefoniaEdit.setIdTelefonia(objTelefonia);
        }

        try {

            tipoTelefonoModelo.edit(telefoniaEdit);
            addMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Telefonia editada");
            listarTelefonia();

        } catch (Exception ex) {
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Telefonia no editada");
        }
        descripcion = "";
        clave = "";
        idTelefonia = 0;

    }

//    //-------------------funcion para eliminar----------------------------------------
    public void eliminarTelefonia(long id) {

        try {
            // CTelefoniaJpaController telefoniaModelo = new CTelefoniaJpaController();
            CTipoTelefonoJpaController tipoTelefonoModelo = new CTipoTelefonoJpaController();

            tipoTelefonoModelo.destroy(id);
            //telefoniaModelo.destroy(id);

            listarTelefonia();
            addMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Telefonia eliminada");

        } catch (Exception ex) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Telefonia no eliminada");
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    //---------------------------------------------------------------------------------
//}
//<editor-fold defaultstate="collapsed" desc="Getters y Setters">
//<editor-fold defaultstate="collapsed" desc="Getters y Settters">
    public List<CTelefonia> getListaTelefonia() {
        return listaTelefonia;
    }
    
    public void setListaTelefonia(List<CTelefonia> listaTelefonia) {
        this.listaTelefonia = listaTelefonia;
    }
    
    public List<CTelefonia> getFiltroTelefonia() {
        return filtroTelefonia;
    }
    
    public void setFiltroTelefonia(List<CTelefonia> filtroTelefonia) {
        this.filtroTelefonia = filtroTelefonia;
    }
    
    public CTelefonia getTelefonia() {
        return telefonia;
    }
    
    public void setTelefonia(CTelefonia telefonia) {
        this.telefonia = telefonia;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getClave() {
        return clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
    
    public CTipoTelefono getTipotelefono() {
        return tipotelefono;
    }
    
    public void setTipotelefono(CTipoTelefono tipotelefono) {
        this.tipotelefono = tipotelefono;
    }
    
    public long getIdTelefonia() {
        return idTelefonia;
    }
    
    public void setIdTelefonia(long idTelefonia) {
        this.idTelefonia = idTelefonia;
    }
    
    public List<CTelefonia> getCombo() {
        return combo;
    }
    
    public void setCombo(List<CTelefonia> combo) {
        this.combo = combo;
    }
    
//</editor-fold>
    public List<CTipoTelefono> getListaTipoTelefono() {
        return listaTipoTelefono;
    }
    
    public void setListaTipoTelefono(List<CTipoTelefono> listaTipoTelefono) {
        this.listaTipoTelefono = listaTipoTelefono;
    }
//</editor-fold>

}
