package modelo;

import data.PoolDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import clases.Ciudad;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import clases.Respuesta;
import clases.Usuario;
import respuestas.RespuestaCiudad;
import sesiones.Sesion;

public class CiudadModelo {

    public CiudadModelo() {
    }

    public Ciudad listarCiudad(Ciudad ciudad) {  //este es el metodo listar ciudad sin respuesta, no se usa por el momento 

        PoolDB pool = new PoolDB();//creamos el objeto pooldb para conectarse al pooldb
        Connection con = null;//declaras la conexion y la inicias en null 

        try {
            con = pool.getConnection("activa");//aqui se conecta 
      
            PreparedStatement ps = con.prepareStatement("SELECT * FROM C_CIUDAD WHERE ID=?"); // hacer la conexion mandando llamar al query de arriba/preparedstatment/llamar procedimientos almacenados  

            ps.setInt(1, ciudad.getId());

            ResultSet rs = ps.executeQuery();//el rs de tipo resulset se va a usar para traer los datos
            while (rs.next()) {
                ciudad = new Ciudad();//declaramos el objeto ciudad
                ciudad.setId(rs.getInt("ID_CIUDAD"));
                ciudad.setDescripcion(rs.getString("DESCRIPCION"));
                ciudad.setCodigo(rs.getString("CODIGO"));
                ciudad.setLada(rs.getInt("LADA"));

            }
            rs.close();
            ps.close();
            con.close();
            //return lista;//regresamos la lista, para esto el metodo no puede ser void 

        } catch (SQLException e) {
            System.out.println(e);
        } catch (NamingException ex) {
            Logger.getLogger(CiudadModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ciudad;//en caso de no poder retornar la lista, debe retornar algo, por eso el return null        
    }

    //------------------------------Mostrar Lista--------------------------------------------------------------------------------   
    public RespuestaCiudad mostrarListaCiudad() {
        RespuestaCiudad respuestaCiudad = new RespuestaCiudad();
        Respuesta claseRespuesta = new Respuesta();
        List<Ciudad> lista = new ArrayList();//creas la lista

        PoolDB pool = new PoolDB();//creamos el objeto pooldb para conectarse al pooldb
        Connection con = null;//declaras la conexion y la inicias en null 

        try {
            con = pool.getConnection("activa");//aqui se conecta 

           
            PreparedStatement consulta = con.prepareStatement("SELECT ID_CIUDAD, DESCRIPCION, CODIGO, LADA FROM C_CIUDAD WITH (NOLOCK)"); // hacer la conexion mandando llamar al query de arriba/preparedstatment/llamar procedimientos almacenados  
            ResultSet rs = consulta.executeQuery();//el rs de tipo resulset se va a usar para traer los datos

            while (rs.next()) {
                Ciudad ciudad = new Ciudad();//declaramos el objeto ciudad
                ciudad.setId(rs.getInt("ID_CIUDAD"));
                ciudad.setDescripcion(rs.getString("DESCRIPCION"));
                ciudad.setCodigo(rs.getString("CODIGO"));
                ciudad.setLada(rs.getInt("LADA"));

                lista.add(ciudad);//llenamos la lista con los datos que le mandamos al objeto ciudad

            }

            rs.close();//cerramos todas las conexiones
            consulta.close();
            con.close();

            if (!lista.isEmpty()) {
                claseRespuesta.setId(0);//mandamos los datos al obejto respuesta
                claseRespuesta.setMensaje("exitoso");

                respuestaCiudad.setListaCiudad(lista);
                respuestaCiudad.setRespuesta(claseRespuesta);

            } else {
                claseRespuesta.setId(1);//mandamos los datos al obejto respuesta
                claseRespuesta.setMensaje("Advertencia");
            }
            //return lista;//regresamos la lista, para esto el metodo no puede ser void 

        } catch (SQLException | NamingException e) {
            System.out.println(e);
            claseRespuesta.setId(-1);//mandamos los datos al obejto respuesta
            claseRespuesta.setMensaje("Error");
            Logger.getLogger(CiudadModelo.class.getName()).log(Level.SEVERE, null, e);

        }
        return respuestaCiudad;//en caso de no poder retornar la lista, debe retornar algo, por eso el return null
    }
//-----------------------------------funcion para borrar --------------------------------------------------------------------------------

    public static Respuesta elimnarCiudad(Ciudad ciudad) throws SQLException {

        Respuesta claseRespuesta = new Respuesta();

        PoolDB pool = new PoolDB();//creamos el objeto pooldb para conectarse al pooldb
        Connection con = null;//declaras la conexion y la inicias en null 
        
        try {

            con = pool.getConnection("activa");//aqui se conecta       
            PreparedStatement ps = con.prepareStatement("DELETE FROM C_CIUDAD WHERE ID_CIUDAD=?");
            ps.setInt(1, ciudad.getId());//le envio al query lo que tiene guardado el objeto ciudad

            int result = ps.executeUpdate();

            ps.close();
            con.close();

            if (result != 0) {//si funciono el update es exitoso
                claseRespuesta.setId(0);//mandamos los datos al obejto respuesta
                claseRespuesta.setMensaje("exitoso");

            } else {
                claseRespuesta.setId(1);//mandamos los datos al obejto respuesta
                claseRespuesta.setMensaje("advertencia");

            }

        } catch (NamingException ex ) {
            Logger.getLogger(CiudadModelo.class.getName()).log(Level.SEVERE, null, ex);
            claseRespuesta.setId(-1);//mandamos los datos al obejto respuesta
            claseRespuesta.setMensaje("Error");
        }
          catch (SQLException ex) {
             Logger.getLogger(CiudadModelo.class.getName()).log(Level.SEVERE, null, ex);
             claseRespuesta.setId(-1);//mandamos los datos al obejto respuesta
            claseRespuesta.setMensaje("Error");
            }
        return claseRespuesta;
    }
//----------------------------funcion para agregar--------------------------------------------------------------

    public static Respuesta agregarCiudad(Ciudad ciudad) {
        // RespuestaCiudad respuestaCiudad = new RespuestaCiudad();//esta tiene la lista
        //Usuario usuario= new Usuario();
        Respuesta claseRespuesta = new Respuesta();

        PoolDB pool = new PoolDB();//creamos el objeto pooldb para conectarse al pooldb
        Connection con = null;//declaras la conexion y la inicias en null
        try {
            con = pool.getConnection("activa");//aqui se conecta

            PreparedStatement ps = con.prepareStatement("INSERT INTO C_CIUDAD (DESCRIPCION, CODIGO, LADA, ID_USUARIO) VALUES (?, ?, ?, ?)");

            ps.setString(1, ciudad.getDescripcion());
            ps.setString(2, ciudad.getCodigo());
            ps.setInt(3, ciudad.getLada());
            ps.setInt(4, Sesion.sesionId());

            int result = ps.executeUpdate();

            ps.close();
            con.close();

            if (result != 0) {//si funciono el update es exitoso -- cambiar a >0
                claseRespuesta.setId(0);//mandamos los datos al obejto respuesta
                claseRespuesta.setMensaje("exitoso");

            } else {
                claseRespuesta.setId(1);//mandamos los datos al obejto respuesta
                claseRespuesta.setMensaje("advertencia");

            }
        } catch (SQLException ex) {
            Logger.getLogger(CiudadModelo.class.getName()).log(Level.SEVERE, null, ex);

            claseRespuesta.setId(-11);//mandamos los datos al obejto respuesta
            claseRespuesta.setMensaje("Error");
        } catch (NamingException ex) {
            Logger.getLogger(CiudadModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return claseRespuesta;

    }

    //------------------funcion para EDITAR------------------------------------
    public static Respuesta editarCiudad(Ciudad ciudad) {
        // RespuestaCiudad respuestaCiudad = new RespuestaCiudad();//esta tiene la lista
        Respuesta claseRespuesta = new Respuesta();

        PoolDB pool = new PoolDB();//creamos el objeto pooldb para conectarse al pooldb
        Connection con = null;//declaras la conexion y la inicias en null 

        try {
            con = pool.getConnection("activa");//aqui se conecta
            PreparedStatement ps = con.prepareStatement("UPDATE C_CIUDAD SET DESCRIPCION=?, CODIGO=?, LADA=?, ID_USUARIO=? WHERE ID_CIUDAD=?");

            ps.setString(1, ciudad.getDescripcion());//le envio con los getter de mi bjeto cioudad, lo que tiene el objeto que se lleno con los set del bean
            ps.setString(2, ciudad.getCodigo());
            ps.setInt(3, ciudad.getLada());
            ps.setInt(4, Sesion.sesionId());
            ps.setInt(5, ciudad.getId());

            int result = ps.executeUpdate();

            ps.close();
            con.close();

            if (result != 0) {//si funciono el update es exitoso
                claseRespuesta.setId(0);//mandamos los datos al obejto respuesta
                claseRespuesta.setMensaje("exitoso");

            } else {
                claseRespuesta.setId(1);//mandamos los datos al obejto respuesta
                claseRespuesta.setMensaje("advertencia");

            }

        } catch (SQLException | NamingException ex) {
            Logger.getLogger(CiudadModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return claseRespuesta;

    }
}
