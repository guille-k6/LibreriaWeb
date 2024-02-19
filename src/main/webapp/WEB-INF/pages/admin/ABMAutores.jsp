<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio"%>
<%@page import="Entities.Autor, Data.DataAutor"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Autores</title>

	<%
		Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
	 	String mensaje = (String)request.getAttribute("estado");
	 	
		LinkedList<Autor> autores = new LinkedList<Autor>();
	    DataAutor autlog = new DataAutor();
	    autores = autlog.getAll();		
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<p class="bienvenidoTitulo">ABM Autores.</p>
	<%if(mensaje != null){ %>
		<p class="mensajeInfo"><%=mensaje%></p>
	<%} %>	
	<form action="ABMAutoresForm" method="get">					
		<button type="submit" name="opcion" value="alta" class="btn btn-success mb-3">Añadir un autor</button>			
		<div class="container">
			<div class="row">
				<div class="col-lg-12, col-sm-12, col-12">
					<div class="table-responsive">
						<table class="table table-light table-striped table-hover">
							<thead>
								<tr>
									<th>ID</th>
									<th>Nombre</th>
									<th>Apellido</th>
									<th>Editar</th>
									<th>Eliminar</th>
								</tr>
							</thead>
							<tbody>
								<% for (Autor aut : autores) {%>
								<tr>
									<td><%=aut.getIdAutor() %></td>
									<td><%=aut.getNombre() %></td>
									<td><%=aut.getApellido() %></td>
									<td><button type="submit" name="editar" value="<%= aut.getIdAutor()%>" class="btn btn-primary">Editar</button></td>
									<td><button type="submit" name="eliminar" value="<%=aut.getIdAutor()%>" class="btn btn-danger">Eliminar</button></td>
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