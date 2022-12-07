<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio, Entities.Libro, Entities.Autor, Logic.AutorLogic"%>
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
<title>Modificar libro</title>

	<%
	  	Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
		Libro libro = (Libro)request.getAttribute("libroModificar");
		
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
	<p class="bienvenidoTitulo">Modificaci&oacute;n de un libro.</p>
	<%if(!(errores == null)){
	for (String error : errores) {%>
	<p class="errorMensaje"><%=error %></p>
	<%}};%>
	<form action="modificarLibro" method="post" class="w-50">
	
		<label for="id">Id del libro:</label> <br>
		<input type="text" class="form-control" name="id" value="<%=libro.getIdLibro()%>" readonly> <br>
		
		<label for="isbn">ISBN del libro:</label> <br>
		<input type="text" class="form-control" name="isbn" value="<%=libro.getIsbn()%>"> <br>
		
		<label for="titulo">Título del libro:</label> <br>
		<input type="text" class="form-control" name="titulo" value="<%=libro.getTitulo()%>"> <br>		
		
		<label for="editorial">Editorial del libro:</label> <br>
		<input type="text" class="form-control" name="editorial" value="<%=libro.getEditorial()%>"> <br>	
		
		<label for="fechaEdicion">Fecha de edición del libro:</label> <br>
		<input type="date" class="form-control" name="fechaEdicion" value="<%=libro.getFechaEdicion().toString()%>"> <br>
		
		<label for="maxDias">Días máximos permitidos para prestar del libro:</label> <br>
		<input type="text" class="form-control" name="maxDias" value="<%=libro.getCantDiasMaxPrestamo()%>"> <br>
		
		<label for="autor">Autor:</label> <br>
		<select name="autor" id="cars">
			<% for (Autor aut : autores){ %>
	    	<option value="<%=aut.getIdAutor()%>" <%if(aut.getIdAutor() == libro.getAutor().getIdAutor() ){%> selected <% } %>> <%=aut.getNombre()%>  <%=aut.getApellido()%> </option>
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