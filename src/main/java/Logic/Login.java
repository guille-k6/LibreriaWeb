package Logic;

import java.util.LinkedList;

import Data.*;
import Entities.*;

public class Login {
	private DataSocio dataSocio = new DataSocio();

	public Socio validate(Socio s) { 
		// Este valida que exista el socio.
		String hashedPassword = PasswordEncrypter.sha256(s.getContrasenia());
		s.setContrasenia(hashedPassword);
		
		return dataSocio.getByUser(s);
	}
	
	public LinkedList<String> validar(Socio socio){
		LinkedList<String> losErrores = new LinkedList<String>();
		
		if(socio.getUsuario().equals("")) {
			losErrores.add("El nombre no puede estar vacio.");
		}
		if(socio.getContrasenia().equals("")) {
			losErrores.add("La contrasenia no puede estar vacia.");
		}
		Socio elSocio = this.validate(socio);
		if(elSocio== null) {
			losErrores.add("Nombre y/o contrasenia incorrectos.");
		}
		return losErrores;
	}


}
