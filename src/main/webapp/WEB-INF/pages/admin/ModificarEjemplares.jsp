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
			request.getRequestDispatcher("index.html").forward(request, response);		
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
	<h2>Bienvenido, <%= c.getNombre() %> admin</h2>
	<h3>Modificar ejemplar</h3>
	<form action="modificarEjemplar" method="post" class="w-50">
	
		<label for="id">Id del ejemplar:</label> <br>
		<input type="text" class="form-control" name="id" value="<%=ejemplar.getIdEjemplar()%>" readonly> <br>
		
		<label for="idLibro">Titulo:</label> <br>
		<select name="idLibro" id="cars">
			<% for (Libro lib : libros){ %>
	    	<option value="<%=lib.getIdLibro()%>" <%if(lib.getIdLibro() == ejemplar.getLibro().getIdLibro() ){%> selected <% } %>> <%=lib.getTitulo()%>  <%=lib.getAutor().getApellido() + " " + lib.getAutor().getNombre()%> </option>
	    	<%} %>
	    </select>
	    <br>
				
		<button type="submit" name="opcion" value="editar" class="btn btn-success mt-3">Modificar libro</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-danger mt-3">Cancelar</button>
	</form> 
</div>

</body>
<script src="js/eliminarErrores.js"></script>
</html>