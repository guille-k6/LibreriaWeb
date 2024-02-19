<%@page import="java.util.LinkedList"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@page import="Entities.Socio"%>
<%@page import="Logic.CuotasLogic"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Pago de cuotas</title>
	<%
		Socio c = (Socio)session.getAttribute("usuario");
	 	String mensaje = (String)request.getAttribute("estado");
	 	CuotasLogic cuolog = new CuotasLogic();
		LinkedList<Socio> socios = new LinkedList<Socio>();
	    socios = cuolog.getSocioAConfirmar();		
	%>
</head>
<body>

<%@ include file="../NavigationBar.jsp" %>

<div class="container">
	<p class="bienvenidoTitulo">Socio a cobrar la cuota.</p>
	<form action="UsuariosAConfirmarForm" method="get" class="w-50">							
		<div class="container">
			<div class="row">
				<div class="col-lg-12, col-sm-12, col-12">
					<div class="table-responsive">
						<table class="table table-light table-striped table-hover">
							<thead>
								<tr>
									<th>ID</th>
									<th>Apellido</th>
									<th>Nombre</th>
									<th>Cobrar</th>
								</tr>
							</thead>
							<tbody>
								<% for (Socio soc : socios) {%>
								<tr>
									<td><%=soc.getIdSocio() %></td>
									<td><%=soc.getApellido() %></td>
									<td><%=soc.getNombre() %></td>
									<td><button type="submit" name="pagar" value="<%= soc.getIdSocio()%>" class="btn btn-primary">Cobrar</button></td>
								</tr>
								<% }%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</form> 
</div>

</body>
</html>