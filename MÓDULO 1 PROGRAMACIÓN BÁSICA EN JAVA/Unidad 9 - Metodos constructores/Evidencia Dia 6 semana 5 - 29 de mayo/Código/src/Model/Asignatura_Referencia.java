package Model;

public class Asignatura_Referencia
{
	private Profesor profesor;
	private Asignatura asignatura;
	
	public Asignatura_Referencia(Profesor profesor, Asignatura asignatura)
	{
		this.profesor = profesor;
		this.asignatura = asignatura;
	}

	public Profesor getProfesor()
	{
		return profesor;
	}

	public void setProfesor(Profesor profesor)
	{
		this.profesor = profesor;
	}

	public Asignatura getAsignatura()
	{
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura)
	{
		this.asignatura = asignatura;
	}
}
