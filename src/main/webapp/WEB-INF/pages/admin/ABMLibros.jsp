<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio"%>
<%@page import="Entities.Libro, Data.DataLibro"%>
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
	 	
		LinkedList<Libro> libros = new LinkedList<Libro>();
	    DataLibro liblog = new DataLibro();
	    libros = liblog.getAll();		
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<p class="bienvenidoTitulo">ABM Libros.</p>
	<%if(mensaje != null){ %>
		<p class="mensajeInfo"><%=mensaje%></p>
	<%} %>
	<form action="ABMLibrosForm" method="get">					
		<button type="submit" name="opcion" value="alta" class="btn btn-success mb-3">Añadir un libro</button>			
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
									<th>Fecha de edición</th>
									<th>Máximo tiempo de préstamo</th>
									<th>Autor</th>
								</tr>
							</thead>
							<tbody>
								<% for (Libro lib : libros) {%>
								<tr>
									<td><%=lib.getIdLibro() %></td>
									<td><%=lib.getIsbn() %></td>
									<td><%=lib.getTitulo() %></td>
									<td><%=lib.getEditorial() %></td>
									<td><%=lib.getFechaEdicion().toString() %></td>
									<td><%=lib.getCantDiasMaxPrestamo() %></td>
									<td><%=lib.getAutor().getNombre() + " " + lib.getAutor().getApellido() %></td>
									<td><button type="submit" name="editar" value="<%= lib.getIdLibro()%>" class="btn btn-primary">Editar</button></td>
									<td><button type="submit" name="eliminar" value="<%=lib.getIdLibro()%>" class="btn btn-danger">Eliminar</button></td>
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