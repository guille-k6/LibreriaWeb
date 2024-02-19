<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio, Entities.Autor, Logic.AutorLogic"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>  
	<title>Alta libro</title>
	<%
	  	Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
		AutorLogic autlog = new AutorLogic();
		LinkedList<Autor> autores = new LinkedList<Autor>();
		autores = autlog.getAll();
		LinkedList<String> errores = (LinkedList<String>)request.getAttribute("listaErrores");
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<p class="bienvenidoTitulo">Alta de un libro.</p>
	<%if(!(errores == null)){
	for (String error : errores) {%>
	<p class="errorMensaje"><%=error %></p>
	<%}};%>
	<form action="altaLibro" method="post" class="w-50">	
		<label for="nombre">ISBN:</label> <br>
		<input type="text" class="form-control" name="isbn"> <br>
		
		<label for="nombre">Titulo:</label> <br>
		<input type="text" class="form-control" name="titulo"> <br>
		
		<label for="nombre">Editorial:</label> <br>
		<input type="text" class="form-control" name="editorial"> <br>
		
		<label for="nombre">Fecha de edición:</label> <br>
		<input type="date" name="fechaEdicion" value="2022-11-23" min="1900-01-01" max="2023-01-01"><br><br>
		
		<label for="apellido">Máximo tiempo de préstamo (días):</label> <br>
		<input type="text" class="form-control" name="maxDias"> <br>
		
		<label for="autor">Autor:</label> <br>
		<select name="autor" id="cars">
			<% for (Autor aut : autores){ %>
	    	<option value="<%=aut.getIdAutor()%>"> <%=aut.getNombre()%>  <%=aut.getApellido()%> </option>
	    	<%} %>
	    </select>
	    <br>
			
		
		<button type="submit" name="opcion" value="crearLibro" class="btn btn-success mt-3">Crear</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-danger mt-3">Cancelar</button>
	</form> 
</div>

</body>
<script src="js/eliminarErrores.js"></script>
</html>