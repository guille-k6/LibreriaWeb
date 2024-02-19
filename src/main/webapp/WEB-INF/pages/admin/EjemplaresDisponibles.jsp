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
	<title>Libros</title>
	<%
		Socio c = (Socio)session.getAttribute("usuario");
		Socio alquila = (Socio)request.getAttribute("socioDeudor");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.jsp").forward(request, response);		
		}
	 	String mensaje = (String)request.getAttribute("estado");
	    EjemplarLogic ejelog = new EjemplarLogic();
	    LinkedList<EjemplarCantidad> lib = ejelog.getAmountOfLibros();		
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<p class="bienvenidoTitulo">Libros disponibles</p>
	<%if(mensaje != null){ %>
		<p class="mensajeInfo"><%=mensaje%></p>
	<%} %>
	<form action="prestamo" method="post">	<!-- Cambiar form action -->			
				
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
									<td>
								</tr>
								<% }%>
							</tbody>
						</table>
						<div class="d-flex justify-content-end mx-3">
							<button type="submit" name="pedir" class="btn btn-primary btn-md p-2">Realizar pedido</button>
							<input type="hidden" name="socioDeudor" value="<%=alquila.getIdSocio()%>" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</form> 
</div>
</body>
<script src="js/eliminarMensajes.js"></script>
</html>