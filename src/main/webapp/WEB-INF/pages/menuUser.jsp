<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Socio"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="HeadTags.jsp" %>
	<title>Menú principal</title>

	<%
	  	Socio c = (Socio)session.getAttribute("usuario");
		String mensaje = (String)request.getAttribute("estado");
	%>
</head>
<body>

<%@ include file="NavigationBar.jsp" %>

<div class="container">
	<p class="bienvenidoTitulo">Bienvenido, <%= c.getNombre() %>.</p>
	
	<h3>MENU PRINCIPAL</h3>		
	
	<form action="menuUser" method="get">		
		<button type="submit" name="opcion" value="verCuotasImpagas" class="opcionMenu">Ver cuotas impagas</button>
		<button type="submit" name="opcion" value="alquilarLibro" class="opcionMenu">Alquilar un libro</button>
	</form> 
</div>

</body>
</html>