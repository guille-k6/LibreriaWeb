package Logic;

import java.util.LinkedList;

import Data.AutorData;
import Entities.Autor;

public class AutorLogic {
	private AutorData autorData;
	
	public AutorLogic(){
		autorData = new AutorData();
	}
	public LinkedList<Autor> getAll(){
		return autorData.getAll();
	}
	public Autor getOneById(Autor autor) {
		return autorData.getById(autor);
	}
	public void add(Autor autor) {
		autorData.add(autor);
	}
	public void update(Autor autor) {
		autorData.update(autor);
	}
	public void remove(Autor autor) {
		autorData.remove(autor);
	}
}
