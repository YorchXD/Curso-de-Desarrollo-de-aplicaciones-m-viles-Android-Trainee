package model;

import java.util.Date;

public class Asistencia
{
	private Date fecha;
	private String asiste;
	private Asignatura_Referencia asignatura;
	
	public Asistencia(Date fecha, String asiste, Asignatura_Referencia asignatura)
	{
		this.fecha = fecha;
		this.asiste = asiste;
		this.asignatura = asignatura;
	}

	public Date getFecha()
	{
		return fecha;
	}

	public void setFecha(Date fecha)
	{
		this.fecha = fecha;
	}

	public String getAsiste()
	{
		return asiste;
	}

	public void setAsiste(String asiste)
	{
		this.asiste = asiste;
	}

	public Asignatura_Referencia getAsignatura()
	{
		return asignatura;
	}

	public void setAsignatura(Asignatura_Referencia asignatura)
	{
		this.asignatura = asignatura;
	}
}
