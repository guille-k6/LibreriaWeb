<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio"%>
<%@page import="Entities.Libro, Data.DataLibro,Entities.Ejemplar,Logic.EjemplarLogic"%>
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
<title>Libros</title>

	<%
		Socio c = (Socio)session.getAttribute("usuario");
		Socio alquila = (Socio)request.getAttribute("socioPrestar");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
	 	String mensaje = (String)request.getAttribute("estado");
	 	
		LinkedList<Libro> libros = new LinkedList<Libro>();
	    DataLibro liblog = new DataLibro();
	    EjemplarLogic ejelog = new EjemplarLogic();
	    libros = liblog.getAll();		
	%>
</head>
<body>

<form action="headerForm" method="post">
	<div class="contenedorNavBar">
		<div class="d-flex flex-row justify-content-start align-items-stretch">
			<div class="navBarItem navBarItem-Main">JAVAUTNFRRO2022</div>
			<div class="navBarItem"><button type="submit" name="opcion" value="menu" class="botonMenu">Menu</button></div>
			<div class="navBarItem ms-auto opcionLogout">Usuario: <%=c.getUsuario() %>  <button class="btn btn-danger" type="submit" name="opcion" value="logout">Logout</button></div>
			<div class="navBarItem">Usuario a prestar: <%=alquila.getUsuario() %> </div>
		</div>
	</div>
</form>

<div class="container">
	<p class="bienvenidoTitulo">Ejemplares Disponibles</p>
	<%if(mensaje != null){ %>
		<p class="mensajeInfo"><%=mensaje%></p>
	<%} %>
	<form action="ABMLibrosForm" method="get">	<!-- Cambiar form action -->			
				
		<div class="container">
			<div class="row">
				<div class="col-lg-12, col-sm-12, col-12">
					<div class="table-responsive">
						<table class="table table-light table-striped table-hover">
							<thead>
								<tr>
									<th>ID</th>
									<th>ISBN</th>
									<th>Titulo</th>
									<th>Editorial</th>
									<th>Fecha de edición</th>
									<th>Máximo tiempo de préstamo</th>
									<th>Autor</th>
									<th>Ejemplares disponibles</th>
								</tr>
							</thead>
							<tbody>
								<% for (Libro lib : libros) {%>
								<tr>
									<td><%=lib.getIdLibro() %></td>
									<td><%=lib.getIsbn() %></td>
									<td><%=lib.getTitulo() %></td>
									<td><%=lib.getEditorial() %></td>
									<td><%=lib.getFechaEdicion().toString() %></td>
									<td><%=lib.getCantDiasMaxPrestamo() %></td>
									<td><%=lib.getAutor().getNombre() + " " + lib.getAutor().getApellido() %></td>
									<td><%=ejelog.getAllEjemplaresByLibro(lib).size() %></td>
									<td><button type="submit" name="pedir" <%= (ejelog.getAllEjemplaresByLibro(lib).size() == 0) ? "disabled" : "" %> value="<%=lib.getIdLibro()+"-"+alquila.getIdSocio()%>" class="btn btn-danger">Pedir</button></td>
								</tr>
								<% }%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</form> 
</div>
</body>
<script src="js/eliminarMensajes.js"></script>
</html>