<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio, Entities.Libro, Logic.LibroLogic"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name=description content="Trabajo pr�ctico Java. Sistema de gesti�n de una librer�a.">
    <meta name=keywords content="library">
    <!-- Bootstrap 5.2 CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
 	<!-- local styles -->
<title>Alta libro</title>

	<%
 	  	Socio c = (Socio)session.getAttribute("usuario");
// 		if(!c.getAdmin()){
// 			request.getRequestDispatcher("WEB-INF/pages/admin/ABMAutores.jsp").forward(request, response);
// 		}
		LibroLogic liblog = new LibroLogic();
		LinkedList<Libro> libros = new LinkedList<Libro>();
		libros = liblog.getAll();
	%>
</head>
<body>

<nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <div class="navbar-nav">
        <a class="nav-link active" aria-current="page" href="#">Home</a>
        <a class="nav-link" href="#">Features</a>
        <a class="nav-link" href="#">Pricing</a>
        <a class="nav-link disabled">Disabled</a>
      </div>
    </div>
  </div>
</nav>

	<h2>Alta Ejemplar.<%= c.getNombre() %> admin</h2>
	<form action="altaEjemplar" method="post">	

		<label for="nombre">Titulo:</label> <br>
		<select name="titulo" id="cars">
			<% for (Libro lib : libros){ %>
	    	<option value="<%=lib.getIdLibro()%>"> <%=lib.getTitulo()%>  <%=lib.getAutor().getApellido()%> </option>
	    	<%} %>
	    </select>
	    <br>
			
		
		<button type="submit" name="opcion" value="crearEjemplar" class="input-button">Crear</button>
        <button type="submit" name="opcion" value="cancelar" class="input-button">Cancelar</button>
	</form> 

</body>
</html>