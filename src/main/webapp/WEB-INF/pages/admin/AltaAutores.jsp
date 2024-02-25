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
			request.getRequestDispatcher("index.jsp").forward(request, response);		
		}
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
		    <li class="breadcrumb-item" aria-current="page"><button type="submit" name="page" value="admin/ABMAutores.jsp" class="button-emula-anchor">Autores</button></li>
		    <li class="breadcrumb-item active" aria-current="page">Alta</li>
		  </ol>
		</nav>
	</form>

	<p class="welcome-title mt-3">Dar de alta un autor</p>
	<%if(!(errores == null)){
	for (String error : errores) {%>
	<p class="errorMensaje"><%=error %></p>
	<%}};%>
	
	<form action="altaAutor" method="post" class="w-50 mt-3">	
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Nombre</span>
		  <input type="text" name="nombre" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>
				
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Apellido</span>
		  <input type="text" name="apellido" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>
		
		
		<button type="submit" name="opcion" value="crearAutor" class="btn btn-primary mt-2 px-4">Crear</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-outline-secondary mt-2">Cancelar</button>
	</form> 
</div>
</body>
<script src="js/eliminarErrores.js"></script>
</html>