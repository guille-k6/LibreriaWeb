<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio, Entities.Autor, Logic.AutorLogic"%>
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
    <link rel="stylesheet" type="text/css" href="css/style.css">
    
<title>Alta libro</title>

	<%
	  	Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
		AutorLogic autlog = new AutorLogic();
		LinkedList<Autor> autores = new LinkedList<Autor>();
		autores = autlog.getAll();
		LinkedList<String> errores = (LinkedList<String>)request.getAttribute("listaErrores");
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
	<p class="bienvenidoTitulo">Alta de un libro.</p>
	<%if(!(errores == null)){
	for (String error : errores) {%>
	<p class="errorMensaje"><%=error %></p>
	<%}};%>
	<form action="altaLibro" method="post" class="w-50">	
		<label for="nombre">ISBN:</label> <br>
		<input type="text" class="form-control" name="isbn"> <br>
		
		<label for="nombre">Titulo:</label> <br>
		<input type="text" class="form-control" name="titulo"> <br>
		
		<label for="nombre">Editorial:</label> <br>
		<input type="text" class="form-control" name="editorial"> <br>
		
		<label for="nombre">Fecha de edición:</label> <br>
		<input type="date" name="fechaEdicion" value="2022-11-23" min="1900-01-01" max="2023-01-01"><br><br>
		
		<label for="apellido">Máximo tiempo de préstamo (días):</label> <br>
		<input type="text" class="form-control" name="maxDias"> <br>
		
		<label for="autor">Autor:</label> <br>
		<select name="autor" id="cars">
			<% for (Autor aut : autores){ %>
	    	<option value="<%=aut.getIdAutor()%>"> <%=aut.getNombre()%>  <%=aut.getApellido()%> </option>
	    	<%} %>
	    </select>
	    <br>
			
		
		<button type="submit" name="opcion" value="crearLibro" class="btn btn-success mt-3">Crear</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-danger mt-3">Cancelar</button>
	</form> 
</div>

</body>
<script src="js/eliminarErrores.js"></script>
</html>