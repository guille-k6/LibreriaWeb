<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio, Entities.Libro, Logic.LibroLogic"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>   
	<title>Alta ejemplar</title>
	<%
	  	Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.jsp").forward(request, response);		
		}
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
		    <li class="breadcrumb-item active" aria-current="page">Alta</li>
		  </ol>
		</nav>
	</form>

	<p class="welcome-title mt-3">Dar de alta un ejemplar</p>
	<form action="altaEjemplar" method="post" class="w-50 mt-3">	
	    
	    <div class="input-group mb-3">
		  <label class="input-group-text" for="inputGroupSelect01">Libro</label>
		  <select name="titulo" class="form-select" id="inputGroupSelect01">
			<% for (Libro lib : libros){ %>
	    	<option value="<%=lib.getIdLibro()%>"> <%=lib.getTitulo() + " (" + lib.getAutor().getNombre() + " " + lib.getAutor().getApellido() + ")"%> </option>
	    	<%} %>
		  </select>
		</div>
		
		<button type="submit" name="opcion" value="crearEjemplar" class="btn btn-primary mt-2 px-4">Crear</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-outline-secondary mt-2">Cancelar</button>
	</form> 
</div>
</body>
<script src="js/eliminarErrores.js"></script>
</html>