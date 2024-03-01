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
			request.getRequestDispatcher("index.jsp").forward(request, response);		
		}
		String mensaje = (String)request.getAttribute("mensaje");
	 	EjemplarLogic ejLogic = new EjemplarLogic();
	 	String ultimaBusqueda = (String)request.getAttribute("ultimaBusqueda");
	 	if(ultimaBusqueda == null){
	 		ultimaBusqueda = "";
	 	}
	 	List<Ejemplar> ejemplaresBuscados = (List<Ejemplar>)request.getAttribute("ejemplares");
	 	if(ejemplaresBuscados == null || ejemplaresBuscados.size() == 0){
	 		ejemplaresBuscados = ejLogic.getAll();	 		
	 	}
	%>
</head>
<body style="display: flex; flex-direction: column; min-height: 100vh;"><main>

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
		<div class="d-flex align-items-center gap-1">
			<div class="w-100 d-flex justify-content-between align-items-center">
				<div class="d-flex " style="gap: 4px">
					<div class="input-group">
						<span class="input-group-text" id="inputGroup-sizing-default">Ejemplar</span>
						<input type="text" name="nombreEjemplar" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="<%=ultimaBusqueda%>">
						<button type="submit" name="opcion" value="buscar" class="btn btn-outline-primary px-1 py-1 d-flex align-items-center" type="button" id="button-addon2"><i class="material-icons tiny">search</i></button>
					</div>		
				</div>
			</div>	
			<button type="submit" name="opcion" value="alta" class="btn btn-success boton-nuevo">Nuevo</button>			
		</div>
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
								<% for (Ejemplar eje : ejemplaresBuscados) {%>
								<tr>
									<td><%=eje.getIdEjemplar() %></td>
									<td><%=eje.getLibro().getTitulo() %></td>
									<td><%=eje.getLibro().getAutor().getNombre() + " " + eje.getLibro().getAutor().getApellido()%></td>
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
</main><%@ include file="../FooterTags.jsp" %></body>
<script src="js/eliminarMensajes.js"></script>
</html>