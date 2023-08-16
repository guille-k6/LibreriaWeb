package Logic;

import java.util.LinkedList;

import Data.DataEjemplar;
import Entities.Ejemplar;
import Entities.Libro;

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
	public LinkedList<Ejemplar> getAllEjemplaresByLibro(Libro libro){
		// Dado un libro devuelve los ejemplares de ese libro
		LinkedList<Ejemplar> listaLibros = dataEjemplar.getAll();
		LinkedList<Ejemplar> listaFiltrada = new LinkedList<>();
		 for (Ejemplar ejemplar : listaLibros) {
	            if (ejemplar.getLibro().getIdLibro() == libro.getIdLibro()) {
	                listaFiltrada.add(ejemplar);
	            }		
		 }
		 return listaFiltrada;
	}

}
