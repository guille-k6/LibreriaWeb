package Entities;
import java.util.LinkedList;


public class Venta {
	private int idVenta;
	private String fechaVenta;
	private float precioTotal;
	private Socio socio;
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	public String getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(String fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	public float getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}
	public Socio getSocio() {
		return socio;
	}
	public void setSocio(Socio socio) {
		this.socio = socio;
	}
	public LinkedList<LineaDeVenta> getLineasDeVenta() {
		return lineasDeVenta;
	}
	public void setLineasDeVenta(LinkedList<LineaDeVenta> lineasDeVenta) {
		this.lineasDeVenta = lineasDeVenta;
	}
	private LinkedList<LineaDeVenta> lineasDeVenta;

}
