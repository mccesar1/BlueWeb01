
package controlador;


import controllers.SAccesosJpaController;
import entidades.SAccesos;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;


@ManagedBean(name="accesosBean")
public class AccesosBean {
    
    
    private List<SAccesos> listaAccesos;//inicialisa la lista;
    private List<SAccesos> filtroAccesos;
    private SAccesos acceso;
    private String nombreAcceso;
    private short orden;
    
    
    public AccesosBean() {

        acceso = new SAccesos();
        
    }
     @PostConstruct

    //-------------------------funcion para mostrar la lista --------------------------------------------------------------
    public void listarAccesos() {

        try {
            SAccesosJpaController accesoModelo = new SAccesosJpaController();//creamos un nuevo modelo de telefonia
            
            listaAccesos = accesoModelo.findSAccesosEntities();
            


        } catch (Exception ex) {
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     //-------------------------------------para agregar------------------------------------------------------------
    public void insertarAcceso() {

        try {
               SAccesosJpaController accesoModelo = new SAccesosJpaController();
  
            acceso.setFechaServidor(new Date());
            acceso.setActivo(true);
            
            

            accesoModelo.create(acceso);
            listarAccesos();
            addMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Acceso Agregado");

        } catch (Exception ex) {
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Acceso no agregado");
        }
    }
    
     //-------------------funcion para eliminar----------------------------------------
    public void eliminarAcceso(int id) {

        try {
           
              SAccesosJpaController accesoModelo = new SAccesosJpaController();

            accesoModelo.destroy(id);
           

            listarAccesos();
            addMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Acceso eliminado");

        } catch (Exception ex) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Acceso no eliminado");
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     //-----------------------funcion para editar------------------------------------
    public void editarAcceso(RowEditEvent event) {

       
         SAccesosJpaController accesoModelo = new SAccesosJpaController();
       SAccesos accesoEdit = (SAccesos) event.getObject();
      
        if (!nombreAcceso.equals("")) {
            accesoEdit.setNombreAcceso(nombreAcceso);
        }
        
        if (orden > 0) {
            
            accesoEdit.setOrden(orden);
        }

        try {

            accesoModelo.edit(accesoEdit);
            addMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Acceso editado");
            listarAccesos();

        } catch (Exception ex) {
            Logger.getLogger(TelefoniaBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Acceso no editado");
        }
        nombreAcceso = "";
        orden = 0;

    }

    
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
    
    public List<SAccesos> getListaAccesos() {
        return listaAccesos;
    }

    public void setListaAccesos(List<SAccesos> listaAccesos) {
        this.listaAccesos = listaAccesos;
    }

    public List<SAccesos> getFiltroAccesos() {
        return filtroAccesos;
    }

    public void setFiltroAccesos(List<SAccesos> filtroAccesos) {
        this.filtroAccesos = filtroAccesos;
    }

    public SAccesos getAcceso() {
        return acceso;
    }

    public void setAcceso(SAccesos acceso) {
        this.acceso = acceso;
    }

    public String getNombreAcceso() {
        return nombreAcceso;
    }

    public void setNombreAcceso(String nombreAcceso) {
        this.nombreAcceso = nombreAcceso;
    }

    public short getOrden() {
        return orden;
    }

    public void setOrden(short orden) {
        this.orden = orden;
    }


    
    
    
    }
    
    
    
    

