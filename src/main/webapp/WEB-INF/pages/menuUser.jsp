<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Socio"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="HeadTags.jsp" %>
	<title>Menu principal</title>

	<%
	  	Socio c = (Socio)session.getAttribute("usuario");
		String mensaje = (String)request.getAttribute("estado");
	%>
</head>
<body style="display: flex; flex-direction: column; min-height: 100vh;"><main>
<%@ include file="NavigationBar.jsp" %>

<div class="container">
	<p class="welcome-title mb-3">Bienvenido de nuevo, <%= c.getNombre() + " " + c.getApellido()%>.</p>
	<h3 class="welcome-second-title mt-3">Menu principal</h3>	
	<form action="menuUser" method="get">
		<div class="row mt-3 menu-section-container">
			<div class="col-3">
				<p class="menu-title">Cuotas</p>
				<div class="section-column">
				    <button type="submit" name="opcion" value="verCuotasImpagas" class="opcionMenu">> Ver cuotas impagas</button>
				</div>
			</div>
			<div class="col-3">
				<p class="menu-title">Libros</p>
				<div class="section-column">
				    <button type="submit" name="opcion" value="verLibros" class="opcionMenu">> Ver libros disponibles</button>
				</div>
			</div>
		</div>		
	</form> 
</div>

</main><%@ include file="FooterTags.jsp" %></body>
</html>