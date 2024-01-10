<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio"%>
<%@page import="Logic.SocioLogic"%>
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
    
<title>Busque un socio</title>

	<%
		Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
	 	String mensaje = (String)request.getAttribute("estado");	 	
		LinkedList<Socio> socios = new LinkedList<Socio>();
		
		socios = (LinkedList<Socio>)request.getAttribute("sociosApellido");
		if(socios==null || socios.isEmpty()){
		    SocioLogic soclog = new SocioLogic();
		    socios = soclog.getAll();		
		}
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

<div class="container">
		<p class="loginTitle">Busque un socio por su apellido.</p>
		<div class="row justify-content-center">
			<div class="col-lg-3 col-xl-3 ">
				<form class="BusquedaSocioForm" action="BusquedaSocioForm" method="post">
					<label for="inp" class="inp">
					  <input type="text" name="apellido" id="inp" placeholder="&nbsp;">
					  <span class="label">Apellido</span>
					  <span class="focus-bg"></span>
					</label>					
				  <button type="submit" class="btn btn-primary botonLogin">Buscar</button>
				</form>		
			</div>	
		</div>

	</div>

<div class="container">
	<p class="bienvenidoTitulo">Elija un socio</p>
	<%if(mensaje != null){ %>
		<p class="mensajeInfo"><%=mensaje%></p>
	<%} %>	
	<form action="BusquedaSocioForm" method="get">								
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
									<th>Telefono</th>
									<th>Estado</th>
								</tr>
							</thead>
							<tbody>
								<% for (Socio soc : socios) {%>
								<tr>
									<td><%=soc.getIdSocio() %></td>
									<td><%=soc.getNombre() %></td>
									<td><%=soc.getApellido() %></td>
									<td><%=soc.getTelefono() %></td>
									<td><%=soc.getEstadoSocio() %></td>
									<td><button type="submit" name="elegir" <%= (soc.getEstadoSocio().equals("Sancionado")) ? "disabled" : "" %> value="<%=soc.getIdSocio()%>" class="btn btn-primary">Elegir</button></td>
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