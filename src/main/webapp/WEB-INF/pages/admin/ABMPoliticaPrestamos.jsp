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
	<title>Politica de prestamo</title>
	
	<%
		Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
	 	String mensaje = (String)request.getAttribute("estado");
	    DataPoliticaPrestamo pollog = new DataPoliticaPrestamo();
	    List<PoliticaPrestamo> PoliticaPrestamos = pollog.getAll();		
	%>
</head>
<body style="display: flex; flex-direction: column; min-height: 100vh;">

<%@ include file="../NavigationBar.jsp" %>

<div class="container pt-3">

	<form action="headerForm" method="post">
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><button type="submit" name="opcion" value="menu" class="button-emula-anchor">Home</button></li>
		    <li class="breadcrumb-item active" aria-current="page">Política de prestamo</li>
		  </ol>
		</nav>
	</form>
	<%if(mensaje != null){ %>
		<p class="mensajeInfo"><%=mensaje%></p>
	<%} %>	
	<form action="ABMPoliticaPrestamosForm" method="get">					
		<div class="w-100 d-flex justify-content-between align-items-center mx-3">
			<p class="welcome-title">Administrar políticas de prestamo.</p>
			<button type="submit" name="opcion" value="alta" class="btn btn-success boton-nuevo">Añadir política de prestamo</button>			
		</div>	
		<div class="container">
			<div class="row">
				<div class="col-lg-12, col-sm-12, col-12">
					<div class="table-responsive">
						<table class="table table-light table-striped table-hover">
							<thead>
								<tr>
									<th>ID</th>
									<th>Fecha Desde</th>
									<th>Máximos libros pendientes</th>
									<th>Editar</th>
									<th>Eliminar</th>
								</tr>
							</thead>
							<tbody>
								<% for (PoliticaPrestamo pol : PoliticaPrestamos) {%>
								<tr>
									<td><%=pol.getIdPoliticaPrestamo() %></td>
									<td><%=pol.getFechaDesde().toString() %></td>
									<td><%=pol.getCantMaxLibrosPend() %></td>
									<td><button type="submit" name="editar" value="<%= pol.getIdPoliticaPrestamo()%>" class="boton-editar"><i class="tiny material-icons">edit</i></button></td>
									<td><button type="submit" name="eliminar" value="<%=pol.getIdPoliticaPrestamo()%>" class="boton-eliminar"><i class="tiny material-icons">delete</i></button></td>
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