<%@page import="java.util.ArrayList, Entities.Socio, utils.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../HeadTags.jsp" %>
	<title>Autores</title>

	<%
		Socio c = (Socio)session.getAttribute("usuario");
		if(!c.getAdmin()){
			request.getRequestDispatcher("index.jsp").forward(request, response);		
		}
		ArrayList<LibraryLog> logsList = LoggerError.readLog();
	%>
</head>
<body style="display: flex; flex-direction: column; min-height: 100vh;"><main>

<%@ include file="../NavigationBar.jsp" %>

<div class="container pt-3">

	<form action="headerForm" method="post">
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><button type="submit" name="opcion" value="menu" class="button-emula-anchor">Home</button></li>
		    <li class="breadcrumb-item active" aria-current="page">Errores</li>
		  </ol>
		</nav>
	</form>
	
	<p class="welcome-title mx-3">Registro de errores</p>			
	<div class="container">
		<div class="row">
			<div class="col-lg-12, col-sm-12, col-12">
			<% for (LibraryLog log : logsList) {%>
			<div class="log-container">
				<div>
					<span class="fw-semibold" style="font-size:18px">
						<%=log.getDateString()%> :
					</span> 
					<span class="text-danger fw-semibold">
						<%=log.getLogMessage()%>
					</span>
				</div>
				<div class="log-body"><%=log.getTitle()%></div>
			</div>
			<% }%>
			</div>
		</div>
	</div>
</div>

</main><%@ include file="../FooterTags.jsp" %></body>
</html>