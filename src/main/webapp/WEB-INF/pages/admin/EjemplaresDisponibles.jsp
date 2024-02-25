<%@page import="java.util.LinkedList"%>
<%@page import="java.io.*,java.util.*"%>
<%@page import="Entities.Socio"%>
<%@page import="Entities.EjemplarCantidad"%>
<%@page import="Entities.Libro, Data.DataLibro,Entities.Ejemplar,Logic.EjemplarLogic"%>
<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Prestamo - Elegir libros</title>
	<%
		Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.jsp").forward(request, response);		
		}
		Socio alquila = (Socio)request.getAttribute("socioDeudor");
	 	String mensaje = (String)request.getAttribute("mensaje");
	    EjemplarLogic ejelog = new EjemplarLogic();
	    LinkedList<EjemplarCantidad> lib = ejelog.getAmountOfLibros();		
	%>
</head>
<body style="display: flex; flex-direction: column; min-height: 100vh;">

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<form action="breadcrumb" method="get">
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><button type="submit" name="page" value="menuAdmin.jsp" class="button-emula-anchor">Home</button></li>
		    <li class="breadcrumb-item"><button type="submit" name="page" value="admin/BuscarSocio.jsp" class="button-emula-anchor">Elegir socio</button></li>
		    <li class="breadcrumb-item active" aria-current="page">Elegir libros</li>
		  </ol>
		</nav>
	</form>
	
	<form action="prestamo" method="post">
		<div class="w-100 d-flex justify-content-between align-items-center mx-3">
				<p class="welcome-title">Libros disponibles</p>
				<div class="d-flex justify-content-end mx-5">
					<button type="submit" name="pedir" class="btn btn-primary btn-md p-2">Realizar pedido</button>
					<input type="hidden" name="socioDeudor" value="<%=alquila.getIdSocio()%>" />
				</div>			
		</div>	
					
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
									<th>Edición</th>
									<th>Días prestamo</th>
									<th>Autor</th>
									<th>Disponibilidad</th>
									<th>Cantidad</th>
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
									<td>
										<input type="number" name="cantidadLibros-<%=l.getLibro().getIdLibro()%>" min="0" max="<%=l.getCantidad() %>" value="0"/>
									</td>
								</tr>
								<% }%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	<%if(mensaje != null){ %>
		<p hidden class="mensajeInfo"><%=mensaje%></p>
	<%} %>	
	</form> 
</div>
</body>
<script src="js/eliminarMensajes.js"></script>
</html>