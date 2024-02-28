<%@page import="java.util.LinkedList, Entities.Socio, Logic.CuotasLogic, java.util.LinkedList"%>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Elegir socio</title>
	<%
		Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.jsp").forward(request, response);		
		}
	 	String mensaje = (String)request.getAttribute("estado");
	 	CuotasLogic cuolog = new CuotasLogic();
		LinkedList<Socio> socios = cuolog.getSocioAConfirmar();
	%>
</head>
<body style="display: flex; flex-direction: column; min-height: 100vh;"><main>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<form action="breadcrumb" method="get">
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><button type="submit" name="page" value="menuAdmin.jsp" class="button-emula-anchor">Home</button></li>
		    <li class="breadcrumb-item active" aria-current="page">Socio</li>
		  </ol>
		</nav>
	</form>
	<p class="welcome-title mx-3">Elegir socio a cobrar</p>
	<form action="UsuariosAConfirmarForm" method="get" class="mt-3">								
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
									<th>Prestar</th>
								</tr>
							</thead>
							<tbody>
								<% for (Socio soc : socios) {%>
								<tr>
									<td><%=soc.getIdSocio() %></td>
									<td><%=soc.getNombre() %></td>
									<td><%=soc.getApellido() %></td>
									<td><%=soc.getTelefono() %></td>
									<td><button type="submit" name="idSocioCobrar" value="<%=soc.getIdSocio()%>" class="btn btn-primary py-1">Elegir</button></td>
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

</main><%@ include file="../FooterTags.jsp" %></body>
<script src="js/eliminarMensajes.js"></script>
</html>