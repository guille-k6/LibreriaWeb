<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Socio, Logic.CuotasLogic, Entities.Cuotas, Logic.ValorCuotasLogic"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>  
	<title>Pagar cuotas</title>
	<%	
		Socio c = (Socio)session.getAttribute("usuario");	
		CuotasLogic cuolog = new CuotasLogic();
		LinkedList<Cuotas> lasCuotas = cuolog.getCuotasImpagasByUser(c);
		ValorCuotasLogic valcuolog = new ValorCuotasLogic();
		double valorCuotas = valcuolog.getValorActual();
		String error = (String)request.getAttribute("error");	
	%>
</head>
<body style="display: flex; flex-direction: column; min-height: 100vh;"><main>

<%@ include file="../NavigationBar.jsp" %>

<div class="container pt-3">

	<form action="headerForm" method="post">
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><button type="submit" name="opcion" value="menu" class="button-emula-anchor">Home</button></li>
		    <li class="breadcrumb-item active" aria-current="page">Pagar</li>
		  </ol>
		</nav>
	</form>
	
	<p class="welcome-title">Elegir cuotas para pagar</p>
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
									<th>Pagar</th>									
								</tr>
							</thead>
							<tbody>
								<% for (Cuotas cuo : lasCuotas) {%>
								<tr>
									<td><%=cuo.getFechaDesde().toString() %></td>
									<td><%=cuo.getFechaHasta().toString() %></td>
									<td><%=valorCuotas%></td>
									<td><%=cuo.getEstado() %></td>
									<td><input class="form-check-input mt-0" type="checkbox" name="idcheck" value="<%=cuo.getIdCuota() %>"></td>
								</tr>
								<% }%>
							</tbody>
						</table>
						<div class="" style="width: max-content; margin-left: auto; margin-right: 1rem">
							<button type="submit" name="opcion" value="cancelar" class="btn btn-outline-secondary mt-2">Cancelar</button>	
							<button type="submit" name="opcion" value="pagar" class="btn btn-primary mt-2 px-4">Pagar</button>	
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>  
	<%if(!(error == null)){%>
	<p hidden class="mensajeInfo"><%=error %></p>
	<%};%>
</div>

</main><%@ include file="../FooterTags.jsp" %></body>
<script src="js/eliminarErrores.js"></script>
</html>