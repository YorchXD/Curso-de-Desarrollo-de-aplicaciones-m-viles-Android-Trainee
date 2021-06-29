package Model;

import java.util.Date;

public class Asistencia
{
	private Date fecha;
	private Tipo_Asistencia asiste;
	private Asignatura_Referencia asignatura;
	
	public Asistencia(Date fecha, Tipo_Asistencia asiste, Asignatura_Referencia asignatura)
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

	public Tipo_Asistencia getAsiste()
	{
		return asiste;
	}

	public void setAsiste(Tipo_Asistencia asiste)
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
