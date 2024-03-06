<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio"%>
<%@page import="Logic.SocioLogic"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Elegir socio</title>
	<%
		Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
	 	String mensaje = (String)request.getAttribute("mensaje");	 	
	 	SocioLogic socioLogic = new SocioLogic();
	 	String ultimaBusqueda = (String)request.getAttribute("ultimaBusqueda");
	 	if(ultimaBusqueda == null){
	 		ultimaBusqueda = "";
	 	}
	 	List<Socio> sociosBuscados = (List<Socio>)request.getAttribute("socios");
	 	if(sociosBuscados == null || sociosBuscados.size() == 0){
	 		sociosBuscados = socioLogic.getAll();	 		
	 	}

	%>
</head>
<body style="display: flex; flex-direction: column; min-height: 100vh;"><main>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<form action="breadcrumb" method="get">
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><button type="submit" name="page" value="menuAdmin.jsp" class="button-emula-anchor">Home</button></li>
		    <li class="breadcrumb-item active" aria-current="page">Elegir socio</li>
		  </ol>
		</nav>
	</form>

	<div class="w-100 d-flex justify-content-between align-items-center mx-3">
		<p class="welcome-title">Elija socio a prestar</p>
		<form action="buscarSocio" method="get" class="d-flex" style="gap: 4px">
			<div class="input-group mb-3">
				<span class="input-group-text" id="inputGroup-sizing-default">Socio</span>
				<input type="text" name="nombreSocio" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="<%=ultimaBusqueda%>">
				<button type="submit" class="btn btn-outline-danger" name="borrar" value="borrar" type="button" id="button-addon2">X</button>
			</div>
			<button type="submit" name="opcion" value="alta" class="btn btn-primary boton-nuevo" style="margin-bottom: 16px">Buscar</button>			
		</form>
	</div>	
	<form action="BusquedaSocioForm" method="get" class="mt-3">								
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
								<% for (Socio soc : sociosBuscados) {%>
								<tr>
									<td><%=soc.getIdSocio() %></td>
									<td><%=soc.getNombre() %></td>
									<td><%=soc.getApellido() %></td>
									<td><%=soc.getTelefono() %></td>
									<td><button type="submit" name="idSocio" value="<%=soc.getIdSocio()%>" class="btn btn-primary py-1">Elegir</button></td>
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