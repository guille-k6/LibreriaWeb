<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio"%>
<%@page import="Entities.Ejemplar, Logic.EjemplarLogic"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name=description content="Trabajo práctico Java. Sistema de gestión de una librería.">
    <meta name=keywords content="library">
    <!-- Bootstrap 5.2 CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
 	<!-- local styles -->
<title>Ejemplares</title>

	<%
		Socio c = (Socio)session.getAttribute("usuario");
	 	String mensaje = (String)request.getAttribute("estado");
	 	
		LinkedList<Ejemplar> ejemplares = new LinkedList<Ejemplar>();
	    EjemplarLogic ejelog = new EjemplarLogic();
	    ejemplares = ejelog.getAll();		
	%>
</head>
<body>

<nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <div class="navbar-nav">
        <a class="nav-link active" aria-current="page" href="#">Home</a>
        <a class="nav-link" href="#">Features</a>
        <a class="nav-link" href="#">Pricing</a>
        <a class="nav-link disabled">Disabled</a>
      </div>
    </div>
  </div>
</nav>
	<h3>Ejemplares. <%= c.getNombre() %> admin</h3>
	<%if(mensaje != null){ %>
		<h4><%=mensaje%></h4>
	<%} %>	
	<form action="ABMEjemplaresForm" method="get">					
		<button type="submit" name="opcion" value="alta" class="input-button">Añadir un ejemplar</button>			
		<div class="container">
			<div class="row">
				<div class="col-lg-12, col-sm-12, col-12">
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th>ID</th>
									<th>Libro</th>
								</tr>
							</thead>
							<tbody>
								<% for (Ejemplar eje : ejemplares) {%>
								<tr>
									<td><%=eje.getIdEjemplar() %></td>
									<td><%=eje.getLibro().getTitulo() + " " + eje.getLibro().getAutor().getApellido() %></td>
									<td><button type="submit" name="editar" value="<%= eje.getIdEjemplar()%>" class="input-button">Editar</button></td>
									<td><button type="submit" name="eliminar" value="<%=eje.getIdEjemplar()%>" class="input-button">Eliminar</button></td>
								</tr>
								<% }%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</form> 

</body>
</html>