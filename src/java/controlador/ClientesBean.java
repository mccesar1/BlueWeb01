package controlador;

import clases.Clientes;
import respuestas.RespuestaCliente;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import modelo.ClienteModelo;

@ViewScoped
@ManagedBean(name = "cliente")
public class ClientesBean {

    private List<Clientes> listaClientes= new ArrayList<>();;
    private List<Clientes> filtroClientes;// = new ArrayList<>();

    @PostConstruct
    public void listarClientes() {

        ClienteModelo clientemodelo = new ClienteModelo();//crea ciudadmodelo que se conecta con el metodo que esta en ciudad modelo
        RespuestaCliente respuestaCliente = clientemodelo.MostrarClientes();
        
        if(respuestaCliente.getRespuesta().getId()==0){
        listaClientes = respuestaCliente.getListaClientes();//se le asigana a la lista ciudad lo que trae de ciudadmodelo
        }
        else if(respuestaCliente.getRespuesta().getId()<0){
            System.out.println("Advertencia");
        }
        else if(respuestaCliente.getRespuesta().getId()>0){
                System.out.println("Error");
    }
    
    }

   

    public List<Clientes> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Clientes> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Clientes> getFiltroClientes() {
        return filtroClientes;
    }

    public void setFiltroClientes(List<Clientes> filtroClientes) {
        this.filtroClientes = filtroClientes;
    }

    
    

}
