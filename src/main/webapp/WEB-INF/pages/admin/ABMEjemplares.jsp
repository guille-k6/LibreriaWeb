<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio"%>
<%@page import="Entities.Ejemplar, Logic.EjemplarLogic"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Ejemplares</title>

	<%
		Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
	 	String mensaje = (String)request.getAttribute("estado");
	 	
		LinkedList<Ejemplar> ejemplares = new LinkedList<Ejemplar>();
	    EjemplarLogic ejelog = new EjemplarLogic();
	    ejemplares = ejelog.getAll();		
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<p class="bienvenidoTitulo">ABM Ejemplares.</p>
	<%if(mensaje != null){ %>
		<p class="mensajeInfo"><%=mensaje%></p>
	<%} %>	
	<form action="ABMEjemplaresForm" method="get">					
		<button type="submit" name="opcion" value="alta" class="btn btn-success mb-3">Añadir un ejemplar</button>			
		<div class="container">
			<div class="row">
				<div class="col-lg-12, col-sm-12, col-12">
					<div class="table-responsive">
						<table class="table table-light table-striped table-hover">
							<thead>
								<tr>
									<th>ID</th>
									<th>Libro</th>
									<th>Autor</th>
									<th>Editar</th>
									<th>Eliminar</th>
								</tr>
							</thead>
							<tbody>
								<% for (Ejemplar eje : ejemplares) {%>
								<tr>
									<td><%=eje.getIdEjemplar() %></td>
									<td><%=eje.getLibro().getTitulo() %></td>
									<td><%=eje.getLibro().getAutor().getApellido() + " " + eje.getLibro().getAutor().getNombre() %></td>
									<td><button type="submit" name="editar" value="<%= eje.getIdEjemplar()%>" class="btn btn-primary">Editar</button></td>
									<td><button type="submit" name="eliminar" value="<%=eje.getIdEjemplar()%>" class="btn btn-danger">Eliminar</button></td>
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