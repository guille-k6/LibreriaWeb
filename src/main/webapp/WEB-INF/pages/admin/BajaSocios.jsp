<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Baja Socio</title>
	<%
	  	Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.jsp").forward(request, response);		
		}
		Socio Socio = (Socio)request.getAttribute("SocioBaja");
	%>
</head>
<body style="display: flex; flex-direction: column; min-height: 100vh;"><main>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<form action="breadcrumb" method="get">
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><button type="submit" name="page" value="menuAdmin.jsp" class="button-emula-anchor">Home</button></li>
		    <li class="breadcrumb-item" aria-current="page"><button type="submit" name="page" value="admin/ABMSocios.jsp" class="button-emula-anchor">Socios</button></li>
		    <li class="breadcrumb-item active" aria-current="page">Baja</li>
		  </ol>
		</nav>
	</form>
	
	<p class="welcome-title mt-3">Dar de baja un Socio</p>
	<form action="bajaSocio" method="post" class="w-50 mt-3">
	
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">ID</span>
		  <input type="text" name="id" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="<%=Socio.getIdSocio()%>" readonly>
		</div>	
	
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Usuario</span>
		  <input type="text" name="usuario" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="<%=Socio.getUsuario()%>" readonly>
		</div>
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Nombre</span>
		  <input type="text" name="nombre" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="<%=Socio.getNombre()%>" readonly>
		</div>
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Apellido</span>
		  <input type="text" name="apellido" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="<%=Socio.getApellido()%>" readonly value="<%=Socio.getIdSocio()%>" readonly>
		</div>
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Email</span>
		  <input type="text" name="email" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="<%=Socio.getEmail()%>" readonly>
		</div>
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Domicilio</span>
		  <input type="text" name="domicilio" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="<%=Socio.getDomicilio()%>" readonly>
		</div>
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Telefono</span>
		  <input type="text" name="telefono" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="<%=Socio.getTelefono()%>" readonly>
		</div>	
				
		<button type="submit" name="opcion" value="eliminar" class="btn btn-primary mt-2 px-4">Eliminar</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-outline-secondary mt-2">Cancelar</button>
	</form> 
</div>

</main><%@ include file="../FooterTags.jsp" %></body>
</html>