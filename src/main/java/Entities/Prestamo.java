package Entities;

import java.sql.Date;
import java.util.LinkedList;

public class Prestamo {
	private int idPrestamo;
	private java.sql.Date fechaPrestamo;
	private String estado; // Pendiente - Finalizado

	private LinkedList<LineaDePrestamo> lineasDePrestamo;
	private Socio socio;

	public Prestamo() {
	}

	public Prestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public Prestamo(Socio socio) {
		this.socio = socio;
		this.fechaPrestamo = new Date(System.currentTimeMillis());
	}

	public int getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public java.sql.Date getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(java.sql.Date fechaPrestamo) {
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
