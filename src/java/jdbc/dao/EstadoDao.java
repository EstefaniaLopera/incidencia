/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.dao;

import jdbc.Pojos.EstadoPojo;
import jdbc.model.MySql;

/**
 *
 * @author vesprada
 */
public class EstadoDao {
     public EstadoPojo getEditar(EstadoPojo e) throws Exception {
        try {
            MySql.conexion();
           
            e.setNombre((String) MySql.getOne("estado", "nombre", e.getId()));
   
        } catch (Exception er) {
            throw new Exception("ClienteDao.getCliente: Error: "
                    + er.getMessage());
        } finally {
            try {
                MySql.desconexion();
            } catch (Exception er) {
                throw new Exception(
                        "ClienteDao.getCliente: Error en la desconexion: "
                        + er.getMessage());
            }
        }
        return e;
    }
    
    
}
