/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdbc.Pojos.IncidenciaPojo;
import jdbc.Pojos.UsuarioPojo;
import jdbc.dao.*;
import jdbc.model.MySql;

/**
 *
 * @author Estefania
 */
public class Nuevo extends HttpServlet {

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
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        IncidenciaPojo incpojo = new IncidenciaPojo();
        IncidenciaDao incdao = new IncidenciaDao();
        PrintWriter out = response.getWriter();
        HttpSession Session = request.getSession();
        String destino = null;

        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
        try {


            System.out.println(request.getParameter("estado"));
            String resumen = request.getParameter("resumen");
            String cambios = request.getParameter("cambios");
            int idestado = Integer.parseInt(request.getParameter("estado"));
            String usuario = request.getParameter("usuario");

            incpojo.setId(0);
            incpojo.setResumen(resumen);
            incpojo.setCambios(cambios);
            incpojo.setIdEstado(idestado);

            if (idestado == 3) {
                incpojo.setFechaResolucion(new Date());
            }


            incpojo.setFechaAlta(new Date());

            incpojo.setIdUsuario(Integer.parseInt(usuario));

            incdao.nuevo(incpojo);

            destino = "/bienvenido.jsp";


            ServletContext cont = getServletConfig().getServletContext();
            RequestDispatcher reqDispatcher = cont.getRequestDispatcher(destino);
            reqDispatcher.forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            out.close();
        }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
