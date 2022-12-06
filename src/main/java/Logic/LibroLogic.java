package Logic;

import java.util.LinkedList;

import Data.DataLibro;
import Entities.Libro;

public class LibroLogic {
	private DataLibro dataLibro;
	
	public LibroLogic() {
		dataLibro = new DataLibro();
	}
	public LinkedList<Libro> getAll(){
		return dataLibro.getAll();
	}
	public Libro getOneById(Libro libro) {
		return dataLibro.getById(libro);
	}
	public void add(Libro libro) {
		dataLibro.add(libro);
	}
	public void update(Libro libro) {
		dataLibro.update(libro);
	}
	public void remove(Libro libro) {
		dataLibro.remove(libro);
	}
	
//	public LinkedList<String> validar(){
//		
//	}
	

}
