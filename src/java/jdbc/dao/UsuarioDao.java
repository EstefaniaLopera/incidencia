/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import jdbc.Pojos.RepositorioPojo;
import jdbc.Pojos.UsuarioPojo;
import jdbc.model.MySql;

/**
 *
 * @author vesprada
 */
public class UsuarioDao {
    public UsuarioDao() {
    
    }
    public UsuarioPojo validaUsuario(UsuarioPojo u) {

      
         
        try {
           MySql conn = new MySql();
            conn.conexion();
             String consulta = ("select * from usuario where login ='"+u.getLogin()+
                    "' AND password = '"+ u.getPassword()+"'");
             
           ResultSet result = conn.get(consulta);
            if(result.next()){
                u.setApe1(result.getString("ape1"));
                u.setApe2(result.getString("ape2"));
               u.setEmail(result.getString("email"));
                u.setId(result.getInt("id"));
               u.setNombre(result.getString("nombre"));
                u.setPassword(result.getString("password"));
              u.setTelefono(result.getString("telefono"));
                u.setIdTipoUsuario(result.getInt("id_tipo_usuario"));
               u.setLogin(result.getString("login"));            
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
         return u;    

    }
    
}
