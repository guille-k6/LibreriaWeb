package Entities;

public class Sancion {
	private int idSancion;
	private String fechaSancion;
	private int diasSancion;
	private boolean estado;
	private Socio socio;
	public int getIdSancion() {
		return idSancion;
	}
	public void setIdSancion(int idSancion) {
		this.idSancion = idSancion;
	}
	public String getFechaSancion() {
		return fechaSancion;
	}
	public void setFechaSancion(String fechaSancion) {
		this.fechaSancion = fechaSancion;
	}
	public int getDiasSancion() {
		return diasSancion;
	}
	public void setDiasSancion(int diasSancion) {
		this.diasSancion = diasSancion;
	}
	public boolean isEstado() {
		/// aca se puede calcular en funion de la fechaSancion y los diasSancion
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public Socio getSocio() {
		return socio;
	}
	public void setSocio(Socio socio) {
		this.socio = socio;
	}

}