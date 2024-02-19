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
		LinkedList<Autor> autores = new LinkedList<Autor>();
		autores = autlog.getAll();
		LinkedList<String> errores = (LinkedList<String>)request.getAttribute("listaErrores");
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<p class="bienvenidoTitulo">Modificaci&oacute;n de un libro.</p>
	<%if(!(errores == null)){
	for (String error : errores) {%>
	<p class="errorMensaje"><%=error %></p>
	<%}};%>
	<form action="modificarLibro" method="post" class="w-50">
	
		<label for="id">Id del libro:</label> <br>
		<input type="text" class="form-control" name="id" value="<%=libro.getIdLibro()%>" readonly> <br>
		
		<label for="isbn">ISBN del libro:</label> <br>
		<input type="text" class="form-control" name="isbn" value="<%=libro.getIsbn()%>"> <br>
		
		<label for="titulo">Título del libro:</label> <br>
		<input type="text" class="form-control" name="titulo" value="<%=libro.getTitulo()%>"> <br>		
		
		<label for="editorial">Editorial del libro:</label> <br>
		<input type="text" class="form-control" name="editorial" value="<%=libro.getEditorial()%>"> <br>	
		
		<label for="fechaEdicion">Fecha de edición del libro:</label> <br>
		<input type="date" class="form-control" name="fechaEdicion" value="<%=libro.getFechaEdicion().toString()%>"> <br>
		
		<label for="maxDias">Días máximos permitidos para prestar del libro:</label> <br>
		<input type="text" class="form-control" name="maxDias" value="<%=libro.getCantDiasMaxPrestamo()%>"> <br>
		
		<label for="autor">Autor:</label> <br>
		<select name="autor" id="cars">
			<% for (Autor aut : autores){ %>
	    	<option value="<%=aut.getIdAutor()%>" <%if(aut.getIdAutor() == libro.getAutor().getIdAutor() ){%> selected <% } %>> <%=aut.getNombre()%>  <%=aut.getApellido()%> </option>
	    	<%} %>
	    </select>
	    <br>
				
		<button type="submit" name="opcion" value="editar" class="btn btn-success mt-3">Modificar libro</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-danger mt-3">Cancelar</button>
	</form> 
</div>

</body>
<script src="js/eliminarErrores.js"></script>
</html>