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
		//if(!c.getAdmin()){
		//	response.sendRedirect("WEB-INF/pages/menuUser.jsp");
		//}  			
		Socio c = (Socio)session.getAttribute("usuario");	
		CuotasLogic cuolog = new CuotasLogic();
		LinkedList<Cuotas> lasCuotas = cuolog.getCuotasImpagasByUser(c);
		ValorCuotasLogic valcuolog = new ValorCuotasLogic();
		double valorCuotas = valcuolog.getValorActual();
		String error = (String)request.getAttribute("error");	
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<p class="bienvenidoTitulo">Pago de cuotas.</p>
	<%if(!(error == null)){%>
	<p class="errorMensaje w-100"><%=error %></p>
	<%};%>
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
								<button type="submit" name="opcion" value="pagar" class="btn btn-success mt-3">Pagar seleccionadas</button>	
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