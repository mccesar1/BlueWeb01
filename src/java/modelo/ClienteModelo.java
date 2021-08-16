
package modelo;

import clases.Ciudad;
import clases.Clientes;
import respuestas.RespuestaCliente;
import data.PoolDB;
import clases.Respuesta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

//@ManagedBean(name="cliente")//es como lo va a mandar llamar el index
public class ClienteModelo {
      
     public List<Clientes> listarClientes(){
        PoolDB pool  = new PoolDB();//creamos el objeto pooldb para conectarse al pooldb
        Connection con = null;//declaras la conexion y la inicias en null 
        List<Clientes> listaCliente = new ArrayList();
        String query;//declaramos el query
        
        try {
            con = pool.getConnection("activa");//aqui se conecta
            
            query = "SELECT * FROM C_CLIENTES";//query para hacer la consulta
            PreparedStatement consulta = con.prepareStatement(query); // hacer la conexion mandando llamar al query de arriba
            ResultSet rs = consulta.executeQuery();//el rs de tipo resulset se va a usar para traer los datos
            
           // System.out.println(rs);//imprime el query
            
            while (rs.next()) {
              Clientes clientes = new Clientes();//declaramos el objeto ciudad
              clientes.setId(rs.getInt("ID_CLIENTE"));
              clientes.setNumero(rs.getString("NUM_CLIENTE"));
              clientes.setNombre(rs.getString("NOMBRE_CLIENTE"));
              
              
              listaCliente.add(clientes);//llenamos la lista con los datos que le mandamos al objeto ciudad
              
            }
            //cerrar conexiones
            
            rs.close();       
            consulta.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCliente;
     
     }

//-------------------------------------------------------------------------------------------------------
     public RespuestaCliente MostrarClientes() {

         RespuestaCliente respuestaCliente = new RespuestaCliente();
         Respuesta claseRespuesta = new Respuesta();
         
         PoolDB pool  = new PoolDB();//creamos el objeto pooldb para conectarse al pooldb
        Connection con = null;//declaras la conexion y la inicias en null 
        List<Clientes> listaCliente = new ArrayList();//creamos la lista
        String query;//declaramos el query
        
        try {
            con = pool.getConnection("activa");//aqui se conecta
            
            query = "SELECT * FROM C_CLIENTES";//query para hacer la consulta
            PreparedStatement consulta = con.prepareStatement(query); // hacer la conexion mandando llamar al query de arriba
            ResultSet rs = consulta.executeQuery();//el rs de tipo resulset se va a usar para traer los datos
            
           // System.out.println(rs);//imprime el query
            
            while (rs.next()) {
              Clientes clientes = new Clientes();//declaramos el objeto ciudad
              clientes.setId(rs.getInt("ID_CLIENTE"));
              clientes.setNumero(rs.getString("NUM_CLIENTE"));
              clientes.setNombre(rs.getString("NOMBRE_CLIENTE"));
              
              listaCliente.add(clientes);//llenamos la lista con los datos que le mandamos al objeto ciudad      
            }
            //cerrar conexiones
            
            rs.close();       
            consulta.close();
            con.close();
            
            claseRespuesta.setId(0);//mandamos los datos al obejto respuesta
            claseRespuesta.setMensaje("exitoso");
            
            respuestaCliente.setListaClientes(listaCliente);//mandamos datos a respuestaciudad
            respuestaCliente.setRespuesta(claseRespuesta);
            
       } catch (SQLException | NamingException e) {
            System.out.println(e);  
            Logger.getLogger(ClienteModelo.class.getName()).log(Level.SEVERE, null, e);            
        }  
               return respuestaCliente;//en caso de no poder retornar la lista, debe retornar algo, por eso el return null
       }
     //-----------------------------------funcion para borrar --------------------------------------------------------------------------------
       public static Respuesta elimnarCiudad(Clientes clientes) throws SQLException {    
          
           Respuesta claseRespuesta = new Respuesta();
            
             PoolDB pool  = new PoolDB();//creamos el objeto pooldb para conectarse al pooldb
             Connection con = null;//declaras la conexion y la inicias en null 
              String query;//declaramos el query
        try {
           
            con = pool.getConnection("activa");//aqui se conecta
            query = "DELETE FROM C_CLIENTES WHERE ID_CLIENTE=?";
             PreparedStatement ps = con.prepareStatement(query);
        
             ps.setInt(1, clientes.getId());//le envio al query lo que tiene guardado el objeto ciudad
            
             
             int result = ps.executeUpdate();
             
              ps.close();
              con.close();
              
              if(result !=0) {//si funciono el update es exitoso
                     claseRespuesta.setId(0);//mandamos los datos al obejto respuesta
                 claseRespuesta.setMensaje("exitoso");
            
            }else{
                 claseRespuesta.setId(1);//mandamos los datos al obejto respuesta
                 claseRespuesta.setMensaje("advertencia");
            
            }
            
           
        } catch (NamingException ex) {
            Logger.getLogger(CiudadModelo.class.getName()).log(Level.SEVERE, null, ex);
             claseRespuesta.setId(-1);//mandamos los datos al obejto respuesta
                 claseRespuesta.setMensaje("Error");
        }
           return claseRespuesta;
    }
     
       //----------------------------funcion para agregar--------------------------------------------------------------

       public static Respuesta agregarCliente(Clientes clientes){
          // RespuestaCiudad respuestaCiudad = new RespuestaCiudad();//esta tiene la lista
           Respuesta claseRespuesta = new Respuesta();
            
             PoolDB pool  = new PoolDB();//creamos el objeto pooldb para conectarse al pooldb
             Connection con = null;//declaras la conexion y la inicias en null 
              String query;//declaramos el query
        try {
            con = pool.getConnection("activa");//aqui se conecta
            
             query = "INSERT INTO C_CLIENTES (NUM_CLIENTE , NOMBRE_CLIENTE) VALUES (?, ?)";
             PreparedStatement ps = con.prepareStatement(query);
             
           
            ps.setString(1, clientes.getNumero());
            ps.setString(2, clientes.getNombre());
           
            
            int result = ps.executeUpdate();
            
            ps.close();
            con.close();
            
            if(result !=0) {//si funciono el update es exitoso -- cambiar a >0
                     claseRespuesta.setId(0);//mandamos los datos al obejto respuesta
                 claseRespuesta.setMensaje("exitoso");
            
            }else{
                 claseRespuesta.setId(1);//mandamos los datos al obejto respuesta
                 claseRespuesta.setMensaje("advertencia");
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(CiudadModelo.class.getName()).log(Level.SEVERE, null, ex);
            //mandar el -1
        } catch (NamingException ex) {
            Logger.getLogger(CiudadModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        return claseRespuesta;
       
       }

}



    
     
