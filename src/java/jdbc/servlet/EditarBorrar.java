/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdbc.Pojos.IncidenciaPojo;
import jdbc.Pojos.UsuarioPojo;
import jdbc.dao.IncidenciaDao;

/**
 *
 * @author vesprada
 */
public class EditarBorrar extends HttpServlet {

    String destino;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        HttpSession Session = request.getSession();

        IncidenciaDao incidenciaDao = new IncidenciaDao();

        String operacion = request.getParameter("operacion");
        int identificador = Integer.parseInt(request.getParameter("id"));

        IncidenciaPojo incidencia = new IncidenciaPojo();

        switch (operacion) {
            case "borrar":

                incidenciaDao.borrar(identificador);

                destino = "/bienvenido.jsp";
                break;

            case "editar1":

                incidencia.setId(identificador);
                incidencia = incidenciaDao.getEditar(incidencia);

                //llamar al formulario y pasarse incidencia
                destino = "/Editar.jsp";
                request.setAttribute("incidencia", incidencia);
                break;
            case "editar2":
                Date fechaAlta = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fecha_alta"));

                incidencia.setId(identificador);
                incidencia.setCambios(request.getParameter("cambios"));
                incidencia.setResumen(request.getParameter("resumen"));
                int idestado = Integer.parseInt(request.getParameter("estado"));
                incidencia.setIdEstado(idestado);
                incidencia.setIdRepositorio(Integer.parseInt(request.getParameter("id_repositorio")));
                incidencia.setFechaAlta(fechaAlta);
                UsuarioPojo oUsuario = (UsuarioPojo) Session.getAttribute("usuario");
                incidencia.setIdUsuario(oUsuario.getId());

                if (idestado == 3) {
                    incidencia.setFechaResolucion(new Date());
                } else {
                    incidencia.setFechaResolucion(null);
                }

                incidencia.setFechaAlta(new Date());


                incidenciaDao.Editar(incidencia);

                destino = "/bienvenido.jsp";
                break;




        }
        ServletContext cont = getServletConfig().getServletContext();
        RequestDispatcher reqDispatcher = cont.getRequestDispatcher(destino);
        reqDispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(EditarBorrar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(EditarBorrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
