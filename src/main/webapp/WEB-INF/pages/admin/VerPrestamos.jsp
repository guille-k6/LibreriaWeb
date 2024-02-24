<%@page import="java.util.LinkedList, java.util.List"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Prestamo, Logic.PrestamoLogic, Entities.Socio"%>
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
	 	String mensaje = (String)request.getAttribute("estado");
		PrestamoLogic pl = new PrestamoLogic();
	 	List<Prestamo> prestamos = pl.getAll();	
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<form action="headerForm" method="post">
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><button type="submit" name="opcion" value="menu" class="button-emula-anchor">Home</button></li>
		    <li class="breadcrumb-item active" aria-current="page">Prestamos</li>
		  </ol>
		</nav>
	</form>
	<p class="welcome-title mx-3">Administrar prestamos</p>
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
									<th>Estado</th>
									<th>Ver</th>
								</tr>
							</thead>
							<tbody>
								<% for (Prestamo p : prestamos) {%>
								<tr>
									<td><%=p.getIdPrestamo() %></td>
									<td><%=p.getFechaPrestamo()%></td>
									<td><%=p.getSocio().getNombre() + " " + p.getSocio().getApellido()%></td>
									<td><%=p.getEstado()%></td>
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
	<%if(mensaje != null){ %>
		<p class="mensajeInfo"><%=mensaje%></p>
	<%} %>
</div>
</body>
<script src="js/eliminarMensajes.js"></script>
</html>