<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio, Entities.PoliticaPrestamo"%>
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
    <link rel="stylesheet" type="text/css" href="css/style.css">
     
<title>Baja Politica de Prestamo</title>

	<%
	  	Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
		PoliticaPrestamo PoliticaPrestamo = (PoliticaPrestamo)request.getAttribute("PoliticaPrestamoBaja");
	%>
</head>
<body>

<form action="headerForm" method="post">
	<div class="contenedorNavBar">
		<div class="d-flex flex-row justify-content-start align-items-stretch">
			<div class="navBarItem navBarItem-Main">JAVAUTNFRRO2022</div>
			<div class="navBarItem"><button type="submit" name="opcion" value="menu" class="botonMenu">Menu</button></div>
			<div class="navBarItem ms-auto opcionLogout">Usuario: <%=c.getUsuario() %> <button class="btn btn-danger" type="submit" name="opcion" value="logout">Logout</button></div>
		</div>
	</div>
</form>

<div class="container">
	<p class="bienvenidoTitulo">Baja de una Politica Prestamo.</p>
	<form action="bajaPoliticaPrestamo" method="post" class="w-50">
	
		<label for="id">Id de la Politica de Prestamo:</label> <br>
		<input type="text" class="form-control" name="id" value="<%=PoliticaPrestamo.getIdPoliticaPrestamo()%>" readonly> <br>
		
		<label for="fechaDesde">Fecha Desde:</label> <br>
		<input type="text" class="form-control" name="fechaDesde" value="<%=PoliticaPrestamo.getFechaDesde().toString()%>" readonly> <br>
		
		<label for="cantMaxLibrosPend">Cant Max Libros Pendientes:</label> <br>
		<input type="text" class="form-control" name="cantMaxLibrosPend" value="<%=PoliticaPrestamo.getCantMaxLibrosPend()%>" readonly> <br>		
				
		<button type="submit" name="opcion" value="eliminar" class="btn btn-success mt-3">Eliminar Politica Prestamo</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-danger mt-3">Cancelar</button>
	</form> 
</div>

</body>
</html>