<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio, Entities.PoliticaPrestamo"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Baja Politica de Prestamo</title>
	<%
	  	Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
		PoliticaPrestamo PoliticaPrestamo = (PoliticaPrestamo)request.getAttribute("PoliticaPrestamoBaja");
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<p class="bienvenidoTitulo">Baja de una Politica Prestamo.</p>
	<form action="bajaPoliticaPrestamo" method="post" class="w-50">
	
		<label for="id">Id de la Politica de Prestamo:</label> <br>
		<input type="text" class="form-control" name="id" value="<%=PoliticaPrestamo.getIdPoliticaPrestamo()%>" readonly> <br>
		
		<label for="fechaDesde">Fecha Desde:</label> <br>
		<input type="text" class="form-control" name="fechaDesde" value="<%=PoliticaPrestamo.getFechaDesde().toString()%>" readonly> <br>
		
		<label for="cantMaxLibrosPend">Cant Max Libros Pendientes:</label> <br>
		<input type="text" class="form-control" name="cantMaxLibrosPend" value="<%=PoliticaPrestamo.getCantMaxLibrosPend()%>" readonly> <br>		
				
		<button type="submit" name="opcion" value="eliminar" class="btn btn-success mt-3">Eliminar Politica Prestamo</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-danger mt-3">Cancelar</button>
	</form> 
</div>

</body>
</html>