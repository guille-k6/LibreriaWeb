<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Socio, Logic.CuotasLogic, Entities.Cuotas, Logic.ValorCuotasLogic"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Alquilar libro</title>
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
<body style="display: flex; flex-direction: column; min-height: 100vh;"><main>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<p class="bienvenidoTitulo">Alquilar libros.</p>

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

</main><%@ include file="../FooterTags.jsp" %></body>
</html>