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
 	
<title>Confirmar el pago de las cuotas</title>

	<%
		Socio c = (Socio)session.getAttribute("usuario");	
		if(c.getAdmin()){
			response.sendRedirect("WEB-INF/pages/menuAdmin.jsp");
			return;
		}  	
		
		String[] lasCuotas = (String[])request.getAttribute("cuotasPagar");			
		LinkedList<Cuotas> cuotasAMostrar = new LinkedList<Cuotas>();
		CuotasLogic cuolog = new CuotasLogic();
		ValorCuotasLogic valcuolog = new ValorCuotasLogic();
		double costoPorCuota = valcuolog.getValorActual();
		double costoTotal = 0;
		
		for(String elIdCuota : lasCuotas){
			int elId = Integer.parseInt(elIdCuota);
			Cuotas cuota = new Cuotas();
			cuota.setIdCuota(elId);
			cuota = cuolog.getOneById(cuota);
			cuotasAMostrar.add(cuota);
		}
		
		for(Cuotas cuota : cuotasAMostrar){
			if(cuota.getEstado().equals("Pago atrasado")){
				costoTotal += costoPorCuota*1.5;
			}else if(cuota.getEstado().equals("Pendiente")){
				costoTotal += costoPorCuota;
			};
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
	<p class="bienvenidoTitulo">Confirmar pago de cuotas.</p>

	<form action="ConfirmarPagoCuotas" method="post" class="w-50">							
		<div class="container">
			<div class="row">
				<div class="col-lg-12, col-sm-12, col-12">
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th>Fecha desde</th>
									<th>Fecha hasta</th>
									<th>Estado</th>								
								</tr>
							</thead>
							<tbody>
								<% for (Cuotas cuo : cuotasAMostrar) {%>
								<tr>
									<td><%=cuo.getFechaDesde().toString() %></td>
									<td><%=cuo.getFechaHasta().toString() %></td>
									<td><%=cuo.getEstado()%></td>
									<td><input type="checkbox" name="idcheck" value="<%=cuo.getIdCuota()%>" checked hidden></td>
								</tr>
								<% }%>
							</tbody>
						</table>
						<h3>El total a pagar es de: <%=costoTotal %></h3>
						<button type="submit" name="opcion" value="pagar" class="btn btn-success mt-3">Pagar</button>	
						<button type="submit" name="opcion" value="cancelar" class="btn btn-danger mt-3">Cancelar</button>	
					</div>
				</div>
			</div>
		</div>
	</form>  
</div>

</body>
</html>