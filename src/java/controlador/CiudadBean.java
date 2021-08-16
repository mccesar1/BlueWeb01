
package controlador;

import clases.Ciudad;
import java.io.Serializable;
import java.sql.SQLException;
import respuestas.RespuestaCiudad;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.CiudadModelo;
import clases.Respuesta;
import java.io.IOException;




@ManagedBean(name="ciudad")
@SessionScoped
public class CiudadBean implements Serializable{
    
    private List<Ciudad> listaCiudad= new ArrayList<>();//inicialisa la lista;
    private List<Ciudad> filtroCiudad;
    
   
   private Ciudad ciudad;
   private int id;
   private String descripcion;
   private int lada;
   //private int lada2= Integer.parseInt(lada);
   private String codigo;
   
    @PostConstruct
    
    public void listarCiudad() {
       
        CiudadModelo ciudadmodelo = new CiudadModelo();//crea ciudadmodelo que se conecta con el metodo que esta en ciudad modelo
        
        RespuestaCiudad respuesta = ciudadmodelo.mostrarListaCiudad();
        if(respuesta.getRespuesta().getId()==0){
            String mensaje=respuesta.getRespuesta().getMensaje();
            listaCiudad=respuesta.getListaCiudad();
            
        }
        else if(respuesta.getRespuesta().getId()>0){
            System.out.println("Advertencia");
        }
        else if(respuesta.getRespuesta().getId()<0){
                System.out.println("Error");
    }
    
    }
    //-------------------------------------para agregar------------------------------------------------------------
     public void insertarCiudad() throws IOException{
        // addMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "sdasdasd");
         
         ciudad= new Ciudad(); //creamos un nuevo objeto ciudad     
         ciudad.setDescripcion(descripcion);//le mandamos al nuevo objeto ciudad los valores que tienen las variables
         ciudad.setCodigo(codigo);
         ciudad.setLada(lada);
         
         Respuesta respuesta = CiudadModelo.agregarCiudad(ciudad);//una respuesta que va a llamar la funcion de agregar ciudad
         
         if (respuesta.getId() ==0){
           
             descripcion="";
               codigo="";
               lada=0;
                addMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Ciudad agregada correctamente");
             //redireccionamos a la lista de ciudad
               //FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+ "/faces/ciudad.xhtml");
               
               listarCiudad();
               // addMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Ciudad agregada correctamente");
             System.out.println("fila agregada");
             
         }else if((respuesta.getId()<0)){
             System.out.println("fila no agregada");
             addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ciudad no agregada");
             
         
         }else{
         addMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ciudad no agregada");
         }
         
     
     }
   
     //-----------------------funcion para editar------------------------------------
        public void editarCiudad(int id) throws IOException{
            Ciudad ciudad= new Ciudad();
         ciudad.setId(id);//le mandamos el id de la fila a editar
        ciudad.setDescripcion(descripcion);//le mandamos al nuevo objeto ciudad los valores que tienen las variables
         ciudad.setCodigo(codigo);
         ciudad.setLada(lada);
      Respuesta respuesta = CiudadModelo.editarCiudad(ciudad);//una respuesta que me va a retornar la funcion del modelo
   
      if (respuesta.getId() ==0){
            
            descripcion="";
               codigo="";
               lada=0;
                addMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Ciudad editada");
             listarCiudad();//para que vuelva a listar despues de agregar la nueva fila
             //redireccionamos a la lista de ciudad
              //FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+ "/faces/ciudad.xhtml");
               // addMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Ciudad ditada");
             System.out.println("fila editada");
         }else if (respuesta.getId() < 0){
                System.out.println("Error");
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", " Error Ciudad no editada");
                
            } else {
                
                System.out.println("Ciudad no editada");
                 addMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ciudad no editada");
                 listarCiudad();
            }
      
      
      }
      //
      //
    //-------------------funcion para eliminar----------------------------------------
    public void eliminarCiudad(int id) throws SQLException {//le pasamos el id del index
        
        Ciudad ciudad= new Ciudad();
        ciudad.setId(id);
        Respuesta respuesta= CiudadModelo.elimnarCiudad(ciudad);
        
        try {
            if (respuesta.getId() > 0) {
                System.out.println("error al borrar");
                addMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Ciudad no eliminada");
                
            }else if (respuesta.getId() < 0){
                System.out.println("Error");
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ciudad no eliminada");
                
            } else {
                
                System.out.println("fila borrada");
                 addMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Ciudad eliminada");
                 listarCiudad();
            }
        } catch (Exception ex) {
            Logger.getLogger(CiudadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    
    
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    } 
    
    
    
    
    
    
    //---------------------------------------------------------------------------------

    public List<Ciudad> getListaCiudad() {
        return listaCiudad;
    }

    public List<Ciudad> getFiltroCiudad() {
        return filtroCiudad;
    }

    public void setListaCiudad(List<Ciudad> listaCiudad) {
        this.listaCiudad = listaCiudad;
    }

    public void setFiltroCiudad(List<Ciudad> filtroCiudad) {
        this.filtroCiudad = filtroCiudad;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getLada() {
        return lada;
    }

    public void setLada(int lada) {
        this.lada = lada;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
       this.id = id;
    }



    
    
    
    
    
     
     
}
