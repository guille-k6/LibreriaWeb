<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Socio"%>
<%@page import="java.time.LocalDate"%>
<%@page import="Entities.EjemplarCantidad"%>
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
	<meta charset="ISO-8859-1">
	<title>Confirmar prestamo</title>
		<%
			Socio c = (Socio)session.getAttribute("usuario");
			Socio alquila = (Socio)request.getAttribute("socioDeudor");
			if(!c.getAdmin()){
				request.getRequestDispatcher("index.jsp").forward(request, response);		
			}
		 	String mensaje = (String)request.getAttribute("estado");
		 	
		    LinkedList<EjemplarCantidad> lib = (LinkedList<EjemplarCantidad>)request.getAttribute("librosCantidad");
		    LocalDate currentDate = LocalDate.now();
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
		<p class="bienvenidoTitulo">Confirmar pedido</p>
		<%if(mensaje != null){ %>
			<p class="mensajeInfo"><%=mensaje%></p>
		<%} %>	
		<form action="confirmarPrestamo" method="post">								
			<div class="container">
				<div class="row">
					<div class="col-lg-12, col-sm-12, col-12">
						<div class="d-flex justify-content-between p-3">
							<div>
								<p class="fecha"><span class="badge">Fecha:</span> <%=currentDate %></p>
							</div>
							<div>
								<p class="fecha"><span class="badge">Socio:</span><%=alquila.getNombre() + " " + alquila.getApellido() %></p>
							</div>
						</div>
						<div class="table-responsive">
							<table class="table table-light table-striped table-hover">
							<thead>
								<tr>
									<th>ID</th>
									<th>ISBN</th>
									<th>Titulo</th>
									<th>Editorial</th>
									<th>Edición</th>
									<th>Días prestamo</th>
									<th>Autor</th>
									<th>Seleccionados</th>
								</tr>
							</thead>
							<tbody>
								<% for (EjemplarCantidad l : lib) {%>
								<tr>
									<td><%=l.getLibro().getIdLibro() %></td>
									<td><%=l.getLibro().getIsbn() %></td>
									<td><%=l.getLibro().getTitulo() %></td>
									<td><%=l.getLibro().getEditorial() %></td>
									<td><%=l.getLibro().getFechaEdicion().toString() %></td>
									<td><%=l.getLibro().getCantDiasMaxPrestamo() %></td>
									<td><%=l.getLibro().getAutor().getNombre() + " " + l.getLibro().getAutor().getApellido() %></td>
									<td><%=l.getCantidad() %></td>
									<td><input type="hidden" name="cantidadLibros-<%=l.getLibro().getIdLibro()%>" value="<%=l.getCantidad()%>"/></td>
								</tr>
								<% }%>
							</tbody>
							<input type="hidden" name="socioId" value="<%=alquila.getIdSocio()%>"/>
						</table>
						<div class="d-flex justify-content-end mx-3">
							<button type="submit" name="cancelar" class="btn btn-danger btn-md m-2">Cancelar</button>
							<button type="submit" name="confirmar" class="btn btn-success btn-md m-2">Confirmar pedido</button>
						</div>
						</div>
					</div>
				</div>
			</div>
		</form> 
	</div>
</body>
</html>