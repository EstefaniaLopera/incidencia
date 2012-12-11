/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import jdbc.Pojos.IncidenciaPojo;
import javax.servlet.http.HttpServletResponse;
import jdbc.Pojos.EstadoPojo;
import jdbc.Pojos.UsuarioPojo;
import jdbc.model.MySql;
import jdbc.servlet.Login;

/**
 *
 * @author vesprada
 */
public class IncidenciaDao {
    public IncidenciaDao() {
    
    }


    public static LinkedList<IncidenciaPojo> getIncidenciaDao(UsuarioPojo usuarioPojo) {

        LinkedList<IncidenciaPojo> listaInc = new LinkedList<>();
        Connection conn = null;
       

        try {
            MySql.conexion();



             String sql = " SELECT ID, RESUMEN, CAMBIOS, ID_ESTADO, ID_REPOSITORIO,ID_USUARIO, "
                    + " FECHA_ALTA, FECHA_RESOLUCION FROM INCIDENCIA WHERE ID_USUARIO = '" + usuarioPojo.getId() + "' ";

 
            ResultSet rs = MySql.get(sql);


            while (rs.next()) {

                 IncidenciaPojo incidencia = new IncidenciaPojo();
                incidencia.setId(rs.getInt("ID"));
                incidencia.setResumen(rs.getString("RESUMEN"));
                incidencia.setCambios(rs.getString("CAMBIOS"));
                incidencia.setIdEstado(rs.getInt("ID_ESTADO"));
                incidencia.setIdRepositorio(rs.getInt("ID_REPOSITORIO"));
                incidencia.setIdUsuario(rs.getInt("ID_USUARIO"));
                incidencia.setFechaAlta(rs.getDate("FECHA_ALTA"));
                incidencia.setFechaResolucion(rs.getDate("FECHA_RESOLUCION"));

                listaInc.add(incidencia);

            }

            MySql.desconexion();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaInc;

    }

    public void nuevo(IncidenciaPojo incidenciaPojo) throws Exception {

        try {
            MySql.conexion();
            MySql.initTrans();
            if (incidenciaPojo.getId() == 0) {
                incidenciaPojo.setId(MySql.insertOne("incidencia"));
            }

            MySql.updateOne(incidenciaPojo.getId(), "incidencia", "resumen", incidenciaPojo.getResumen());
            MySql.updateOne(incidenciaPojo.getId(), "incidencia", "cambios", incidenciaPojo.getCambios());
            MySql.updateOne(incidenciaPojo.getId(), "incidencia", "id_estado",Integer.toString(incidenciaPojo.getIdEstado()));
            MySql.updateOne(incidenciaPojo.getId(), "incidencia", "id_repositorio", "1");
            MySql.updateOne(incidenciaPojo.getId(), "incidencia", "id_usuario",Integer.toString(incidenciaPojo.getIdUsuario()));
            MySql.updateOne(incidenciaPojo.getId(), "incidencia", "fecha_alta", dateToMySQLDate(incidenciaPojo.getFechaAlta()));
            if (incidenciaPojo.getFechaResolucion() != null) {
                MySql.updateOne(incidenciaPojo.getId(), "incidencia", "fecha_resolucion", dateToMySQLDate(incidenciaPojo.getFechaResolucion()));
            }

            MySql.commitTrans();
            MySql.desconexion();
        } catch (Exception e) {
            throw new Exception("IncidenciaDao.Nuevo() Error: "
                    + e.getMessage());
        }
    }
     public void borrar(int id) throws Exception{
      try {
                       MySql.conexion();
                       MySql.removeOne(id, "incidencia");
                       MySql.desconexion();
               } catch (Exception e) {
                       throw new Exception("Error: " + e.getMessage());
               } finally {
                               MySql.desconexion();
               }
  }
//      public static LinkedList<IncidenciaPojo> getEditar(IncidenciaPojo incidenciaPojo) {
//
//        LinkedList<IncidenciaPojo> listaEditar = new LinkedList<>();
//        Connection conn = null;
//       
//
//        try {
//            MySql.conexion();
//
//
//
//             String sql = " SELECT ID, RESUMEN, CAMBIOS, ID_ESTADO, ID_REPOSITORIO,ID_USUARIO, "
//                    + " FECHA_ALTA, FECHA_RESOLUCION FROM INCIDENCIA WHERE ID = '"+ incidenciaPojo.getId() + "' ";
//
// 
//            ResultSet rs = MySql.get(sql);
//
//
//            while (rs.next()) {
//
//                 IncidenciaPojo incidencia = new IncidenciaPojo();
//                incidencia.setId(rs.getInt("ID"));
//                incidencia.setResumen(rs.getString("RESUMEN"));
//                incidencia.setCambios(rs.getString("CAMBIOS"));
//                incidencia.setIdEstado(rs.getInt("ID_ESTADO"));
//                incidencia.setIdRepositorio(rs.getInt("ID_REPOSITORIO"));
//                incidencia.setIdUsuario(rs.getInt("ID_USUARIO"));
//                incidencia.setFechaAlta(rs.getDate("FECHA_ALTA"));
//                incidencia.setFechaResolucion(rs.getDate("FECHA_RESOLUCION"));
//
//                listaEditar.add(incidencia);
//
//            }
//
//            MySql.desconexion();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return listaEditar;
//
//    }
     public IncidenciaPojo getEditar(IncidenciaPojo i) throws Exception {
		try {
			MySql.conexion();
			i.setResumen((String) MySql.getOne("incidencia", "resumen", i.getId()));
			i.setCambios((String) MySql.getOne("incidencia", "cambios", i.getId()));
                        i.setIdEstado((int) MySql.getOne("incidencia", "id_estado", i.getId()));
                        i.setIdRepositorio((int) MySql.getOne("incidencia", "id_repositorio", i.getId()));
                        i.setIdUsuario((int) MySql.getOne("incidencia", "id_usuario", i.getId()));
                        i.setFechaAlta((Date) MySql.getOne("incidencia", "fecha_alta", i.getId()));
                        i.setFechaResolucion((Date) MySql.getOne("incidencia", "fecha_resolucion", i.getId()));
			MySql.desconexion();
		} catch (Exception e) {
			throw new Exception("ClienteDao.getCliente: Error: "
					+ e.getMessage());
		} finally {
			try {
				MySql.desconexion();
			} catch (Exception e) {
				throw new Exception(
						"ClienteDao.getCliente: Error en la desconexion: "
								+ e.getMessage());
			}
		}
		return i;
	}
      public void Editar(IncidenciaPojo incidenciaPojo) throws Exception {
		try {
			MySql.conexion();
			MySql.initTrans();
			if (incidenciaPojo.getId() == 0) { //es nueva la incidencia
				incidenciaPojo.setId(MySql.insertOne("incidencia"));
			}
			MySql.updateOne(incidenciaPojo.getId(), "incidencia", "resumen", incidenciaPojo.getResumen());
            MySql.updateOne(incidenciaPojo.getId(), "incidencia", "cambios", incidenciaPojo.getCambios());
            MySql.updateOne(incidenciaPojo.getId(), "incidencia", "id_estado",Integer.toString(incidenciaPojo.getIdEstado()));
            MySql.updateOne(incidenciaPojo.getId(), "incidencia", "id_repositorio", "1");
            MySql.updateOne(incidenciaPojo.getId(), "incidencia", "id_usuario",Integer.toString(incidenciaPojo.getIdUsuario()));
            MySql.updateOne(incidenciaPojo.getId(), "incidencia", "fecha_alta", dateToMySQLDate(incidenciaPojo.getFechaAlta()));
             if (incidenciaPojo.getFechaResolucion() != null) {
                MySql.updateOne(incidenciaPojo.getId(), "incidencia", "fecha_resolucion", dateToMySQLDate(incidenciaPojo.getFechaResolucion()));
            } else {
                 MySql.updateOne(incidenciaPojo.getId(), "incidencia", "fecha_resolucion", null);
             }
			MySql.commitTrans();			
		} catch (Exception e) {
			MySql.rollbackTrans();
			throw new Exception("IncidenciaDao.setProducto: Error: " + e.getMessage());
		} finally {			
			MySql.desconexion();
		}
                
	}
  public String dateToMySQLDate(Date fecha) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(fecha);
    }
}
