<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio, Entities.Libro"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Baja libro</title>
	<%
	  	Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}
		Libro libro = (Libro)request.getAttribute("libroBaja");
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<p class="bienvenidoTitulo">Baja de un libro.</p>
	<form action="bajaLibro" method="post" class="w-50">
	
		<label for="id">Id del libro:</label> <br>
		<input type="text" class="form-control" name="id" value="<%=libro.getIdLibro()%>" readonly> <br>
		
		<label for="nombre">ISBN del libro:</label> <br>
		<input type="text" class="form-control" name="isbn" value="<%=libro.getIsbn()%>" readonly> <br>
		
		<label for="apellido">Título del libro:</label> <br>
		<input type="text" class="form-control" name="titulo" value="<%=libro.getTitulo()%>" readonly> <br>		
		
		<label for="apellido">Editorial del libro:</label> <br>
		<input type="text" class="form-control" name="editorial" value="<%=libro.getEditorial()%>" readonly> <br>	
		
		<label for="apellido">Fecha de edición del libro:</label> <br>
		<input type="text" class="form-control" name="fechaEdicion" value="<%=libro.getFechaEdicion().toString()%>" readonly> <br>
		
		<label for="apellido">Días máximos permitidos para prestar del libro:</label> <br>
		<input type="text" class="form-control" name="maxDias" value="<%=libro.getCantDiasMaxPrestamo()%>" readonly> <br>
		
		<label for="apellido">Autor del libro:</label> <br>
		<input type="text" class="form-control" name="editorial" value="<%=libro.getAutor().getNombre()%> <%=libro.getAutor().getApellido()%>" readonly>
				
		<button type="submit" name="opcion" value="eliminar" class="btn btn-success mt-3">Eliminar libro</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-danger mt-3">Cancelar</button>
	</form> 
</div>

</body>
</html>