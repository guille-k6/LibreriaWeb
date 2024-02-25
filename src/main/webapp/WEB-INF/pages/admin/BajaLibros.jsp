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
			request.getRequestDispatcher("index.jsp").forward(request, response);		
		}
		Libro libro = (Libro)request.getAttribute("libroBaja");
	%>
</head>
<body style="display: flex; flex-direction: column; min-height: 100vh;">

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<form action="breadcrumb" method="get">
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><button type="submit" name="page" value="menuAdmin.jsp" class="button-emula-anchor">Home</button></li>
		    <li class="breadcrumb-item" aria-current="page"><button type="submit" name="page" value="admin/ABMLibros.jsp" class="button-emula-anchor">Libros</button></li>
		    <li class="breadcrumb-item active" aria-current="page">Baja</li>
		  </ol>
		</nav>
	</form>
	
	<p class="welcome-title mt-3">Dar de baja un libro</p>
	<form action="bajaLibro" method="post" class="w-50 mt-3">
	
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">ID</span>
		  <input type="text" name="id" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="<%=libro.getIdLibro()%>" readonly>
		</div>	
	
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">ISBN</span>
		  <input type="text" name="isbn" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="<%=libro.getIsbn()%>" readonly value="<%=libro.getIdLibro()%>" readonly>
		</div>
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Nombre</span>
		  <input type="text" name="titulo" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="<%=libro.getTitulo()%>" readonly>
		</div>
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Editorial</span>
		  <input type="text" name="editorial" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="<%=libro.getEditorial()%>" readonly>
		</div>
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Fecha edición</span>
		  <input type="date" name="fechaEdicion" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="<%=libro.getFechaEdicion().toString()%>" readonly>
		</div>
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Máximo tiempo de préstamo (días)</span>
		  <input type="number" name="maxDias" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="<%=libro.getCantDiasMaxPrestamo()%>" readonly>
		</div>	
		
		<div class="input-group mb-3">
		  <span class="input-group-text" id="inputGroup-sizing-default">Autor</span>
		  <input type="text" name="autor" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="<%=libro.getAutor().getNombre()%> <%=libro.getAutor().getApellido()%>" readonly>
		</div>
				
		<button type="submit" name="opcion" value="eliminar" class="btn btn-primary mt-2 px-4">Eliminar</button>
        <button type="submit" name="opcion" value="cancelar" class="btn btn-outline-secondary mt-2">Cancelar</button>
	</form> 
</div>

</body>
</html>