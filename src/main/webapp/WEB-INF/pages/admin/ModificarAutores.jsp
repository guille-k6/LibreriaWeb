<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio, Entities.Autor"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Modificar autor</title>
	<%
	  	Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
		Autor autor = (Autor)request.getAttribute("autorModificar");
		LinkedList<String> errores = (LinkedList<String>)request.getAttribute("listaErrores");
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<p class="bienvenidoTitulo">Modificaci&oacute;n de un autor.</p>
	<%if(!(errores == null)){
	for (String error : errores) {%>
	<p class="errorMensaje"><%=error %></p>
	<%}};%>
	
	<form action="modificarAutor" method="post" class="w-50">
	
		<label for="id">Id del autor:</label> <br>
		<input type="text" class="form-control" name="id" value="<%=autor.getIdAutor()%>" readonly> <br>
		
		<label for="nombre">Nombre del autor:</label> <br>
		<input type="text" class="form-control" name="nombre" value="<%=autor.getNombre()%>"> <br>
		
		<label for="apellido">Apellido del autor:</label> <br>
		<input type="text" class="form-control" name="apellido" value="<%=autor.getApellido()%>"> <br>		
				
		<button type="submit" name="opcion" value="editar" class="btn btn-success mt-3">Editar autor</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-danger mt-3">Cancelar</button>
	</form> 
</div>

</body>
<script src="js/eliminarErrores.js"></script>
</html>