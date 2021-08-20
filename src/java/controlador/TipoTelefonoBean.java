package controlador;

import clases.Respuesta;
import clases.TipoTelefono;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import modelo.TipoTelefonoModelo;
import org.primefaces.event.RowEditEvent;
import respuestas.RespuestaTipoTelefono;


@ManagedBean(name = "telefonoBean")
public class TipoTelefonoBean {

    private List<TipoTelefono> listaTelefono = new ArrayList<>();
    ;
    private List<TipoTelefono> filtroTelefono;// = new ArrayList<>();

    private TipoTelefono tipoTelefono;
    private String clave;
    private String descripcion;
    private String idTelefonia;
    private int id;

    @PostConstruct
    public void listarTelefonos() {

        TipoTelefonoModelo telefonoModelo = new TipoTelefonoModelo();//crea ciudadmodelo que se conecta con el metodo que esta en ciudad modelo
        RespuestaTipoTelefono respuestaTelefono = telefonoModelo.MostrarTelefonos();

        if (respuestaTelefono.getRespuesta().getId() == 0) {
            listaTelefono = respuestaTelefono.getListaTelefono();//se le asigana a la lista ciudad lo que trae de ciudadmodelo
        } else if (respuestaTelefono.getRespuesta().getId() < 0) {
            System.out.println("Advertencia");
        } else if (respuestaTelefono.getRespuesta().getId() > 0) {
            System.out.println("Error");
        }
    }
    //-------------------funcion para eliminar----------------------------------------

    public void eliminarTelefono(int id) throws SQLException {//le pasamos el id del index

        TipoTelefono telefono = new TipoTelefono();
        telefono.setId(id);
        Respuesta respuesta = TipoTelefonoModelo.elimnarTelefono(telefono);

        try {
            if (respuesta.getId() > 0) {
                System.out.println("error al borrar");
                addMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Telefono no eliminado");

            } else if (respuesta.getId() < 0) {
                System.out.println("Error");
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Telefono no eliminado");

            } else {

                System.out.println("fila borrada");
                addMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Telefono eliminado");
                listarTelefonos();
            }
        } catch (Exception ex) {
            Logger.getLogger(TipoTelefono.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //-------------------------------------para agregar------------------------------------------------------------
    public void insertarTelefono() throws IOException {
        // addMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "sdasdasd");

        tipoTelefono = new TipoTelefono(); //creamos un nuevo objeto telefono    
        tipoTelefono.setDescripcion(descripcion);//le mandamos al nuevo objeto ciudad los valores que tienen las variables
        tipoTelefono.setClave(clave);
        tipoTelefono.setId_telefonia(Integer.parseInt(idTelefonia));

        Respuesta respuesta = TipoTelefonoModelo.agregarTelefono(tipoTelefono);

        if (respuesta.getId() == 0) {
            listarTelefonos();//para que vuelva a listar despues de agregar la nueva fila
            addMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Telefono agregado");
            //redireccionamos a la lista de ciudad
            //FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+ "/faces/ciudad.xhtml");
            descripcion = "";
            clave = "";
            idTelefonia = "";

            // addMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Ciudad agregada correctamente");
            System.out.println("fila agregada");

        } else if ((respuesta.getId() < 0)) {
            System.out.println("fila no agregada");
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Telefono no agregado");

        } else {
            addMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "telefono no agregado");
        }

    }
    //
    //-----------------------funcion para editar------------------------------------

    public void editarTelefono(RowEditEvent event) throws IOException {

        TipoTelefono telefonoEdit = (TipoTelefono) event.getObject();

        if (!descripcion.equals("")) {
            telefonoEdit.setDescripcion(descripcion);
        }
        if (!idTelefonia.equals("")) {
            telefonoEdit.setId_telefonia(Integer.parseInt(idTelefonia));
        }
        if (!clave.equals("")) {
            telefonoEdit.setClave(clave);
        }
       // TipoTelefono telefono = new TipoTelefono();
       // telefono.setId(id);//le mandamos el id de la fila a editar

        Respuesta respuesta = TipoTelefonoModelo.editarTelefono(telefonoEdit);

        if (respuesta.getId() == 0) {

            descripcion = "";
            clave = "";
            idTelefonia = "";
            addMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Telefono editado");
            listarTelefonos();//para que vuelva a listar despues de agregar la nueva fila

            System.out.println("fila editada");
        } else if (respuesta.getId() < 0) {
            System.out.println("Error");
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Telefono no editado");

        } else {

            System.out.println("fila borrada");
            addMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Telefono no editado");
            listarTelefonos();
        }

    }
    //

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

//<editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public List<TipoTelefono> getListaTelefono() {
        return listaTelefono;
    }
    
    public void setListaTelefono(List<TipoTelefono> listaTelefono) {
        this.listaTelefono = listaTelefono;
    }
    
    public List<TipoTelefono> getFiltroTelefono() {
        return filtroTelefono;
    }
    
    public void setFiltroTelefono(List<TipoTelefono> filtroTelefono) {
        this.filtroTelefono = filtroTelefono;
    }
    
    public TipoTelefono getTipoTelefono() {
        return tipoTelefono;
    }
    
    public void setTipoTelefono(TipoTelefono tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }
    
    public String getClave() {
        return clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getIdTelefonia() {
        return idTelefonia;
    }
    
    public void setIdTelefonia(String idTelefonia) {
        this.idTelefonia = idTelefonia;
    }
    
    
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
//</editor-fold>

}
