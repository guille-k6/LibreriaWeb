<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio"%>
<%@page import="Entities.PoliticaPrestamo, Data.DataPoliticaPrestamo"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
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

<%@ include file="../NavigationBar.jsp" %>

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