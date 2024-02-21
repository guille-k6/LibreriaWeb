<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio, Entities.Ejemplar, Entities.Libro,Entities.Autor, Logic.LibroLogic"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Modificar Ejemplar</title>
	<%
  		Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.jsp").forward(request, response);		
		}
		Ejemplar ejemplar = (Ejemplar)request.getAttribute("ejemplarModificar");
		LibroLogic liblog = new LibroLogic();
		LinkedList<Libro> libros = new LinkedList<Libro>();
		libros = liblog.getAll();
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
		    <li class="breadcrumb-item active" aria-current="page">Modificar</li>
		  </ol>
		</nav>
	</form>
	
	<p class="welcome-title mt-3">Modificar un autor</p>
	
	<form action="modificarEjemplar" method="post" class="w-50 mt-3">
	
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">&emsp; ID &emsp;</span>
		  <input type="text" name="id" value="<%=ejemplar.getIdEjemplar()%>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" readonly>
		</div>
		
	    <div class="input-group mb-3">
		  <label class="input-group-text" for="inputGroupSelect01">&ensp;Libro&ensp;</label>
		  <select name="idLibro" class="form-select" id="inputGroupSelect01">
			<% for (Libro lib : libros){ %>
	    	<option value="<%=lib.getIdLibro()%>"> <%=lib.getTitulo() + " (" + lib.getAutor().getNombre() + " " + lib.getAutor().getApellido() + ")"%> </option>
	    	<%} %>
		  </select>
		</div>
				
		<button type="submit" name="opcion" value="editar" class="btn btn-primary mt-2 px-4">Modificar</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-outline-secondary mt-2">Cancelar</button>
	</form> 
</div>

</body>
<script src="js/eliminarErrores.js"></script>
</html>