<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio, Entities.Autor"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Baja autor</title>
	<%
	  	Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
		Autor autor = (Autor)request.getAttribute("autorBaja");
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<p class="bienvenidoTitulo">Baja de un autor.</p>
	<form action="bajaAutor" method="post" class="w-50">
	
		<label for="id">Id del autor:</label> <br>
		<input type="text" class="form-control" name="id" value="<%=autor.getIdAutor()%>" readonly> <br>
		
		<label for="nombre">Nombre del autor:</label> <br>
		<input type="text" class="form-control" name="nombre" value="<%=autor.getNombre()%>" readonly> <br>
		
		<label for="apellido">Apellido del autor:</label> <br>
		<input type="text" class="form-control" name="apellido" value="<%=autor.getApellido()%>" readonly> <br>		
				
		<button type="submit" name="opcion" value="eliminar" class="btn btn-success mt-3">Eliminar autor</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-danger mt-3">Cancelar</button>
	</form> 
</div>

</body>
</html>