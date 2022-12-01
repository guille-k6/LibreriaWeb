package Logic;

import java.util.LinkedList;

import Data.DataEjemplar;
import Entities.Ejemplar;

public class EjemplarLogic {
	private DataEjemplar dataEjemplar;
	
	public EjemplarLogic() {
		dataEjemplar = new DataEjemplar();
	}
	public LinkedList<Ejemplar> getAll(){
		return dataEjemplar.getAll();
	}
	public Ejemplar getOneById(Ejemplar ejemplar) {
		return dataEjemplar.getById(ejemplar);
	}
	public void add(Ejemplar ejemplar) {
		dataEjemplar.add(ejemplar);
	}
	public void update(Ejemplar ejemplar) {
		dataEjemplar.update(ejemplar);
	}
	public void remove(Ejemplar ejemplar) {
		dataEjemplar.remove(ejemplar);
	}	
	

}
