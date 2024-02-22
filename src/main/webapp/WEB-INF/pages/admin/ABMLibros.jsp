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

<div class="container pt-3">

	<form action="headerForm" method="post">
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><button type="submit" name="opcion" value="menu" class="button-emula-anchor">Home</button></li>
		    <li class="breadcrumb-item active" aria-current="page">Libros</li>
		  </ol>
		</nav>
	</form>
	
	<form action="ABMLibrosForm" method="get">					
		<div class="w-100 d-flex justify-content-between align-items-center mx-3">
			<p class="welcome-title">Administrar libros.</p>
			<button type="submit" name="opcion" value="alta" class="btn btn-success boton-nuevo">Añadir libro</button>			
		</div>	
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
									<th>Máx. prestamo</th>
									<th>Autor</th>
									<th>Editar</th>
									<th>Eliminar</th>
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
									<td><%=lib.getCantDiasMaxPrestamo() %> días</td>
									<td><%=lib.getAutor().getNombre() + " " + lib.getAutor().getApellido() %></td>
									<td><button type="submit" name="editar" value="<%= lib.getIdLibro()%>" class="boton-editar"><i class="tiny material-icons">edit</i></button></td>
									<td><button type="submit" name="eliminar" value="<%=lib.getIdLibro()%>" class="boton-eliminar"><i class="tiny material-icons">delete</i></button></td>
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
		<p hidden class="mensajeInfo"><%=mensaje%></p>
	<%} %>
</div>
</body>
<script src="js/eliminarMensajes.js"></script>
</html>