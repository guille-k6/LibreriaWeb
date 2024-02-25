<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="HeadTags.jsp" %>
	<title>Menu principal</title>
	<%
 	  	Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMAutores.jsp").forward(request, response);
		}
		String mensaje = (String)request.getAttribute("mensaje");
	%>
</head>
<body style="display: flex; flex-direction: column; min-height: 100vh;">
<main>
<%@ include file="NavigationBar.jsp" %>
<div class="container">
	<p class="welcome-title mb-3">Bienvenido de nuevo, <%= c.getNombre() + " " + c.getApellido()%>.</p>
	<h3 class="welcome-second-title mt-3">Gestiona tu biblioteca</h3>
	<form action="menuAdmin" method="get">		
		<div class="row mt-3 menu-section-container">
			<div class="col-3">
				<p class="menu-title">Administrar entidades</p>
				<div class="section-column">
				    <button type="submit" name="opcion" value="abmAutores" class="opcionMenu">> Administrar autores</button>
				    <button type="submit" name="opcion" value="abmLibros" class="opcionMenu">> Administrar libros</button>
				    <button type="submit" name="opcion" value="abmEjemplares" class="opcionMenu">> Administrar ejemplares</button>			
				    <button type="submit" name="opcion" value="abmPoliticaPrestamos" class="opcionMenu">> Administrar politica de prestamo</button>
				</div>
			</div>
			<div class="col-3">
				<p class="menu-title">Administrar pagos</p>
				<div class="section-column">
				    <button type="submit" name="opcion" value="cobrarCuotas" class="opcionMenu">> Cobrar Cuotas</button>
				</div>
			</div>
			<div class="col-3">
				<p class="menu-title">Administrar prestamos</p>
				<div class="section-column">
		    	    <button type="submit" name="opcion" value="buscarSocio" class="opcionMenu">> Efectuar prestamo</button>
   					<button type="submit" name="opcion" value="verPrestamos" class="opcionMenu">> Adminitrar prestamos</button>
				</div>
			</div>
			<div class="col-3">
				<p class="menu-title">General</p>
				<div class="section-column">
		    	    <button type="submit" name="opcion" value="verErrores" class="opcionMenu">> Registro de errores</button>
				</div>
			</div>
		</div>
	</form> 
	<%if(mensaje != null){ %>
		<p hidden class="mensajeInfo"><%=mensaje%></p>
	<%} %>	
</div>
</main>
<%@ include file="FooterTags.jsp" %>


</body>
<%mensaje = null; %>
</html>