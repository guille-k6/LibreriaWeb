<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio, Entities.PoliticaPrestamo "%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Modificar Politica de prestamo</title>
	<%
	  	Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
		PoliticaPrestamo PoliticaPrestamo = (PoliticaPrestamo)request.getAttribute("PoliticaPrestamoModificar");
		LinkedList<String> errores = (LinkedList<String>)request.getAttribute("listaErrores");
	%>
</head>
<body style="display: flex; flex-direction: column; min-height: 100vh;">

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<form action="breadcrumb" method="get">
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><button type="submit" name="page" value="menuAdmin.jsp" class="button-emula-anchor">Home</button></li>
		    <li class="breadcrumb-item" aria-current="page"><button type="submit" name="page" value="/admin/ABMPoliticaPrestamos.jsp" class="button-emula-anchor">Políticas de préstamo</button></li>
		    <li class="breadcrumb-item active" aria-current="page">Modificar</li>
		  </ol>
		</nav>
	</form>
	
	<p class="welcome-title mt-3">Modificar una Política de prestamo</p>
	<%if(!(errores == null)){
	for (String error : errores) {%>
	<p class="errorMensaje"><%=error %></p>
	<%}};%>
	
	<form action="modificarPoliticaPrestamo" method="post" class="w-50 mt-3">
	
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">&emsp; ID &emsp;</span>
		  <input type="number" name="id" value="<%=PoliticaPrestamo.getIdPoliticaPrestamo()%>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Fecha desde</span>
		  <input type="date" name="fechaDesde" value="<%=PoliticaPrestamo.getFechaDesde().toString()%>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Máximo libros pendientes</span>
		  <input type="number" name="cantMaxLibrosPend" value="<%=PoliticaPrestamo.getCantMaxLibrosPend()%>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>
				
		<button type="submit" name="opcion" value="editar" class="btn btn-primary mt-2 px-4">Modificar</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-outline-secondary mt-2">Cancelar</button>
	</form> 
</div>

</body>
<script src="js/eliminarErrores.js"></script>
</html>