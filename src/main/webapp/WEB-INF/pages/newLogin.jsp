<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name=description content="Trabajo pr�ctico Java. Sistema de gesti�n de una librer�a.">
    <meta name=keywords content="library">
    <!-- Bootstrap 5.2 CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
 	<!-- local styles -->
    <link rel="stylesheet" type="text/css" href="css/loginStyle.css">

    <title>Libreria</title>
  </head>
  <body>




	<div class="container">
		<p class="loginTitle">Sistema de autogesti&oacute;n bibliotecario.</p>
		<p class="loginText">Por favor, ingrese su usuario y su contrase&ntilde;a.</p>
		
		
		<div class="row justify-content-center">
			<div class="col-lg-3 col-xl-3 ">
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
				</form>	
				<a href="" class="loginText registro">Nuevo usuario? Reg&iacute;strate</a>
				<a href="" class="loginText registro">Olvid&oacute; su contraseña?</a>		
			</div>	
		</div>

	</div>

  </body>
</html>