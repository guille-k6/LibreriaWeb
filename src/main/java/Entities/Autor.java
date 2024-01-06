package Entities;

public class Autor {
	private int idAutor;
	private String nombre;
	private String apellido;

	public Autor() {}
	public Autor(int idAutor, String nombre, String apellido) {
		this.idAutor = idAutor;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public int getIdAutor() {
		return idAutor;
	}
	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
}
