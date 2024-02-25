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
<body style="display: flex; flex-direction: column; min-height: 100vh;"><main>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<form action="breadcrumb" method="get">
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><button type="submit" name="page" value="menuAdmin.jsp" class="button-emula-anchor">Home</button></li>
		    <li class="breadcrumb-item"><button type="submit" name="page" value="admin/VerPrestamos.jsp" class="button-emula-anchor">Prestamos</button></li>
		    <li class="breadcrumb-item active" aria-current="page">Prestamo <%=prestamo.getIdPrestamo() %></li>
		  </ol>
		</nav>
	</form>
	<form action="devolverUnEjemplar" method="post">							
		<div class="container">
			<div class="welcome-title mt-4">Detalles del prestamo N°<%=prestamo.getIdPrestamo() %></div>
			<div class="d-flex pt-3" style="gap:16px">
				<div class="input-group mb-3 w-25">
				  <span class="input-group-text fw-semibold">Fecha</span>
				  <div aria-label="First name" class="form-control"><%=prestamo.getFechaPrestamo()%></div>
				</div>
				<div class="input-group mb-3 w-25">
				  <span class="input-group-text fw-semibold">Socio</span>
				  <div aria-label="First name" class="form-control"><%=prestamo.getSocio().getNombre() + " " + prestamo.getSocio().getApellido()%></div>
				</div>
			</div>
			<div class="row">
				<input type="hidden" name="prestamoId" value="<%=prestamo.getIdPrestamo()%>">
				<div class="col-lg-12, col-sm-12, col-12">
					<div class="libros-pedidos-title mb-2">Libros pedidos</div>
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
</main><%@ include file="../FooterTags.jsp" %></body>
<script src="js/eliminarMensajes.js"></script>
</html>