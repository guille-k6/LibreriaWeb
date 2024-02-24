package Entities;

public class Socio {
	private int idSocio;
	private String apellido;
	private String nombre;
	private String email;
	private String domicilio;
	private String telefono;
	private String contrasenia;
	private String usuario;
	private boolean isAdmin;

	public Socio() {
	}

	public Socio(int idSocio) {
		this.idSocio = idSocio;
	}

	public Socio(int idSocio, String apellido, String nombre, String email, String domicilio, String telefono,
			String contrasenia, String usuario, boolean isAdmin) {
		this.apellido = apellido;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.contrasenia = contrasenia;
		this.usuario = usuario;
		this.isAdmin = isAdmin;
	}

	public Socio(int idSocio, String apellido, String nombre, String email, String domicilio, String telefono,
			String usuario) {
		this.apellido = apellido;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.usuario = usuario;
	}

	public int getIdSocio() {
		return idSocio;
	}

	public void setIdSocio(int idSocio) {
		this.idSocio = idSocio;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public boolean getAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
