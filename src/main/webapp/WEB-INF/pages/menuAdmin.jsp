<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name=description content="Trabajo práctico Java. Sistema de gestión de una librería.">
    <meta name=keywords content="library">
    <!-- Bootstrap 5.2 CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
 	<!-- local styles -->
    <link rel="stylesheet" type="text/css" href="css/style.css">
    
<title>Menú principal</title>

	<%
 	  	Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMAutores.jsp").forward(request, response);
		}
	%>
</head>
<body>

<form action="headerForm" method="post">
	<div class="contenedorNavBar">
		<div class="d-flex flex-row justify-content-start align-items-stretch">
			<div class="navBarItem navBarItem-Main">JAVAUTNFRRO2022</div>
			<div class="navBarItem"><button type="submit" name="opcion" value="menu" class="botonMenu">Menu</button></div>
			<div class="navBarItem ms-auto opcionLogout">Usuario: <%=c.getUsuario() %> <button class="btn btn-danger" type="submit" name="opcion" value="logout">Logout</button></div>
		</div>
	</div>
</form>


<div class="container">
	<p class="bienvenidoTitulo">Bienvenido, <%= c.getNombre() %> admin</p>
	<form action="menuAdmin" method="get">		
		<h3>MENU PRINCIPAL</h3>		
		<button type="submit" name="opcion" value="abmAutores" class="opcionMenu">ABM Autores</button>
	       <button type="submit" name="opcion" value="abmLibros" class="opcionMenu">ABM Libros</button>
	       <button type="submit" name="opcion" value="abmEjemplares" class="opcionMenu">ABM Ejemplares</button>
	       <button type="submit" name="opcion" value="cobrarCuotas" class="opcionMenu">Cobrar Cuotas</button>
	       <button type="submit" name="opcion" value="prestarEjemplar" class="opcionMenu">Prestar Ejemplar</button>
	       <button type="submit" name="opcion" value="buscarSocio" class="opcionMenu">Buscar Socio</button>
	</form> 
</div>

</body>
</html>