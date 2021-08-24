package controlador;

import clases.Ciudad;
import clases.Respuesta;
import controllers.CTelefoniaJpaController;
import entidades.CTelefonia;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import modelo.CiudadModelo;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author César Del Río
 */
@ManagedBean(name = "telefoniaBean")
public class TelefoniaBean {

    private List<CTelefonia> listaTelefonia;//inicialisa la lista;
    private List<CTelefonia> filtroTelefonia;
    private CTelefonia telefonia;
    private long id;
    private String descripcion;
    private String clave;

    public TelefoniaBean() {
        listarTelefonia();
        telefonia = new CTelefonia();
    }

    @PostConstruct

    //-------------------------funcion para mostrar la lista --------------------------------------------------------------
    public void listarTelefonia() {

        try {
            CTelefoniaJpaController telefoniaModelo = new CTelefoniaJpaController();//crea ciudadmodelo que se conecta con el metodo que esta en ciudad modelo
            listaTelefonia = telefoniaModelo.findCTelefoniaEntities();

        } catch (Exception ex) {
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //-------------------------------------para agregar------------------------------------------------------------
    public void insertarTelefonia() throws IOException {

        try {
            CTelefoniaJpaController telefoniaModelo = new CTelefoniaJpaController();
            telefonia.setActivo(Boolean.TRUE);
            telefonia.setFechaServidor(new Date());

            telefoniaModelo.create(telefonia);
            listarTelefonia();
            addMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Telefonia agregada");

        } catch (Exception ex) {
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Telefonia no agregada");
        }
    }

    //-----------------------funcion para editar------------------------------------
    public void editarTelefonia(RowEditEvent event) {
        
        CTelefoniaJpaController telefoniaModelo = new CTelefoniaJpaController();
        CTelefonia telefoniaEdit = (CTelefonia) event.getObject();
        
        if(!descripcion.equals("")){
            telefoniaEdit.setDescripcion(descripcion);
        }
        if(!clave.equals("")){
            telefoniaEdit.setClave(clave);
        }
        try {
            
            telefoniaModelo.edit(telefoniaEdit);
            addMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Telefonia editada");
            listarTelefonia();

        } catch (Exception ex) {
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Telefonia no editada");
        }
            
    }

//    //-------------------funcion para eliminar----------------------------------------
    public void eliminarTelefonia(long id) {

        try {
            CTelefoniaJpaController telefoniaModelo = new CTelefoniaJpaController();

            telefoniaModelo.destroy(id);

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

}
