package Entities;

public class EjemplarCantidad {
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Libro getLibro() {
		return libro;
	}
	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	public int cantidad;
	public Libro libro;
	public EjemplarCantidad() {};
	public EjemplarCantidad(int cantidad, Libro libro) {
		this.cantidad = cantidad;
		this.libro = libro;
	}
	
}