package Logic;

import java.util.LinkedList;

import Data.DataSocio;
import Entities.*;

public class SocioLogic {
	
	private DataSocio dataSocio;
	
	public SocioLogic(){
		dataSocio = new DataSocio();
	}
	public LinkedList<Socio> getAll(){
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
	
	/*
	public Socio getByUser(Socio socio){
		Socio elSocio = new Socio();
		elSocio.setIdSocio(-1);
		LinkedList<Socio> losSocios = dataSocio.getAll();
		for (Socio s : losSocios) {
			if (s.getUsuario().equals(socio.getUsuario()) && ) {
				elSocio = s;
			}
		}
		return elSocio; // si no se encuentra el socio, devuelve un socio vacio con id = -1
	}*/
}
