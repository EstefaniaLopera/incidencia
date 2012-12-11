<%-- 
    Document   : bienvenido
    Created on : 05-nov-2012, 17:56:59
    Author     : vesprada
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jdbc.Pojos.*"%>
<%@page import="jdbc.dao.*"%>



<!DOCTYPE html>
<%
    HttpSession sesion = request.getSession();
    //si no se inicia sesión, redirecciona al index
    UsuarioPojo oUsuario = (UsuarioPojo) sesion.getAttribute("usuario");
    if (oUsuario == null) {


        response.sendRedirect("index.jsp");

    } else {

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
             <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" href="bootstrap/css/bootstrap.css" media="all">
              <link rel="stylesheet" href="bootstrap/js/bootstrap-tab.js" media="all">
  <link rel="stylesheet" href="bootstrap/css/estilos.css" media="all">   
        <title>Bienvenido</title>
    </head>
    <body>
         <form  action="/proyecto1eva/Login" method ="POST" >
             <div class="contenedor">
            <div class="navbar navbar-inverse navbar-static-top">
                <div class="navbar-inner">
                
                    <div class="brand" id="logeado">Bienvenido, <%=oUsuario.getNombre()%> <%=oUsuario.getApe1()%> 
                        <a href="Login?accion=cerrar" class="btn-mini btn-danger">Cerrar Sesión</a></div>
                </div>
         </form>
         <ul class="nav nav-tabs">
  <li class="active"><a href="#home">Incidencias</a></li>
  <li><a href="/proyecto1eva/nuevo.jsp">Nueva Incidencia</a></li>
</ul>
 
<div class="tab-content">
  <div class="tab-pane active" id="home">
      <table  class="table table-condensed">
            <tr>
                <th>ID</th>
                <th>RESUMEN</th>
                <th>CAMBIOS</th>
                <th>ESTADO</th>
                <th>NUMERO DEL REPOSITORIO</th>
                <th>FECHA ALTA</th>
                <th>FECHA RESOLUCIÓN</th>
                <th>EDITAR</th>

            </tr>


            <%

                LinkedList<IncidenciaPojo> lista = IncidenciaDao.getIncidenciaDao(oUsuario);
                for (int i = 0; i < lista.size(); i++) {
                    EstadoDao estadoDAO = new EstadoDao();
                    EstadoPojo estadoPOJO = new EstadoPojo(lista.get(i).getIdEstado());
                    out.print("<tr>");
                    out.print("<td>" + lista.get(i).getId());
                    out.print("<td>" + lista.get(i).getResumen());
                    out.print("<td>" + lista.get(i).getCambios());
                    out.print("<td>" + estadoDAO.getEditar(estadoPOJO).getNombre());
                    out.print("<td>" + lista.get(i).getIdRepositorio());
                    out.print("<td>" + lista.get(i).getFechaAlta());
                    if (lista.get(i).getFechaResolucion() != null) {
                        out.print("<td>" + lista.get(i).getFechaResolucion());
                    } else {
                        out.print("<td> <i>No resuelta</i>");
                    }
                    out.print("<td> <a href=\"/proyecto1eva/EditarBorrar?id=" + lista.get(i).getId() + "&operacion=editar1\">Editar</a>-<a href=\"/proyecto1eva/EditarBorrar?id=" + lista.get(i).getId() + "&operacion=borrar\">Borrar</a>");
                    out.print("</tr>");
                }




            %>
        </table></div>
  <div class="tab-pane" id="profile"><%=oUsuario.getNombre()%></div>
</div>
 
<script>
  $(function () {
    $('.tabs a:last').tab('show')
  })
</script>
        <br />
</body>
</html>

<% }%>
