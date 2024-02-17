package Logic;

import java.util.LinkedList;

import Data.DataPoliticaPrestamo;
import Entities.PoliticaPrestamo;

public class PoliticaPrestamoLogic {
	private DataPoliticaPrestamo dataPoliticaPrestamo;

	public PoliticaPrestamoLogic(){
		dataPoliticaPrestamo = new DataPoliticaPrestamo();
	}
	public LinkedList<PoliticaPrestamo> getAll(){
		return dataPoliticaPrestamo.getAll();
	}
	public PoliticaPrestamo getOneById(PoliticaPrestamo PoliticaPrestamo) {
		return dataPoliticaPrestamo.getById(PoliticaPrestamo);
	}
	public void add(PoliticaPrestamo PoliticaPrestamo) {
		dataPoliticaPrestamo.add(PoliticaPrestamo);
	}
	public void update(PoliticaPrestamo PoliticaPrestamo) {
		dataPoliticaPrestamo.update(PoliticaPrestamo);
	}
	public void remove(PoliticaPrestamo PoliticaPrestamo) {
		dataPoliticaPrestamo.remove(PoliticaPrestamo);
	}

}
