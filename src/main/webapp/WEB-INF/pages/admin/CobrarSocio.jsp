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
			request.getRequestDispatcher("index.jsp").forward(request, response);		
		}	
		Socio socioACobrar = (Socio)request.getAttribute("socioACobrar");
		CuotasLogic cuolog = new CuotasLogic();
		LinkedList<Cuotas> lasCuotas = cuolog.getCuotasAConfirmarByUser(socioACobrar);
		ValorCuotasLogic valcuolog = new ValorCuotasLogic();
		double valorCuotas = valcuolog.getValorActual();
		String error = (String)request.getAttribute("error");	
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
		    <li class="breadcrumb-item active" aria-current="page">Elegir cuotas</li>
		  </ol>
		</nav>
	</form>
	<p class="welcome-title mx-3">Elegir cuotas a pagar</p>
	<form action="cobrarCuotasForm" method="post" class="">							
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
									<th>Cobrar</th>									
								</tr>
							</thead>
							<tbody>
								<% for (Cuotas cuo : lasCuotas) {%>
								<tr>
									<td><%=cuo.getFechaDesde().toString() %></td>
									<td><%=cuo.getFechaHasta().toString() %></td>
									<td><%=valorCuotas%></td>
									<td><%=cuo.getEstado() %></td>
									<td><input class="form-check-input mt-0" type="checkbox" name="idcheck" value="<%=cuo.getIdCuota()%>"></td>
								</tr>
								<% }%>
							</tbody>
							
						</table>
						<div class="d-flex justify-content-start">					
							<button type="submit" name="opcion" value="cobrar" class="btn btn-primary btn-md m-2">Cobrar seleccionadas</button>	
							<button type="submit" name="opcion" value="cancelar" class="btn btn-outline-secondary btn-md m-2">Cancelar</button>	
							<input type="checkbox" name="idcheckSocio" value="<%=socioACobrar.getIdSocio()%>" checked hidden>
						</div>
					</div>
				</div>
			</div>
		</div>
	<%if(!(error == null)){%>
	<p class="errorMensaje"><%=error %></p>
	<%};%>
	</form>  
</div>

</main><%@ include file="../FooterTags.jsp" %></body>
<script src="js/eliminarErrores.js"></script>
</html>