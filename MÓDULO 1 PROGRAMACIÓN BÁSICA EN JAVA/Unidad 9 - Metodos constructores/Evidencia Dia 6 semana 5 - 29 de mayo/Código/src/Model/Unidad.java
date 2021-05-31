package Model;

public class Unidad
{
	private String nombre;
	private int numero_unidad;
	private int division_anual;
	private Asignatura asignatura;
	private String estado;
	
	public Unidad(String nombre, int numero_unidad, int division_anual, Asignatura asignatura, String estado)
	{
		this.nombre = nombre;
		this.numero_unidad = numero_unidad;
		this.division_anual = division_anual;
		this.asignatura = asignatura;
		this.estado = estado;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public int getNumero_unidad()
	{
		return numero_unidad;
	}

	public void setNumero_unidad(int numero_unidad)
	{
		this.numero_unidad = numero_unidad;
	}

	public int getDivision_anual()
	{
		return division_anual;
	}

	public void setDivision_anual(int division_anual)
	{
		this.division_anual = division_anual;
	}

	public Asignatura getAsignatura()
	{
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura)
	{
		this.asignatura = asignatura;
	}

	public String getEstado()
	{
		return estado;
	}

	public void setEstado(String estado)
	{
		this.estado = estado;
	}
}
