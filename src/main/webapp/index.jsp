<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name=description content="Trabajo prï¿½ctico Java. Sistema de gestiï¿½n de una librerï¿½a.">
    <meta name=keywords content="library">
	<link rel="icon" type="image/x-icon" href="/favicon.ico">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/loginStyle.css">

    <title>Libreria</title>
    
    <%
    	String mensaje = (String)request.getAttribute("loginMessage");
    %>
  </head>
  <body style="display: flex; flex-direction: column; min-height: 100vh;"><main>
	<div class="container">	
		<div class="row justify-content-center">
			<div class="col-lg-4 col-xl-4">
				<h1 class="loginTitle pb-4">Sistema de autogesti&oacute;n bibliotecario.</h1>
				<form class="loginForm" action="login" method="post">
					<label for="inp" class="inp">
					  <input type="text" name="usuario" id="inp" placeholder="&nbsp;">
					  <span class="label">Usuario</span>
					  <span class="focus-bg"></span>
					</label>
					<label for="inp" class="inp">
					  <input type="password" name="password" id="inp" placeholder="&nbsp;">
					  <span class="label">Contrase&ntilde;a</span>
					  <span class="focus-bg"></span>
					</label>
				  	<button type="submit" class="btn btn-primary botonLogin">Ingresar</button>
				  	<div class="d-flex justify-content-between">
					  	<a href="" class="login-anchor pt-2">Regístrate</a>
						<a href="" class="login-anchor pt-2">Olvidaste tu contraseña?</a>		
				  	</div>
				</form>	
			<%if(mensaje != null){ %>
				<p class="mensajeInfo"><%=mensaje%></p>
			<%} %>		
			</div>
		</div>
	</div>
</html>