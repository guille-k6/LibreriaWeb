package Logic;

import java.util.LinkedList;
import java.util.List;

import Data.DataSocio;
import Entities.PoliticaPrestamo;
import Entities.Socio;

public class SocioLogic {

	private DataSocio dataSocio;

	public SocioLogic() {
		dataSocio = new DataSocio();
	}

	public LinkedList<Socio> getAll() {
		return dataSocio.getAll();
	}

	public Socio getOneById(Socio socio) {
		return dataSocio.getById(socio);
	}

	public void add(Socio socio) {
		dataSocio.add(socio);
	}

	public void update(Socio socio) {
		dataSocio.update(socio);
	}

	public void remove(Socio socio) {
		dataSocio.remove(socio);
	}

	public LinkedList<Socio> getAllByApellido(Socio socio) {
		LinkedList<Socio> losSocios = dataSocio.getAll();
		LinkedList<Socio> sociosFiltrados = new LinkedList<>();
		for (Socio s : losSocios) {
			if (s.getApellido().toLowerCase().contains(socio.getApellido().toLowerCase())) {
				sociosFiltrados.add(s);
			}
		}
		return sociosFiltrados;
	}

	public List<Socio> getAllSociosThatMatch(String matching) {
		return dataSocio.getAllSociosThatMatch(matching);
	}

	/**
	 * Devuelve la cantidad de libros que un socio tiene prestados
	 * 
	 * @param socio
	 * @return Cantidad de libros que un socio tiene prestados actualmente
	 */
	public int getCantidadLibrosPrestadosBySocio(Socio socio) {
		return dataSocio.getCantidadLibrosPrestadosBySocio(socio);
	}

	/**
	 * Devuelve la cantidad de libros que un socio puede pedir prestados
	 * 
	 * @param socio
	 * @return Cantidad de libros que un socio puede pedir prestados actualmente
	 */
	public int getPossibleAmountOfBooks(Socio socio) {
		PoliticaPrestamoLogic ppl = new PoliticaPrestamoLogic();
		PoliticaPrestamo pp = ppl.getLast();
		return pp.getCantMaxLibrosPend() - this.getCantidadLibrosPrestadosBySocio(socio);
	}
	/*
	 * public Socio getByUser(Socio socio){ Socio elSocio = new Socio();
	 * elSocio.setIdSocio(-1); LinkedList<Socio> losSocios = dataSocio.getAll(); for
	 * (Socio s : losSocios) { if (s.getUsuario().equals(socio.getUsuario()) && ) {
	 * elSocio = s; } } return elSocio; // si no se encuentra el socio, devuelve un
	 * socio vacio con id = -1 }
	 */
}
