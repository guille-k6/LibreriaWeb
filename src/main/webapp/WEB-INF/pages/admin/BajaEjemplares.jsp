<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio, Entities.Ejemplar"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Baja Ejemplar</title>
	<%
	  	Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
		Ejemplar ejemplar = (Ejemplar)request.getAttribute("ejemplarBaja");
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<p class="bienvenidoTitulo">Baja de un ejemplar.</p>
	<form action="bajaEjemplar" method="post" class="w-50">
	
		<label for="id">Id del Ejemplar:</label> <br>
		<input type="text" class="form-control" name="id" value="<%=ejemplar.getIdEjemplar()%>" readonly> <br>
		
		<label for="titulo">Titulo del libro:</label> <br>
		<input type="text" class="form-control" name="titulo" value="<%=ejemplar.getLibro().getTitulo() + ". " + ejemplar.getLibro().getAutor().getNombre() + " " +ejemplar.getLibro().getAutor().getApellido()%>" readonly> <br>
				
		<button type="submit" name="opcion" value="eliminar" class="btn btn-success mt-3">Eliminar libro</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-danger mt-3">Cancelar</button>
	</form> 
</div>

</body>
</html>