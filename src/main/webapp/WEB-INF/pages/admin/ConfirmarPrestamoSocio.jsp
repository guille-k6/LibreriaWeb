<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Socio"%>
<%@page import="java.time.LocalDate"%>
<%@page import="Entities.EjemplarCantidad"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
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

<%@ include file="../NavigationBar.jsp" %>

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
							<button type="submit" name="cancelar" value="cancelar" class="btn btn-danger btn-md m-2">Cancelar</button>
							<button type="submit" name="aceptar" value="aceptar" class="btn btn-success btn-md m-2">Confirmar pedido</button>
						</div>
						</div>
					</div>
				</div>
			</div>
		</form> 
	</div>
</body>
</html>