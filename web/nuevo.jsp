<%-- 
    Document   : Nuevo
    Created on : 21-nov-2012, 10:21:39
    Author     : Estefania
--%>

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
        <title>Nueva Incidencia</title>
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
                   alert("La incidencia se ha creado correctamente");              
                   return true;
               }    
               
           
           }
 
  </script>
    </head>
    <body>

        <form class="semantic" action="./Nuevo" method ="POST" accept-charset="ISO-8859-1" name="validar" onSubmit="return valida(this)">
              <div class="contenedor">
            <div class="navbar navbar-inverse navbar-static-top">
                <div class="navbar-inner">
                
                    <div class="brand" id="logeado">Bienvenido, <%=oUsuario.getNombre()%> <%=oUsuario.getApe1()%> 
                        <a href="Login?accion=cerrar" class="btn-mini btn-danger">Cerrar Sesión</a></div>
                </div>
                        <div id="contenido" >    
            
            <fieldset>
                <legend>Nueva Incidencia</legend>

                <div>
                    <label for="resumen">Resumen:</label> <textarea cols="4" rows="4" id="resumen"
                                                                    name="resumen"></textarea>
                </div>
                <div>
                    <label for="cambios">Cambios:</label> <input type="text" id="cambios"
                                                                 name="cambios" />
                </div>
                <input type="hidden" id="usuario" name="usuario" value="<%=oUsuario.getId()%>" />
                <div>
                     <label for="estadoetiqueta">Elige el estado de la incidencia</label>  
                    <br />
                    <select name="estado"> 
                        <option value="1">Abierta</option>
                        <option value="2">En estudio</option>
                        <option value="3">Resulta</option>                    
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