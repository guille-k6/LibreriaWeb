<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Socio, Logic.CuotasLogic, Entities.Cuotas, Logic.ValorCuotasLogic"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>  
	<title>Confirmar el cobro de las cuotas</title>
	<%
		Socio c = (Socio)session.getAttribute("usuario");		
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.jsp").forward(request, response);		
		}
		CuotasLogic cuolog = new CuotasLogic();
		ValorCuotasLogic valcuolog = new ValorCuotasLogic();
		
		String[] lasCuotas = (String[])request.getAttribute("cuotasCobrar");	
		LinkedList<Cuotas> cuotasAMostrar = new LinkedList<Cuotas>();
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
				costoTotal += costoPorCuota;
		}
	%>
</head>
<body style="display: flex; flex-direction: column; min-height: 100vh;"><main>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<form action="breadcrumb" method="get">
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><button type="submit" name="page" value="menuAdmin.jsp" class="button-emula-anchor">Home</button></li>
		    <li class="breadcrumb-item"><button type="submit" name="page" value="admin/UsuariosAConfirmar.jsp" class="button-emula-anchor">Socio</button></li>
		    <li class="breadcrumb-item active" aria-current="page">Pagar</li>
		  </ol>
		</nav>
	</form>
	<p class="welcome-title mx-3">Confirmar pago</p>
	<form action="ConfirmarCobroCuotas" method="post" class="w-50">							
		<div class="container">
			<div class="row">
				<div class="col-lg-12, col-sm-12, col-12">
					<div class="table-responsive">
						<table class="table table-light table-striped table-hover">
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
						<div class="fs-4 mb-2">El total a cobrar es de: <span class="fw-semibold">$<%=costoTotal%></span></div>
						<div class="d-flex justify-content-start gap-1">					
							<button type="submit" name="opcion" value="cobrar" class="btn btn-primary btn-md">Confirmar</button>	
							<button type="submit" name="opcion" value="cancelar" class="btn btn-outline-secondary btn-md">Cancelar</button>	
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>  
</div>

</main><%@ include file="../FooterTags.jsp" %></body>
</html>