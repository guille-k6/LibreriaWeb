<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio"%>
<%@page import="Entities.PoliticaPrestamo, Data.DataPoliticaPrestamo"%>
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
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
 	<!-- local styles -->
    <link rel="stylesheet" type="text/css" href="css/style.css">
    
<title>PoliticaPrestamos</title>

	<%
		Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
	 	String mensaje = (String)request.getAttribute("estado");
	 	
		LinkedList<PoliticaPrestamo> PoliticaPrestamos = new LinkedList<PoliticaPrestamo>();
	    DataPoliticaPrestamo pollog = new DataPoliticaPrestamo();
	    PoliticaPrestamos = pollog.getAll();		
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
	<p class="bienvenidoTitulo">ABM PoliticaPrestamos.</p>
	<%if(mensaje != null){ %>
		<p class="mensajeInfo"><%=mensaje%></p>
	<%} %>	
	<form action="ABMPoliticaPrestamosForm" method="get">					
		<button type="submit" name="opcion" value="alta" class="btn btn-success mb-3">Añadir una Politica Prestamo</button>			
		<div class="container">
			<div class="row">
				<div class="col-lg-12, col-sm-12, col-12">
					<div class="table-responsive">
						<table class="table table-light table-striped table-hover">
							<thead>
								<tr>
									<th>ID</th>
									<th>Fecha Desde</th>
									<th>Cantidad Max Libros Pendientes</th>
								</tr>
							</thead>
							<tbody>
								<% for (PoliticaPrestamo pol : PoliticaPrestamos) {%>
								<tr>
									<td><%=pol.getIdPoliticaPrestamo() %></td>
									<td><%=pol.getFechaDesde().toString() %></td>
									<td><%=pol.getCantMaxLibrosPend() %></td>
									<td><button type="submit" name="editar" value="<%= pol.getIdPoliticaPrestamo()%>" class="btn btn-primary">Editar</button></td>
									<td><button type="submit" name="eliminar" value="<%=pol.getIdPoliticaPrestamo()%>" class="btn btn-danger">Eliminar</button></td>
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