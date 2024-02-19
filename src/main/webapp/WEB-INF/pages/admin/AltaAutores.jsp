<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Alta autor</title>
	<%
 	  	Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
		LinkedList<String> errores = (LinkedList<String>)request.getAttribute("listaErrores");	
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>>

<div class="container">
	<p class="bienvenidoTitulo">Alta de un autor.</p>
	<%if(!(errores == null)){
	for (String error : errores) {%>
	<p class="errorMensaje"><%=error %></p>
	<%}};%>
	
	<form action="altaAutor" method="post" class="w-50">	
		<label for="nombre">Nombre del autor:</label> <br>
		<input type="text" class="form-control" name="nombre"> <br>
		
		<label for="apellido">Apellido del autor:</label> <br>
		<input type="text" class="form-control" name="apellido"> <br>
		
		
		<button type="submit" name="opcion" value="crearAutor" class="btn btn-success mt-3">Crear</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-danger mt-3">Cancelar</button>
	</form> 
</div>
</body>
<script src="js/eliminarErrores.js"></script>
</html>