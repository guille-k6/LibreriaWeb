<%@page import="java.util.LinkedList, java.util.List"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Prestamo, Logic.PrestamoLogic, Entities.Socio, Entities.LineaDePrestamo"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Libros</title>
	<%
		Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
	 	Prestamo prestamo = (Prestamo)request.getAttribute("prestamo");
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<p class="bienvenidoTitulo">Prestamo ID: <%=prestamo.getIdPrestamo()%></p>
	<form action="devolverUnEjemplar" method="post">							
		<div class="container">
			<div class="row">
				<input type="hidden" name="prestamoId" value="<%=prestamo.getIdPrestamo()%>">
				<p><%= prestamo.getSocio().getNombre() + " " + prestamo.getSocio().getApellido() %></p>
				<p><%= prestamo.getFechaPrestamo() %></p>
				<div class="col-lg-12, col-sm-12, col-12">
					<div class="table-responsive">
						<table class="table table-light table-striped table-hover">
							<thead>
								<tr>
									<th>Libro</th>
									<th>Ejemplar</th>
									<th>Devolución teórica</th>
									<th>Devolución real</th>
									<th>Devolver</th>
								</tr>
							</thead>
							<tbody>
								<% for (LineaDePrestamo ldp : prestamo.getLineasDePrestamo()) {%>
								<tr>
									<td><%=ldp.getEjemplar().getLibro().getTitulo()%></td>
									<td><%=ldp.getEjemplar().getIdEjemplar()%></td>
									<td><%=ldp.getFechaDevolucionTeorica()%></td>
									<td><%=ldp.getFechaDevolucionReal() == null ? "Pendiente" : ldp.getFechaDevolucionReal()%></td>
									<td><button 
											type="submit" 
											name="idLineaDePrestamo" 
											value="<%=ldp.getIdLineaPrestamo()%>" 
											class="btn btn-primary"
											<%= (ldp.getFechaDevolucionReal() != null) ? "disabled" : "" %>
											>
											Devolver
										</button>
									</td>
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