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
 	<link rel="stylesheet" type="text/css" href="css/style.css">
 	
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

<form action="headerForm" method="post">
	<div class="contenedorNavBar">
		<div class="d-flex flex-row justify-content-start align-items-stretch">
			<div class="navBarItem navBarItem-Main">JAVAUTNFRRO2022</div>
			<div class="navBarItem"><button type="submit" name="opcion" value="menu" class="botonMenu">Menu</button></div>
			<div class="navBarItem ms-auto opcionLogout">Usuario: <%=c.getUsuario() %> <button class="btn btn-danger" type="submit" name="opcion" value="logout">Logout</button></div>
		</div>
	</div>
</form>

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