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

	public LinkedList<String> validar(Libro libro, String cantDiasMaxPrestamo){
		LinkedList<String> losErrores = new LinkedList<>();
		int numero;
		if(libro.getIsbn().equals("")) {
			losErrores.add("No se puede ingresar un isbn nulo.");
		}if(libro.getTitulo().equals("")) {
			losErrores.add("No se puede ingresar un titulo nulo.");
		}if(libro.getEditorial().equals("")) {
			losErrores.add("No se puede ingresar un editorial nula.");
		}if(libro.getEditorial().equals("")) {
			losErrores.add("No se puede ingresar un editorial nula.");
		}if(!parsestring(cantDiasMaxPrestamo)) {
			if(!(cantDiasMaxPrestamo == "")) {
				losErrores.add("No se puede ingresar caracter en cant dias max prestamo.");}
		}if(cantDiasMaxPrestamo == "") {
			losErrores.add("No se puede ingresar un cant dias max prestamo nulo.");
		}if(parsestring(cantDiasMaxPrestamo)) {
				numero = Integer.parseInt(cantDiasMaxPrestamo);
				if(numero < 0) {
					losErrores.add("No se puede ingresar un cant dias max prestamo negativo.");}
		}if(parsestring(cantDiasMaxPrestamo)) {
			numero = Integer.parseInt(cantDiasMaxPrestamo);
			if(numero > 1000) {
				losErrores.add("No se puede ingresar un cant dias max prestamo mayor a 1000.");}
		}
		return losErrores; // si losErrores est� vac�o significa que aprob� la validaci�n
	}
	public boolean parsestring(String cantDiasMaxPrestamo) {
		int numero;
		try {
		     numero = Integer.parseInt(cantDiasMaxPrestamo);
		    return true;
		} catch (NumberFormatException e) {
			return false;
		    }
	}

}
