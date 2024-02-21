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
			request.getRequestDispatcher("index.jsp").forward(request, response);		
		}
		Ejemplar ejemplar = (Ejemplar)request.getAttribute("ejemplarBaja");
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<form action="breadcrumb" method="get">
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><button type="submit" name="page" value="menuAdmin.jsp" class="button-emula-anchor">Home</button></li>
		    <li class="breadcrumb-item" aria-current="page"><button type="submit" name="page" value="admin/ABMEjemplares.jsp" class="button-emula-anchor">Ejemplares</button></li>
		    <li class="breadcrumb-item active" aria-current="page">Baja</li>
		  </ol>
		</nav>
	</form>

	<p class="welcome-title mt-3">Dar de baja un ejemplar</p>
	<form action="bajaEjemplar" method="post" class="w-50 mt-3">
	
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">&ensp;ID&ensp;</span>
		  <input type="text" name="id" value="<%=ejemplar.getIdEjemplar()%>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" readonly>
		</div>
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default"> Libro </span>
		  <input type="text" name="titulo" value="<%=ejemplar.getLibro().getTitulo() + " (" + ejemplar.getLibro().getAutor().getNombre() + " " +ejemplar.getLibro().getAutor().getApellido() + ")"%>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" readonly>
		</div>
				
		<button type="submit" name="opcion" value="eliminar" class="btn btn-primary mt-2 px-4">Eliminar</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-outline-secondary mt-2">Cancelar</button>
	</form> 
</div>

</body>
</html>