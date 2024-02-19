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
	<p class="bienvenidoTitulo">Bienvenido, <%= c.getNombre() %> admin</p>
	<form action="menuAdmin" method="get">		
		<h3>MENU PRINCIPAL</h3>
		<%if(message != null){ %>
		<div id="toast">
			<div id="img"></div>
			<div id="desc"><%=message%></div>
		</div>	
		<%} %>
		
		<button type="submit" name="opcion" value="abmAutores" class="opcionMenu">ABM Autores</button>
	       <button type="submit" name="opcion" value="abmLibros" class="opcionMenu">ABM Libros</button>
	       <button type="submit" name="opcion" value="abmPoliticaPrestamos" class="opcionMenu">ABM Politica Prestamos</button>
	       <button type="submit" name="opcion" value="abmEjemplares" class="opcionMenu">ABM Ejemplares</button>
	       <button type="submit" name="opcion" value="cobrarCuotas" class="opcionMenu">Cobrar Cuotas</button>
	       <button type="submit" name="opcion" value="buscarSocio" class="opcionMenu">Buscar Socio</button>
	       <button type="submit" name="opcion" value="verPrestamos" class="opcionMenu">Ver prestamos</button>
	</form> 
</div>

</body>
<script src="js/toast.js"></script>
<%message = null; %>
</html>