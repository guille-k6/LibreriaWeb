<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio, Entities.PoliticaPrestamo "%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Modificar Politica de Prestamo</title>
	<%
	  	Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
		PoliticaPrestamo PoliticaPrestamo = (PoliticaPrestamo)request.getAttribute("PoliticaPrestamoModificar");
		LinkedList<String> errores = (LinkedList<String>)request.getAttribute("listaErrores");
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<p class="bienvenidoTitulo">Modificaci&oacute;n de una Politica de Prestamo.</p>
	<%if(!(errores == null)){
	for (String error : errores) {%>
	<p class="errorMensaje"><%=error %></p>
	<%}};%>
	
	<form action="modificarPoliticaPrestamo" method="post" class="w-50">
	
		<label for="id">Id de la Politica de Prestamo:</label> <br>
		<input type="text" class="form-control" name="id" value="<%=PoliticaPrestamo.getIdPoliticaPrestamo()%>" readonly> <br>
		
		<label for="fechaDesde">Fecha desde de la Politica de Prestamo:</label> <br>
		<input type="text" class="form-control" name="fechaDesde" value="<%=PoliticaPrestamo.getFechaDesde().toString()%>"> <br>
		
		<label for="cantMaxLibrosPend">Cantidad maxima de libros pendientes:</label> <br>
		<input type="text" class="form-control" name="cantMaxLibrosPend" value="<%=PoliticaPrestamo.getCantMaxLibrosPend()%>"> <br>		
				
		<button type="submit" name="opcion" value="editar" class="btn btn-success mt-3">Editar Politica Prestamo</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-danger mt-3">Cancelar</button>
	</form> 
</div>

</body>
<script src="js/eliminarErrores.js"></script>
</html>