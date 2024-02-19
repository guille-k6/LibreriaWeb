<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Socio, Logic.CuotasLogic, Entities.Cuotas, Logic.ValorCuotasLogic"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>	
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

<%@ include file="../NavigationBar.jsp" %>

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
						<table class="table table-light table-striped table-hover">
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