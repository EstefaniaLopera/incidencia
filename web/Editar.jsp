<%--

    Document   : Editar
    Created on : 21-nov-2012, 18:21:04
    Author     : vesprada
--%>

<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jdbc.Pojos.*"%>
<%@page import="jdbc.dao.*"%>
<!DOCTYPE html>
<%
    HttpSession sesion = request.getSession();


    //si no se inicia sesión, redirecciona al index
    UsuarioPojo oUsuario = (UsuarioPojo) sesion.getAttribute("usuario");
    IncidenciaPojo incidencia = (IncidenciaPojo) request.getAttribute("incidencia");
  IncidenciaDao incDao = new IncidenciaDao();
    if (oUsuario == null) {


        response.sendRedirect("index.jsp");

    } else {

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="bootstrap/css/bootstrap.css" media="all">
              <link rel="stylesheet" href="bootstrap/js/bootstrap-tab.js" media="all">
  <link rel="stylesheet" href="bootstrap/css/estilos.css" media="all">
  <script type="text/javascript">
           
           function vacio(q) {  
               for ( i = 0; i < q.length; i++ ) {  
                   if ( q.charAt(i) != " ") {
                       
                       return true  ;
                   }  
               }  
               return false  ;
           }    
           
           function valida(dato) {                
         
               if( vacio(dato.resumen.value) == false ) {  
                   alert("El campo resumen no puede estar vacio")  
                   document.validar.resumen.focus();
                   return false  ;                    
               }
               
               if( vacio(dato.cambios.value) == false ) {  
                   alert("El campo cambios no puede estar vacio¡")  
                   document.validar.cambios.focus();
                   return false  ;                    
               }
             
               else {  
                   alert("La incidencia se ha modificado correctamente.");              
                   return true;
               }    
               
           
           }
 
  </script>
        <title>Editar Incidencia</title>
    </head>
    <body>
       <form class="semantic" action="./EditarBorrar" method ="POST"  accept-charset="ISO-8859-1" name="validar" onSubmit="return valida(this)">
             <div class="contenedor">
            <div class="navbar navbar-inverse navbar-static-top">
                <div class="navbar-inner">
                
                    <div class="brand" id="logeado">Bienvenido, <%=oUsuario.getNombre()%> <%=oUsuario.getApe1()%> 
                        <a href="Login?accion=cerrar" class="btn-mini btn-danger">Cerrar Sesión</a></div>
                </div>
                        <div id="contenido" >    
           <fieldset>
                <legend>Editar Incidencia</legend>
             

                <div>
                    <label for="resumen">Resumen:</label> <input type="text" id="resumen"
                                                                 name="resumen"
                                                                 value="<%=incidencia.getResumen()%>" />
                </div>
                <div>
                    <label for="cambios">Cambios:</label> <input type="text" id="cambios"
                                                                 name="cambios"  value="<%=incidencia.getCambios()%>" />
                </div>

                <input type="hidden" name="id" value="<%=incidencia.getId()%>" />
                <input type="hidden" name="id_repositorio" value="<%=incidencia.getIdRepositorio()%>" />
                <input type="hidden" name="fecha_alta" value="<%=incidencia.getFechaAlta()%>" />
                <input type="hidden" name="operacion" value="editar2" />
                <div>
                   <label for="estadoetiqueta">Elige el estado de la incidencia</label>  
                    <select name="estado"> 
                        <option value="1" <%=incidencia.getIdEstado() == 1?"selected=\"selected\"":"" %>>Abierta</option>
                        <option value="2" <%=incidencia.getIdEstado() == 2?"selected=\"selected\"":"" %>>En estudio</option>
                        <option value="3" <%=incidencia.getIdEstado() == 3?"selected=\"selected\"":"" %>>Resuelta</option>                    
                    </select>
                </div>
                
                 <div class="controls">
                        <input class="btn btn-primary" type="submit" value="Enviar"/>
                    </div>
               
            </fieldset>
                        </div>
               

        </form>
    </body>
</html>
<% }%>