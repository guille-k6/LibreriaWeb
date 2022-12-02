<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio, Entities.Libro"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name=description content="Trabajo práctico Java. Sistema de gestión de una librería.">
    <meta name=keywords content="library">
    <!-- Bootstrap 5.2 CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
 	<!-- local styles -->
<title>Baja autor</title>

	<%
 	  	Socio c = (Socio)session.getAttribute("usuario");
// 		if(!c.getAdmin()){
// 			request.getRequestDispatcher("WEB-INF/pages/admin/ABMAutores.jsp").forward(request, response);
// 		}
		Libro libro = (Libro)request.getAttribute("libroBaja");
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

	<h2>Bienvenido, <%= c.getNombre() %> admin</h2>
	<h3>Baja libro</h3>
	<form action="bajaLibro" method="post">
	
		<label for="id">Id del libro:</label> <br>
		<input type="text" class="form-control" name="id" value="<%=libro.getIdLibro()%>" readonly> <br>
		
		<label for="nombre">ISBN del libro:</label> <br>
		<input type="text" class="form-control" name="isbn" value="<%=libro.getIsbn()%>" readonly> <br>
		
		<label for="apellido">Título del libro:</label> <br>
		<input type="text" class="form-control" name="titulo" value="<%=libro.getTitulo()%>" readonly> <br>		
		
		<label for="apellido">Editorial del libro:</label> <br>
		<input type="text" class="form-control" name="editorial" value="<%=libro.getEditorial()%>" readonly> <br>	
		
		<label for="apellido">Fecha de edición del libro:</label> <br>
		<input type="text" class="form-control" name="fechaEdicion" value="<%=libro.getFechaEdicion().toString()%>" readonly> <br>
		
		<label for="apellido">Días máximos permitidos para prestar del libro:</label> <br>
		<input type="text" class="form-control" name="maxDias" value="<%=libro.getCantDiasMaxPrestamo()%>" readonly> <br>
		
		<label for="apellido">Autor del libro:</label> <br>
		<input type="text" class="form-control" name="editorial" value="<%=libro.getAutor().getNombre()%> <%=libro.getAutor().getApellido()%>" readonly> <br>
				
		<button type="submit" name="opcion" value="eliminar" class="input-button">Eliminar libro</button>
        <button type="submit" name="opcion" value="cancelar" class="input-button">Cancelar</button>
	</form> 

</body>
</html>