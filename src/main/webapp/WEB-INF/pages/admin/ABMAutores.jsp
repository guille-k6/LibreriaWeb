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
<body style="display: flex; flex-direction: column; min-height: 100vh;"><main>

<%@ include file="../NavigationBar.jsp" %>

<div class="container pt-3">

	<form action="headerForm" method="post">
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><button type="submit" name="opcion" value="menu" class="button-emula-anchor">Home</button></li>
		    <li class="breadcrumb-item active" aria-current="page">Autores</li>
		  </ol>
		</nav>
	</form>
	
	<form action="ABMAutoresForm" method="get">	
		<div class="w-100 d-flex justify-content-between align-items-center mx-3">
			<p class="welcome-title">Administrar autores.</p>
			<button type="submit" name="opcion" value="alta" class="btn btn-success boton-nuevo">Añadir autor</button>			
		</div>				
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
									<td><button type="submit" name="editar" value="<%= aut.getIdAutor()%>" class="boton-editar"><i class="tiny material-icons">edit</i></button></td>
									<td><button type="submit" name="eliminar" value="<%=aut.getIdAutor()%>" class="boton-eliminar"><i class="tiny material-icons">delete</i></button></td>
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
</html>