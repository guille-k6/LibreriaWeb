<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio"%>
<%@page import="Logic.SocioLogic"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Busque un socio</title>
	<%
		Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
	 	String mensaje = (String)request.getAttribute("estado");	 	
	 	SocioLogic socioLogic = new SocioLogic();
		List<Socio> socios = socioLogic.getAll();
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<h1 class="loginTitle">Busque un socio por su apellido.</h1>
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