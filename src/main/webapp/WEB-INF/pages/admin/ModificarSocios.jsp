<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio, Logic.SocioLogic"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Modificar Socio</title>
	<%
	  	Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
		SocioLogic socLogic = new SocioLogic();
		String idModificar = (String)request.getAttribute("idModificar");
		Socio socio = socLogic.getOneById(new Socio(Integer.parseInt(idModificar)));
		LinkedList<String> errores = (LinkedList<String>)request.getAttribute("listaErrores");
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
		    <li class="breadcrumb-item active" aria-current="page">Modificar</li>
		  </ol>
		</nav>
	</form>
	
	<p class="welcome-title mt-3">Modificar un Socio</p>
	<%if(!(errores == null)){
	for (String error : errores) {%>
	<p class="errorMensaje"><%=error %></p>
	<%}};%>
	
	<form action="modificarSocio" method="post" class="w-50 mt-3">
	
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">&emsp; ID &emsp;</span>
		  <input type="number" name="id" value="<%=socio.getIdSocio()%>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" readonly>
		</div>
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Apellido</span>
		  <input type="text" name="apellido" value="<%=socio.getApellido()%>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Nombre</span>
		  <input type="text" name="nombre" value="<%=socio.getNombre()%>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>	
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Telefono</span>
		  <input type="text" name="telefono" value="<%=socio.getTelefono()%>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Email</span>
		  <input type="text" name="email" value="<%=socio.getEmail()%>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Domicilio</span>
		  <input type="text" name="domicilio" value="<%=socio.getDomicilio()%>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Usuario</span>
		  <input type="text" name="usuario" value="<%=socio.getUsuario()%>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Contrase&#241;a</span>
		  <input type="password" name="contrasenia" value="" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>
		
		<div class="input-group mb-3">
		  <div class="input-group-text">
		  	<input class="form-check-input mt-0" type="checkbox" name="isadmin" aria-label="Checkbox for following text input" <%= socio.getAdmin() ? "checked" : "" %>>
		  </div>
		  <div class="form-control" aria-label="Text input with checkbox">Es administrador?</div>
		</div>
	
		<button type="submit" name="opcion" value="editar" class="btn btn-primary mt-2 px-4">Modificar</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-outline-secondary mt-2">Cancelar</button>
	</form> 
</div>

</main><%@ include file="../FooterTags.jsp" %></body>
<script src="js/eliminarErrores.js"></script>
</html>