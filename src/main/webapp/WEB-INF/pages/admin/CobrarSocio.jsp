<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Socio, Logic.CuotasLogic, Entities.Cuotas, Logic.ValorCuotasLogic"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name=description content="Trabajo pr�ctico Java. Sistema de gesti�n de una librer�a.">
    <meta name=keywords content="library">
    <!-- Bootstrap 5.2 CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
 	<!-- local styles -->
 	<link rel="stylesheet" type="text/css" href="css/style.css">
 	
<title>Cobro de cuotas</title>

	<%
		Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.html").forward(request, response);		
		}	
		Socio socioACobrar = (Socio)request.getAttribute("socioACobrar");
		CuotasLogic cuolog = new CuotasLogic();
		LinkedList<Cuotas> lasCuotas = cuolog.getCuotasAConfirmarByUser(socioACobrar);
		ValorCuotasLogic valcuolog = new ValorCuotasLogic();
		double valorCuotas = valcuolog.getValorActual();
		String error = (String)request.getAttribute("error");
		

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
	<p class="bienvenidoTitulo">Cobrar cuota a un socio.</p>
	<%if(!(error == null)){%>
	<p class="errorMensaje"><%=error %></p>
	<%};%>

	<form action="cobrarCuotasForm" method="post" class="w-50">							
		<div class="container">
			<div class="row">
				<div class="col-lg-12, col-sm-12, col-12">
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th>Fecha desde</th>
									<th>Fecha hasta</th>
									<th>Precio</th>
									<th>Estado</th>
									<th>Cobrar?</th>									
								</tr>
							</thead>
							<tbody>
								<% for (Cuotas cuo : lasCuotas) {%>
								<tr>
									<td><%=cuo.getFechaDesde().toString() %></td>
									<td><%=cuo.getFechaHasta().toString() %></td>
									<td><%=valorCuotas%></td>
									<td><%=cuo.getEstado() %></td>
									<td><input type="checkbox" name="idcheck" value="<%=cuo.getIdCuota() %>"></td>
									<td><input type="checkbox" name="idcheckSocio" value="<%=cuo.getSocio().getIdSocio()%>" checked hidden></td>
								</tr>
								<% }%>
							</tbody>
						</table>
								<button type="submit" name="opcion" value="cobrar" class="btn btn-success mt-3">Cobrar seleccionadas</button>	
								<button type="submit" name="opcion" value="cancelar" class="btn btn-danger mt-3">Cancelar</button>	
					</div>
				</div>
			</div>
		</div>
		
	</form>  
</div>

</body>
<script src="js/eliminarErrores.js"></script>
</html>