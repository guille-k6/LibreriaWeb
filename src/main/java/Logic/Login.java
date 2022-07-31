package Logic;

import java.util.LinkedList;

import Data.*;
import Entities.*;

public class Login {
	private DataSocio ds;
	
	public Login() {
		ds=new DataSocio();
	}
	
	public Socio validate(Socio s) {
		
		
		/* para hacer más seguro el manejo de passwords este sería un lugar 
		 * adecuado para generar un hash de la password utilizando un cifrado
		 * asimétrico como sha256 y utilizar el hash en lugar de la password en plano 
		 */
		
		return ds.getByUser(s);
	}

	public LinkedList<Socio> getAll(){
		return ds.getAll();
	}
}
