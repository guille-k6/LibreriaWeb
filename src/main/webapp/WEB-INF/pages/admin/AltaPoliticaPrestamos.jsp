<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Alta Politica de Prestamo</title>
	<%
 	  	Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
		LinkedList<String> errores = (LinkedList<String>)request.getAttribute("listaErrores");
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<p class="bienvenidoTitulo">Alta de una Politica de Prestamo.</p>
	<%if(!(errores == null)){
	for (String error : errores) {%>
	<p class="errorMensaje"><%=error %></p>
	<%}};%>
	
	<form action="altaPoliticaPrestamo" method="post" class="w-50">	
		<label for="fechaDesde">Fecha Desde:</label> <br>
		<input type="date" name="fechaDesde" value="2024-02-06" min="1900-01-01" max="2025-01-01"><br><br>
		
		<label for="getCantMaxLibrosPend">Cantidad Maxima de Libros Pendientes:</label> <br>
		<input type="text" class="form-control" name="cantMaxLibrosPend"> <br>
		
		
		<button type="submit" name="opcion" value="crearPoliticaPrestamo" class="btn btn-success mt-3">Crear</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-danger mt-3">Cancelar</button>
	</form> 
</div>
</body>
<script src="js/eliminarErrores.js"></script>
</html>