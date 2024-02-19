<form action="headerForm" method="post">
	<div class="contenedorNavBar">
		<div class="d-flex flex-row justify-content-start align-items-stretch">
		
			<div class="navBarItem navBarItem-Main d-flex align-items-center justify-content-center">WEBLibrary</div>
			
			<div class="navBarItem d-flex align-items-center justify-content-center">
				<button type="submit" name="opcion" value="menu" class="botonMenu d-flex align-items-center"><i class="small material-icons home-icon">home</i>Home</button>
			</div>
			
			<div class="navBarItem ms-auto opcionLogout d-flex align-items-center justify-content-center">
				<i class="material-icons person-icon">person</i>
				<div class="d-flex align-items-center justify-content-center"><%=c.getUsuario() %></div>
			 	<button class="logout-button d-flex align-items-center justify-content-center" type="submit" name="opcion" value="logout"><i class="material-icons logout-icon">keyboard_tab</i></button>
			</div>
		</div>
	</div>
</form>