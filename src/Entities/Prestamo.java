package Entities;
import java.util.LinkedList;

public class Prestamo {
	private int idPrestamo;
	private String fechaPrestamo;
	private LinkedList<LineaDePrestamo> lineasDePrestamo;
	private Socio socio;
	public int getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public String getFechaPrestamo() {
		return fechaPrestamo;
	}
	public void setFechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}
	public LinkedList<LineaDePrestamo> getLineasDePrestamo() {
		return lineasDePrestamo;
	}
	public void setLineasDePrestamo(LinkedList<LineaDePrestamo> lineasDePrestamo) {
		this.lineasDePrestamo = lineasDePrestamo;
	}
	public Socio getSocio() {
		return socio;
	}
	public void setSocio(Socio socio) {
		this.socio = socio;
	}

}
