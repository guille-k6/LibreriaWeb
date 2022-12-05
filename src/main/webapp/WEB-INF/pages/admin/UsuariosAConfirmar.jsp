<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio"%>
<%@page import="Logic.CuotasLogic"%>
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
<title>Autores</title>

	<%
		Socio c = (Socio)session.getAttribute("usuario");
	 	String mensaje = (String)request.getAttribute("estado");
	 	CuotasLogic cuolog = new CuotasLogic();
		LinkedList<Socio> socios = new LinkedList<Socio>();
	    socios = cuolog.getSocioAConfirmar();		
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
	<h3>Pago. <%= c.getNombre() %> admin</h3>
	<%if(mensaje != null){ %>
		<h4><%=mensaje%></h4>
	<%} %>	
	<form action="UsuariosAConfirmarForm" method="get">							
		<div class="container">
			<div class="row">
				<div class="col-lg-12, col-sm-12, col-12">
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th>ID</th>
									<th>Apellido</th>
									<th>Nombre</th>
								</tr>
							</thead>
							<tbody>
								<% for (Socio soc : socios) {%>
								<tr>
									<td><%=soc.getIdSocio() %></td>
									<td><%=soc.getApellido() %></td>
									<td><%=soc.getNombre() %></td>
									<td><button type="submit" name="pagar" value="<%= soc.getIdSocio()%>" class="input-button">Cobrar</button></td>
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