package Logic;

import java.util.LinkedList;

import Data.DataPrestamo;
import Entities.Prestamo;
import Entities.Socio;
import Entities.LineaDePrestamo;

public class PrestamoLogic {
	private DataPrestamo dataPrestamo;
	private LineaDePrestamoLogic liLogic;
	
	public PrestamoLogic(){
		dataPrestamo = new DataPrestamo();
		liLogic = new LineaDePrestamoLogic();
	}
	public LinkedList<Prestamo> getAll(){
		return dataPrestamo.getAll();
	}
	public Prestamo getOneById(Prestamo prestamo) {		
		return liLogic.getAllByPrestamo(dataPrestamo.getOneById(prestamo));
	}
	public void add(Prestamo prestamo) {
		dataPrestamo.add(prestamo);
		liLogic.addAll(prestamo);
	}
	public void update(Prestamo prestamo) {
		dataPrestamo.update(prestamo);
	}
	public void remove(Prestamo prestamo) {
		// Antes de borrar un prestamo borro todas sus lineas de prestamo
		Prestamo prestamoCargado= this.getOneById(prestamo); //Primero se cargan las lineas al prestamo para borrarlas
		LinkedList<LineaDePrestamo> lineas = new LinkedList<>();
		lineas = prestamoCargado.getLineasDePrestamo();
		for (LineaDePrestamo lineaDePrestamo : lineas) {
			liLogic.remove(lineaDePrestamo);
		}
		dataPrestamo.remove(prestamo);
	}
	public int getCantidadLibrosPrestadosBySocio(Socio socio) {
		LinkedList<Prestamo> prestamos = dataPrestamo.getAll();
		LinkedList<Prestamo> prestamosDelSocio = new LinkedList<>();
		int librosPedidosDelSocio = 0;
		for(Prestamo p : prestamos) {
			if(p.getSocio().getIdSocio() == socio.getIdSocio()) {
				// Cargo el prestamo con sus lineas de prestamo.
				prestamosDelSocio.add(p);
			}
		}
		for(Prestamo p : prestamosDelSocio) {
			for(LineaDePrestamo ldp : p.getLineasDePrestamo()) {
				if(ldp.getFechaDevolucionReal() == null) librosPedidosDelSocio++;
			}
		}
		return librosPedidosDelSocio;
	}
	

}
