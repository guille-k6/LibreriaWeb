package Logic;

import java.util.LinkedList;

import Data.DataLibro;
import Entities.Autor;
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
	
	public LinkedList<String> validar(Libro libro){
		LinkedList<String> losErrores = new LinkedList<String>();
		if(libro.getIsbn().equals("")) {
			losErrores.add("No se puede ingresar un isbn nulo.");
		}if(libro.getTitulo().equals("")) {
			losErrores.add("No se puede ingresar un titulo nulo.");
		}if(libro.getEditorial().equals("")) {
			losErrores.add("No se puede ingresar un editorial nula.");
		}
		return losErrores; // si losErrores está vacío significa que aprobó la validación
	}

}
