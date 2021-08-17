
package controlador;


import clases.Usuario;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import modelo.LoginModelo;
import respuestas.RespuestaUsuario;
import javax.faces.context.FacesContext;

@RequestScoped
@SessionScoped
@ManagedBean(name = "login")

public class LoginBean {
    
  private Usuario usuario;
   private String user, pass;//variables que se llenan con el html de login 
   
      

      public void login() throws IOException {
          
         
          usuario= new Usuario();
          usuario.setUsuario(user); //mandamos los datos al objeto usuario
          usuario.setPassword(pass);
          
         // LoginModelo login = new LoginModelo();
          RespuestaUsuario respuesta = LoginModelo.login(usuario);//le mandamos el parametro de login al metodo login 
                      
          //login.login(usuario);
           if (respuesta.getRespuesta().getId() == 0) {
               
               System.out.println("conectado");  
             
               
               //almacenamos la sesion en las variables de sesion                                                                 //esto o usuario??
             FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("IdUsuario", respuesta.getLogin().getIdUsuario());//metemos los datos a la variable de sesion
              FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", respuesta.getLogin().getUsuario());//metemos los datos a la variable de sesion
              FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Nombre", respuesta.getLogin().getNombreUsuario());
            //context.getExternalContext().redirect("/faces/index.xhtml");
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+ "/faces/catalogos/index.xhtml");
            addMessage(FacesMessage.SEVERITY_INFO, "BIENVENIDO", "");
           }else if(respuesta.getRespuesta().getId() > 0){
               System.out.println("Advertencia");
               addMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Usuario y/o contraseña incorrectos");
           }else if(respuesta.getRespuesta().getId()<0){
                System.out.println("Error");
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se produjo un error");
    }
          
          
}
      //metodo para logOut
       public void logOut() throws IOException {
    
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        
         FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+ "/faces/login.xhtml");
            
    
    }
       //metodo para los mensajes
        public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    } 

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

 


}