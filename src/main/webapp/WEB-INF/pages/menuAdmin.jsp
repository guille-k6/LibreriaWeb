<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="HeadTags.jsp" %>
	<title>Menú principal</title>
	<%
 	  	Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMAutores.jsp").forward(request, response);
		}
		String message = (String)request.getAttribute("message");
	%>
</head>
<body>
<%@ include file="NavigationBar.jsp" %>
<div class="container">
	<p class="welcome-title">Bienvenido de nuevo, <%= c.getNombre() + " " + c.getApellido()%>.</p>
	<h3 class="welcome-second-title">Gestioná tu biblioteca</h3>
	<form action="menuAdmin" method="get">		
		<div class="d-flex mt-3 menu-section-container">
			<div class="col-md-4">
				<p class="menu-title">Administrar entidades</p>
				<div class="section-column">
				    <button type="submit" name="opcion" value="abmAutores" class="opcionMenu">> Administrar autores</button>
				    <button type="submit" name="opcion" value="abmLibros" class="opcionMenu">> Administrar libros</button>
				    <button type="submit" name="opcion" value="abmEjemplares" class="opcionMenu">> Administrar ejemplares</button>			
				    <button type="submit" name="opcion" value="abmPoliticaPrestamos" class="opcionMenu">> Administrar política de prestamo</button>
				</div>
			</div>
			<div class="col-md-4">
				<p class="menu-title">Administrar pagos</p>
				<div class="section-column">
				    <button type="submit" name="opcion" value="cobrarCuotas" class="opcionMenu">> Cobrar Cuotas</button>
				</div>
			</div>
			<div class="col-md-4">
				<p class="menu-title">Administrar prestamos</p>
				<div class="section-column">
		    	    <button type="submit" name="opcion" value="buscarSocio" class="opcionMenu">> Buscar Socio</button>
   					<button type="submit" name="opcion" value="verPrestamos" class="opcionMenu">> Ver prestamos</button>
				</div>
			</div>
		</div>
	</form> 
</div>

<%if(message != null){ %>
<div id="toast">
	<div id="img"></div>
	<div id="desc"><%=message%></div>
</div>	
<%} %>

</body>
<script src="js/toast.js"></script>
<%message = null; %>
</html>