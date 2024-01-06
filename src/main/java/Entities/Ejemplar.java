package Entities;

public class Ejemplar {
	private int idEjemplar;
	private Libro libro;
	private boolean isDisponible;

	public Ejemplar() {}
	public Ejemplar(int idEjemplar, boolean isDisponible, Libro libro) {
		this.idEjemplar = idEjemplar;
		this.isDisponible = isDisponible;
		this.libro = libro;
	}

	public boolean isDisponible() {
		return isDisponible;
	}
	public void setDisponible(boolean isDisponible) {
		this.isDisponible = isDisponible;
	}
	public int getIdEjemplar() {
		return idEjemplar;
	}
	public void setIdEjemplar(int idEjemplar) {
		this.idEjemplar = idEjemplar;
	}
	public Libro getLibro() {
		return libro;
	}
	public void setLibro(Libro libro) {
		this.libro = libro;
	}

}
