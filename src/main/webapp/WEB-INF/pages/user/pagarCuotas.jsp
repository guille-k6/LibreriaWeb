<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Socio, Logic.CuotasLogic, Entities.Cuotas, Logic.ValorCuotasLogic"%>
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
		//if(!c.getAdmin()){
		//	response.sendRedirect("WEB-INF/pages/menuUser.jsp");
		//}  	
		
		Socio c = (Socio)session.getAttribute("usuario");
		
		CuotasLogic cuolog = new CuotasLogic();
		LinkedList<Cuotas> lasCuotas = cuolog.getCuotasImpagasByUser(c);
		ValorCuotasLogic valcuolog = new ValorCuotasLogic();
		double valorCuotas = valcuolog.getValorActual();
		

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

	<h2>Bienvenido, <%= c.getNombre() %> noAdmin</h2>

	<form action="pagarCuotasForm" method="post">							
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
									<th>Pagar?</th>									
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
								</tr>
								<% }%>
							</tbody>
						</table>
								<button type="submit" name="opcion" value="pagar" class="input-button">Pagar seleccionadas</button>	
								<button type="submit" name="opcion" value="cancelar" class="input-button">Cancelar</button>	
					</div>
				</div>
			</div>
		</div>
		
	</form>  


</body>
</html>