<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio"%>
<%@page import="Entities.Cuotas, Logic.CuotasLogic, Logic.SocioLogic"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Listado de cuotas</title>
	
	<%
		SocioLogic socioLogic = new SocioLogic();	
		Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.jsp").forward(request, response);		
		}
	 	String mensaje = (String)request.getAttribute("mensaje");
	 	List<Cuotas> cuotasBuscadas = (List<Cuotas>)request.getAttribute("cuotasBuscadas");
	 	String fechaDesde = (String)request.getAttribute("fechaDesde");
	 	String fechaHasta = (String)request.getAttribute("fechaHasta");
	 	String idSocioString = (String)request.getAttribute("idSocio");
	 	int idSocio = -1;
	 	if(idSocioString != null){
	 		idSocio = Integer.parseInt(idSocioString);
	 	}
	%>
</head>
<body style="display: flex; flex-direction: column; min-height: 100vh;"><main>

<%@ include file="../NavigationBar.jsp" %>

<div class="container pt-3">

	<form action="headerForm" method="post">
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><button type="submit" name="opcion" value="menu" class="button-emula-anchor">Home</button></li>
		    <li class="breadcrumb-item active" aria-current="page">Cuotas</li>
		  </ol>
		</nav>
	</form>
	
	<form action="listadoCuotas" method="get">		
				
		<div class="w-100 d-flex justify-content-between align-items-center mx-3">
			<p class="welcome-title">Listado de cuotas</p>
			<div class="d-flex align-items-center gap-1">
				<div class="w-100 d-flex justify-content-between align-items-center">
					<div class="d-flex " style="gap: 4px">
						<div class="input-group">
						  <span class="input-group-text" id="inputGroup-sizing-default">Fecha desde</span>
						  <input type="date" name="fechaDesde" value="<%=fechaDesde %>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
						</div>
						<div class="input-group">
						  <span class="input-group-text" id="inputGroup-sizing-default">Fecha hasta</span>
						  <input type="date" name="fechaHasta" value="<%=fechaHasta %>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
						</div>
						<div class="input-group">
						  <label class="input-group-text" for="inputGroupSelect01">Socio</label>
						  <select class="form-select" name="socio">
						    <%for(Socio soc : socioLogic.getAll()){ %>
						    	<option value="<%=soc.getIdSocio()%>" <%= soc.getIdSocio() == idSocio ? "selected" : "" %>><%=soc.getNombre() + " " + soc.getApellido()%></option>
						    <%} %>
						  </select>
						</div>	
						<button type="submit" name="buscar" class="btn btn-primary py-1">Buscar</button>
					</div>
				</div>		
			</div>
		</div>
		
		<!--   -->
		<div class="container">
		<% if(cuotasBuscadas == null){%>
		<p class="fs-5 mx-3" style="color: #747474;"> Elija un filtro.</p>
		<%} else if(cuotasBuscadas.size() == 0){ %>
		<p class="fs-5 mx-3" style="color: #747474;"> No se encontraron cuotas.</p>
		<%} else{ %>
			<div class="row">
				<div class="col-lg-12, col-sm-12, col-12">
					<div class="table-responsive">
						<table class="table table-light table-striped table-hover">
							<thead>
								<tr>
									<th>Fecha desde</th>
									<th>Fecha hasta</th>
									<th>Fecha pago</th>
									<th>Estado</th>
								</tr>
							</thead>
							<tbody>
								<% for (Cuotas cuo : cuotasBuscadas) {%>
								<tr>
									<td><%=cuo.getFechaDesde() %></td>
									<td><%=cuo.getFechaHasta() %></td>
									<td><%=cuo.getFechaPago() == null ? "-" : cuo.getFechaPago() %></td>
									<td><%=cuo.getEstado() %></td>
								</tr>
								<% }%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<%}%>
	</form> 
		<%if(mensaje != null){ %>
		<p hidden class="mensajeInfo"><%=mensaje%></p>
	<%} %>	
</div>
</main><%@ include file="../FooterTags.jsp" %></body>
<script src="js/eliminarMensajes.js"></script>
</html>