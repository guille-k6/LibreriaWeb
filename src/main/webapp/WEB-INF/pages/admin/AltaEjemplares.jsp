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
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
		LibroLogic liblog = new LibroLogic();
		LinkedList<Libro> libros = new LinkedList<Libro>();
		libros = liblog.getAll();
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<p class="bienvenidoTitulo">Alta de un ejemplar.</p>
	<form action="altaEjemplar" method="post" class="w-50">	

		<label for="titulo">Libro:</label> <br>
		<select name="titulo" class="custom-select">
			<% for (Libro lib : libros){ %>
	    	<option value="<%=lib.getIdLibro()%>"> <%=lib.getTitulo() + ". " + lib.getAutor().getNombre() + " " + lib.getAutor().getApellido()%> </option>
	    	<%} %>
	    </select>
	    <br>
		
		<button type="submit" name="opcion" value="crearEjemplar" class="btn btn-success mt-3">Crear</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-danger mt-3">Cancelar</button>
	</form> 
</div>
</body>
<script src="js/eliminarErrores.js"></script>
</html>