<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio, Entities.Libro, Entities.Autor, Logic.AutorLogic"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Modificar libro</title>
	<%
	  	Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
		Libro libro = (Libro)request.getAttribute("libroModificar");
		AutorLogic autlog = new AutorLogic();
		List<Autor> autores = autlog.getAll();
		LinkedList<String> errores = (LinkedList<String>)request.getAttribute("listaErrores");
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<form action="breadcrumb" method="get">
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><button type="submit" name="page" value="menuAdmin.jsp" class="button-emula-anchor">Home</button></li>
		    <li class="breadcrumb-item" aria-current="page"><button type="submit" name="page" value="admin/ABMLibros.jsp" class="button-emula-anchor">Libros</button></li>
		    <li class="breadcrumb-item active" aria-current="page">Modificar</li>
		  </ol>
		</nav>
	</form>
	
	<p class="welcome-title mt-3">Modificar un libro</p>
	<%if(!(errores == null)){
	for (String error : errores) {%>
	<p class="errorMensaje"><%=error %></p>
	<%}};%>
	
	<form action="modificarLibro" method="post" class="w-50 mt-3">
	
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">&emsp; ID &emsp;</span>
		  <input type="number" name="id" value="<%=libro.getIdLibro()%>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" readonly>
		</div>
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">ISBN</span>
		  <input type="text" name="isbn" value="<%=libro.getIsbn()%>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Nombre</span>
		  <input type="text" name="titulo" value="<%=libro.getTitulo()%>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>	
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Editorial</span>
		  <input type="text" name="editorial" value="<%=libro.getEditorial()%>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Fecha edición</span>
		  <input type="date"  name="fechaEdicion" value="<%=libro.getFechaEdicion().toString()%>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>		
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Máximo tiempo de préstamo (días)</span>
		  <input type="number" name="maxDias" value="<%=libro.getCantDiasMaxPrestamo()%>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
		</div>
	    
	    <div class="input-group mb-3">
		  <label class="input-group-text" for="inputGroupSelect01">Autor</label>
		  <select name="autor" class="form-select" id="inputGroupSelect01">
			<% for (Autor aut : autores){ %>
	    	<option value="<%=aut.getIdAutor()%>" <%if(aut.getIdAutor() == libro.getAutor().getIdAutor() ){%> selected <% } %>> <%=aut.getNombre()%>  <%=aut.getApellido()%> </option>
	    	<%} %>
		  </select>
		</div>
				
		<button type="submit" name="opcion" value="editar" class="btn btn-primary mt-2 px-4">Modificar</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-outline-secondary mt-2">Cancelar</button>
	</form> 
</div>

</body>
<script src="js/eliminarErrores.js"></script>
</html>