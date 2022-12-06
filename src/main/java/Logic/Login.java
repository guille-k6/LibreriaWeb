package Logic;

import java.util.LinkedList;

import Data.*;
import Entities.*;

public class Login {
	private DataSocio ds;
	
	public Login() {
		ds=new DataSocio();
	}
	
	public Socio validate(Socio s) { // Este valida que exista el socio.
		
		
		/* para hacer más seguro el manejo de passwords este sería un lugar 
		 * adecuado para generar un hash de la password utilizando un cifrado
		 * asimétrico como sha256 y utilizar el hash en lugar de la password en plano 
		 */
		
		return ds.getByUser(s);
	}
	
	public LinkedList<String> validar(Socio socio){
		LinkedList<String> losErrores = new LinkedList<String>();
		
		if(socio.getUsuario().equals("")) {
			losErrores.add("El nombre no puede estar vacío.");
		}
		if(socio.getContrasenia().equals("")) {
			losErrores.add("La contraseña no puede estar vacía.");
		}
		Socio elSocio = this.validate(socio);
		if(elSocio== null) {
			losErrores.add("Nombre y/o contraseña incorrectos.");
		}
		return losErrores;
	}


}
