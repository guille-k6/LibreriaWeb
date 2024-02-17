<%@page import="java.util.LinkedList, java.util.List"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Prestamo, Logic.PrestamoLogic, Entities.Socio"%>
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
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
	 	String mensaje = (String)request.getAttribute("estado");
	 	
		PrestamoLogic pl = new PrestamoLogic();
	 	List<Prestamo> prestamos = pl.getAll();	
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
	<p class="bienvenidoTitulo">Prestamos activos</p>
	<%if(mensaje != null){ %>
		<p class="mensajeInfo"><%=mensaje%></p>
	<%} %>
	<form action="verUnPrestamo" method="get">							
		<div class="container">
			<div class="row">
				<div class="col-lg-12, col-sm-12, col-12">
					<div class="table-responsive">
						<table class="table table-light table-striped table-hover">
							<thead>
								<tr>
									<th>ID</th>
									<th>Fecha</th>
									<th>Socio</th>
									<th>Ver</th>
								</tr>
							</thead>
							<tbody>
								<% for (Prestamo p : prestamos) {%>
								<tr>
									<td><%=p.getIdPrestamo() %></td>
									<td><%=p.getFechaPrestamo()%></td>
									<td><%=p.getSocio().getNombre() + " " + p.getSocio().getApellido()%></td>
									<td><button type="submit" name="ver" value="<%=p.getIdPrestamo()%>" class="btn btn-primary">Detalle</button></td>
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