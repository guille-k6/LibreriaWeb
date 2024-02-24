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
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.jsp").forward(request, response);		
		}
		Socio alquila = (Socio)request.getAttribute("socioDeudor");
	 	String mensaje = (String)request.getAttribute("estado");	
	    LinkedList<EjemplarCantidad> lib = (LinkedList<EjemplarCantidad>)request.getAttribute("librosCantidad");
	    LocalDate currentDate = LocalDate.now();
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>

	<div class="container">
		<form action="breadcrumb" method="get">
			<nav aria-label="breadcrumb">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><button type="submit" name="page" value="menuAdmin.jsp" class="button-emula-anchor">Home</button></li>
			    <li class="breadcrumb-item"><button type="submit" name="page" value="admin/BuscarSocio.jsp" class="button-emula-anchor">Elegir socio</button></li>
			    <li class="breadcrumb-item active" aria-current="page">Confirmar</li>
			  </ol>
			</nav>
		</form>
		<form action="confirmarPrestamo" method="post">								
			<div class="container">
				<div class="row">
					<div class="welcome-title mt-2">Confirmar prestamo</div>
					<div class="col-lg-12, col-sm-12, col-12">
						<div class="d-flex py-3" style="gap:16px">
							<div class="input-group mb-3 w-25">
							  <span class="input-group-text fw-semibold">Fecha</span>
							  <div aria-label="First name" class="form-control"><%=currentDate%></div>
							</div>
							<div class="input-group mb-3 w-25">
							  <span class="input-group-text fw-semibold">Socio</span>
							  <div aria-label="First name" class="form-control"><%=alquila.getNombre() + " " + alquila.getApellido()%></div>
							</div>
						</div>
						<div class="libros-pedidos-title">Libros pedidos</div>
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
							<button type="submit" name="cancelar" value="cancelar" class="btn btn-outline-secondary btn-md m-2">Cancelar</button>
							<button type="submit" name="aceptar" value="aceptar" class="btn btn-primary btn-md m-2">Confirmar</button>
						</div>
						</div>
					</div>
				</div>
			</div>
		</form> 
	<%if(mensaje != null){ %>
		<p hidden class="mensajeInfo"><%=mensaje%></p>
	<%} %>	
	</div>
</body>
</html>