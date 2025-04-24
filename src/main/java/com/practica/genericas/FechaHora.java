package com.practica.genericas;
import java.time.LocalDateTime;

public class FechaHora implements Comparable<FechaHora>{

	Fecha fecha;
	Hora hora;
	
	public FechaHora(Fecha fecha, Hora hora) {
		super();
		this.fecha = fecha;
		this.hora = hora;
	}

	public FechaHora(int dia, int mes, int anio, int hora, int minuto) {
		this.fecha = new Fecha(dia, mes, anio);
		this.hora = new Hora(hora, minuto);
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public Hora getHora() {
		return hora;
	}

	public void setHora(Hora hora) {
		this.hora = hora;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((hora == null) ? 0 : hora.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		FechaHora fecha = (FechaHora) obj;
		return getFecha().getDia() == fecha.getFecha().getDia() && getFecha().getMes() == fecha.getFecha().getMes()
				&& getFecha().getAnio() == fecha.getFecha().getAnio()
				&& getHora().getHora() == fecha.getHora().getHora()
				&& getHora().getMinuto() == fecha.getHora().getMinuto();
	}

	@Override
	public int compareTo(FechaHora o) {
		LocalDateTime dateTime1= LocalDateTime.of(this.getFecha().getAnio(), this.getFecha().getMes(), this.getFecha().getDia(), 
				this.getHora().getHora(), this.getHora().getMinuto());
		LocalDateTime dateTime2= LocalDateTime.of(o.getFecha().getAnio(), o.getFecha().getMes(), o.getFecha().getDia(), 
				o.getHora().getHora(), o.getHora().getMinuto());
		
		return dateTime1.compareTo(dateTime2);
	}
	
	
}
