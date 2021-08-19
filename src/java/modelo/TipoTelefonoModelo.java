
package modelo;



import clases.Respuesta;
import clases.TipoTelefono;
import data.PoolDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import respuestas.RespuestaTipoTelefono;


public class TipoTelefonoModelo {
    
    public RespuestaTipoTelefono MostrarTelefonos(){
    
        RespuestaTipoTelefono respuestaTelefono= new RespuestaTipoTelefono();
        Respuesta claseRespuesta = new Respuesta();
        
          PoolDB pool  = new PoolDB();//creamos el objeto pooldb para conectarse al pooldb
        Connection con = null;//declaras la conexion y la inicias en null 
        List<TipoTelefono> listaTelefono = new ArrayList();//creamos la lista
        String query;//declaramos el query
        
          
        try {
            con = pool.getConnection("activa");//aqui se conecta
            
            query = "SELECT * FROM C_TIPO_TELEFONO";//query para hacer la consulta
            PreparedStatement consulta = con.prepareStatement(query); // hacer la conexion mandando llamar al query de arriba
            ResultSet rs = consulta.executeQuery();//el rs de tipo resulset se va a usar para traer los datos
            
           // System.out.println(rs);//imprime el query
            
            while (rs.next()) {
                TipoTelefono tipoTelefono = new TipoTelefono();
                tipoTelefono.setId(rs.getInt("ID"));
                tipoTelefono.setDescripcion(rs.getString("DESCRIPCION"));
                tipoTelefono.setClave(rs.getString("CLAVE"));
                tipoTelefono.setId_telefonia(rs.getInt("ID_TELEFONIA"));
                
                listaTelefono.add(tipoTelefono);
            }
        rs.close();       
            consulta.close();
            con.close();
            
            claseRespuesta.setId(0);//mandamos los datos al obejto respuesta
            claseRespuesta.setMensaje("exitoso");
            
            respuestaTelefono.setListaTelefono(listaTelefono);//mandamos datos a respuestaciudad
            respuestaTelefono.setRespuesta(claseRespuesta);
            
       } catch (SQLException | NamingException e) {
            System.out.println(e);   
            Logger.getLogger(TipoTelefonoModelo.class.getName()).log(Level.SEVERE, null, e);
        }  
               return respuestaTelefono;//en caso de no poder retornar la lista, debe retornar algo, por eso el return null
       }
    
      //-----------------------------------funcion para borrar --------------------------------------------------------------------------------
       public static Respuesta elimnarTelefono( TipoTelefono tipoTelefono) throws SQLException {    
          
           Respuesta claseRespuesta = new Respuesta();
            
             PoolDB pool  = new PoolDB();//creamos el objeto pooldb para conectarse al pooldb
             Connection con = null;//declaras la conexion y la inicias en null 
              String query;//declaramos el query
        try {
           
            con = pool.getConnection("activa");//aqui se conecta
            query = "DELETE FROM C_TIPO_TELEFONO WHERE ID=?";
             PreparedStatement ps = con.prepareStatement(query);
        
             ps.setInt(1, tipoTelefono.getId());//le envio al query lo que tiene guardado el objeto ciudad
            
             
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
            Logger.getLogger(TipoTelefonoModelo.class.getName()).log(Level.SEVERE, null, ex);
             claseRespuesta.setId(-1);//mandamos los datos al obejto respuesta
                 claseRespuesta.setMensaje("Error");
        }
           return claseRespuesta;
    }
    
    //----------------------------funcion para agregar--------------------------------------------------------------

       public static Respuesta agregarTelefono(TipoTelefono tipoTelefono){
          // RespuestaCiudad respuestaCiudad = new RespuestaCiudad();//esta tiene la lista
           Respuesta claseRespuesta = new Respuesta();
            
             PoolDB pool  = new PoolDB();//creamos el objeto pooldb para conectarse al pooldb
             Connection con = null;//declaras la conexion y la inicias en null 
              String query;//declaramos el query
        try {
            con = pool.getConnection("activa");//aqui se conecta
            
             query = "INSERT INTO C_TIPO_TELEFONO (DESCRIPCION, CLAVE, ID_TELEFONIA) VALUES (?, ?, ?)";
             PreparedStatement ps = con.prepareStatement(query);
             
           
            ps.setString(1, tipoTelefono.getDescripcion());
            ps.setString(2, tipoTelefono.getClave());
            ps.setInt(3, tipoTelefono.getId_telefonia());
            
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
             claseRespuesta.setId(-1);//mandamos los datos al obejto respuesta
                 claseRespuesta.setMensaje("ERROR");
        } catch (NamingException ex) {
            Logger.getLogger(TipoTelefonoModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        return claseRespuesta;
       
       }
       
        //------------------funcion para EDITAR------------------------------------

       public static Respuesta editarTelefono(TipoTelefono tipoTelefono){
             // RespuestaCiudad respuestaCiudad = new RespuestaCiudad();//esta tiene la lista
             Respuesta claseRespuesta = new Respuesta();
            
             PoolDB pool  = new PoolDB();//creamos el objeto pooldb para conectarse al pooldb
             Connection con = null;//declaras la conexion y la inicias en null 
             String query;//declaramos el query
        try {
            con = pool.getConnection("activa");//aqui se conecta
             query = "UPDATE C_TIPO_TELEFONO SET DESCRIPCION=?, CLAVE=?, ID_TELEFONIA=? WHERE ID=?";
             PreparedStatement ps = con.prepareStatement(query);
           
             ps.setString(1, tipoTelefono.getDescripcion());//le envio con los getter de mi bjeto cioudad, lo que tiene el objeto que se lleno con los set del bean
             ps.setString(2, tipoTelefono.getClave());
             ps.setInt(3, tipoTelefono.getId_telefonia());
             ps.setInt(4, tipoTelefono.getId());
            
             
             int result=ps.executeUpdate();
             
              
            ps.close();
            con.close();
            
            if(result !=0) {//si funciono el update es exitoso
                     claseRespuesta.setId(0);//mandamos los datos al obejto respuesta
                 claseRespuesta.setMensaje("exitoso");
            
            }else{
                 claseRespuesta.setId(1);//mandamos los datos al obejto respuesta
                 claseRespuesta.setMensaje("advertencia");
            
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CiudadModelo.class.getName()).log(Level.SEVERE, null, ex);
             claseRespuesta.setId(1);//mandamos los datos al obejto respuesta
                 claseRespuesta.setMensaje("advertencia");
        } catch (NamingException ex) {
            Logger.getLogger(CiudadModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
        return claseRespuesta;
           
       }
    }

