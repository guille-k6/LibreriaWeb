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

<div class="container pt-3">

	<form action="headerForm" method="post">
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><button type="submit" name="opcion" value="menu" class="button-emula-anchor">Home</button></li>
		    <li class="breadcrumb-item active" aria-current="page">Ejemplares</li>
		  </ol>
		</nav>
	</form>
	
	<form action="ABMEjemplaresForm" method="get">					
		<div class="w-100 d-flex justify-content-between align-items-center mx-3">
			<p class="welcome-title">Administrar ejemplares.</p>
			<button type="submit" name="opcion" value="alta" class="btn btn-success boton-nuevo">Añadir ejemplar</button>			
		</div>		
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
									<td><button type="submit" name="editar" value="<%=eje.getIdEjemplar()%>" class="boton-editar"><i class="tiny material-icons">edit</i></button></td>
									<td><button type="submit" name="eliminar" value="<%=eje.getIdEjemplar()%>" class="boton-eliminar"><i class="tiny material-icons">delete</i></button></td>
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