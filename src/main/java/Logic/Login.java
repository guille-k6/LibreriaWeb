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
		
		
		/* para hacer m�s seguro el manejo de passwords este ser�a un lugar 
		 * adecuado para generar un hash de la password utilizando un cifrado
		 * asim�trico como sha256 y utilizar el hash en lugar de la password en plano 
		 */
		
		return ds.getByUser(s);
	}
	
	public LinkedList<String> validar(Socio socio){
		LinkedList<String> losErrores = new LinkedList<String>();
		
		if(socio.getUsuario().equals("")) {
			losErrores.add("El nombre no puede estar vac�o.");
		}
		if(socio.getContrasenia().equals("")) {
			losErrores.add("La contrase�a no puede estar vac�a.");
		}
		Socio elSocio = this.validate(socio);
		if(elSocio== null) {
			losErrores.add("Nombre y/o contrase�a incorrectos.");
		}
		return losErrores;
	}


}
