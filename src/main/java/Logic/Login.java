package Logic;

import Data.DataSocio;
import Entities.Socio;
import utils.PasswordEncrypter;

public class Login {
	private DataSocio dataSocio = new DataSocio();

	/**
	 * Valida las credenciales de un socio en el login.
	 * @param socio Un socio con nombre de usuario y contraseña.
	 * @return un socio si las credenciales son correctas o nulo si las credenciales son incorrectas.
	 */
	public Socio validate(Socio socio) {
		String hashedPassword = PasswordEncrypter.sha256(socio.getContrasenia());
		socio.setContrasenia(hashedPassword);

		return dataSocio.getByUser(socio);
	}

}
