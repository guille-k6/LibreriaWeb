package Logic;

import java.util.LinkedList;
import java.util.List;

import Data.DataLibro;
import Entities.Libro;

public class LibroLogic {
	private DataLibro dataLibro;

	public LibroLogic() {
		dataLibro = new DataLibro();
	}

	public LinkedList<Libro> getAll() {
		return dataLibro.getAll();
	}

	public Libro getOneById(Libro libro) {
		return dataLibro.getById(libro);
	}

	public void add(Libro libro) throws Exception {
		dataLibro.add(libro);
	}

	public void update(Libro libro) throws Exception {
		dataLibro.update(libro);
	}

	public void remove(Libro libro) throws Exception {
		dataLibro.remove(libro);
	}

	public List<Libro> getAllLibrosDisponibles() {
		return dataLibro.getAllLibrosDisponibles();
	}

	public List<Libro> getAllLibrosDisponiblesThatMatch(String input) {
		return dataLibro.getAllLibrosDisponiblesThatMatch(input);
	}

	public LinkedList<String> validar(Libro libro, String cantDiasMaxPrestamo) {
		LinkedList<String> losErrores = new LinkedList<>();
		int numero;
		if (libro.getIsbn().equals("")) {
			losErrores.add("No se puede ingresar un isbn nulo.");
		}
		if (libro.getTitulo().equals("")) {
			losErrores.add("No se puede ingresar un titulo nulo.");
		}
		if (libro.getEditorial().equals("")) {
			losErrores.add("No se puede ingresar un editorial nula.");
		}
		if (libro.getEditorial().equals("")) {
			losErrores.add("No se puede ingresar un editorial nula.");
		}
		if (!parsestring(cantDiasMaxPrestamo)) {
			if (!(cantDiasMaxPrestamo == "")) {
				losErrores.add("No se puede ingresar caracter en cant dias max prestamo.");
			}
		}
		if (cantDiasMaxPrestamo == "" || cantDiasMaxPrestamo == null) {
			losErrores.add("No se puede ingresar un cant dias max prestamo nulo.");
		}
		if (parsestring(cantDiasMaxPrestamo)) {
			numero = Integer.parseInt(cantDiasMaxPrestamo);
			if (numero < 0) {
				losErrores.add("No se puede ingresar un cant dias max prestamo negativo.");
			}
		}
		if (parsestring(cantDiasMaxPrestamo)) {
			numero = Integer.parseInt(cantDiasMaxPrestamo);
			if (numero > 1000) {
				losErrores.add("No se puede ingresar un cant dias max prestamo mayor a 1000.");
			}
		}
		return losErrores;
	}

	public boolean parsestring(String cantDiasMaxPrestamo) {
		try {
			Integer.parseInt(cantDiasMaxPrestamo);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public List<Libro> getAllLibrosThatMatch(String libroBuscado) {
		return dataLibro.getAllLibrosThatMatch(libroBuscado);
	}

}
