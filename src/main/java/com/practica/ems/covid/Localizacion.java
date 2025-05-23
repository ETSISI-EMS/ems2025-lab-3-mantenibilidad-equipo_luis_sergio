package com.practica.ems.covid;


import java.util.Iterator;
import java.util.LinkedList;

import com.practica.excecption.EmsDuplicateLocationException;
import com.practica.excecption.EmsLocalizationNotFoundException;
import com.practica.genericas.FechaHora;
import com.practica.genericas.Fecha;
import com.practica.genericas.PosicionPersona;

public class Localizacion {
	LinkedList<PosicionPersona> lista;

	public Localizacion() {
		super();
		this.lista = new LinkedList<PosicionPersona>();
	};
	
	public LinkedList<PosicionPersona> getLista() {
		return lista;
	}

	public void setLista(LinkedList<PosicionPersona> lista) {
		this.lista = lista;
	}

	public void addLocalizacion (PosicionPersona p) throws EmsDuplicateLocationException {
		try {
			findLocalizacion(p.getDocumento(), p.getFechaPosicion().getFecha().toString(),p.getFechaPosicion().getHora().toString() );
			throw new EmsDuplicateLocationException();
		}catch(EmsLocalizationNotFoundException e) {
			lista.add(p);
		}
	}
	
	public int findLocalizacion (String documento, String fecha, String hora) throws EmsLocalizationNotFoundException {
	    int cont = 0;
	    Iterator<PosicionPersona> it = lista.iterator();
	    while(it.hasNext()) {
	    	cont++;
	    	PosicionPersona pp = it.next();
	    	FechaHora fechaHora = this.parsearFechaHora(fecha, hora);
	    	if(pp.getDocumento().equals(documento) && 
	    	   pp.getFechaPosicion().equals(fechaHora)) {
	    		return cont;
	    	}
	    } 
	    throw new EmsLocalizationNotFoundException();
	}
	public void delLocalizacion(String documento, String fecha, String hora) throws EmsLocalizationNotFoundException {
	    int pos=-1;
	    try {
			pos = findLocalizacion(documento, fecha, hora);
		} catch (EmsLocalizationNotFoundException e) {
			throw new EmsLocalizationNotFoundException();
		}
	    this.lista.remove(pos);
	    
	}
	
	void printLocalizacion() {    
	    for(int i = 0; i < this.lista.size(); i++) {
			System.out.println(lista.get(i).toString());
	    }
	}

	@Override
	public String toString() {
		String cadena = "";
		for(int i = 0; i < this.lista.size(); i++) {
			PosicionPersona pp = lista.get(i);
	        cadena += String.format("%s;", pp.getDocumento());
	        FechaHora fecha = pp.getFechaPosicion();        
	        cadena+=String.format("%02d/%02d/%04d;%02d:%02d;", 
	        		fecha.getFecha().getDia(), 
	        		fecha.getFecha().getMes(), 
	        		fecha.getFecha().getAnio(),
	        		fecha.getHora().getHora(),
	        		fecha.getHora().getMinuto());
	        cadena+=String.format("%.4f;%.4f\n", pp.getCoordenada().getLatitud(), 
	        		pp.getCoordenada().getLongitud());
	    }
		return cadena;		
	}
	
	private Fecha parsearFecha (String fecha) {
		int dia, mes, anio;
		String[] valores = fecha.split("\\/");
		dia = Integer.parseInt(valores[0]);
		mes = Integer.parseInt(valores[1]);
		anio = Integer.parseInt(valores[2]);
		Fecha fechaHora = new Fecha(dia, mes, anio);
		return fechaHora;
	}
	
	private FechaHora parsearFechaHora (String fecha, String hora) {
		Fecha newFecha = parsearFecha(fecha);
		int minuto, segundo;
		String[] valores = hora.split("\\:");
		minuto = Integer.parseInt(valores[0]);
		segundo = Integer.parseInt(valores[1]);
		FechaHora fechaHora = new FechaHora(newFecha.getDia(), newFecha.getMes(), newFecha.getAnio(), minuto, segundo);
		return fechaHora;
	}
	
}
