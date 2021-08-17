package modelo;

import clases.Respuesta;
import clases.Usuario;
import data.PoolDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import respuestas.RespuestaUsuario;
import utils.HexDigest;
//funcion para hacer login 
public class LoginModelo {

    public static RespuestaUsuario login(Usuario usu) {
        RespuestaUsuario respuestaUsuario = new RespuestaUsuario();//
        Respuesta claseRespuesta = new Respuesta();
        Usuario usuario = null;
        String query = "";
        PoolDB pool = new PoolDB();
        Connection con = null;
        String passEncriptado;

        try {
            con = pool.getConnection("activa");
            query = ("Select USUARIO, PASSWORD, ID_USUARIO, NOMBRE_USUARIO from S_USUARIOS where USUARIO = ? and PASSWORD = ?");
            PreparedStatement ps = con.prepareStatement(query); // hacer la conexion mandando llamar al query de arriba/preparedstatment/llamar procedimientos almacenados  
            
            passEncriptado= HexDigest.hexDigest(usu.getPassword());
            
            //System.out.println("passsssssssssssssssssssss"+ passEncriptado);
            ps.setString(1, usu.getUsuario());
            ps.setString(2, passEncriptado);

            ResultSet rs = ps.executeQuery();//el rs de tipo resulset se va a usar para traer los datos
            
            usuario = new Usuario();
            if (rs.next()) {
                usuario.setUsuario(rs.getString("USUARIO"));
                usuario.setPassword(rs.getString("PASSWORD"));
                usuario.setIdUsuario(rs.getInt("ID_USUARIO"));
                usuario.setNombreUsuario(rs.getString("NOMBRE_USUARIO"));
              
                claseRespuesta.setId(0);
                claseRespuesta.setMensaje("Exitoso");
            } else {
                claseRespuesta.setId(1);
                claseRespuesta.setMensaje("Login invalido");
            }
            
            rs.close();//cerramos todas las conexiones 
            ps.close();
            con.close();
            
        } catch (SQLException | NamingException se) {
            Logger.getLogger(LoginModelo.class.getName()).log(Level.SEVERE, null, se);
            claseRespuesta.setId(-1);//mandamos los datos al obejto respuesta
            claseRespuesta.setMensaje("Error");
        }

        respuestaUsuario.setRespuesta(claseRespuesta);
        respuestaUsuario.setLogin(usuario);
        return respuestaUsuario;
    }
    
   

}
