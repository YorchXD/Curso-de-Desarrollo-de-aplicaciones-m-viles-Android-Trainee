package Model;

public class Unidad
{
	private String nombre;
	private int numero_unidad;
	private int division_anual;
	private Asignatura asignatura;
	private Estado estado;
	private int id;
	
	public Unidad(String nombre, int numero_unidad, int division_anual, Asignatura asignatura)
	{
		this.nombre = nombre;
		this.numero_unidad = numero_unidad;
		this.division_anual = division_anual;
		this.asignatura = asignatura;
		this.estado = Estado.HABILITADO;
	}
	
	public Unidad(int id, String nombre, int numero_unidad, int division_anual, Asignatura asignatura, Estado estado)
	{
		this.id = id;
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

	public Estado getEstado()
	{
		return estado;
	}

	public void setEstado(Estado estado)
	{
		this.estado = estado;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
	

	public void mostrarDatos(Tipo_Division_Anual tipoDivAnual)
	{
		System.out.println("\n********************************************************");
		System.out.println("*            Datos de la unidad a modificar            *");
		System.out.println("********************************************************");
		System.out.println("Unidad: " + this.numero_unidad);
		System.out.println("Id: " + this.id);
		System.out.println("Nombre: " + this.nombre);
		if(tipoDivAnual == tipoDivAnual.TRIMESTRAL)
		{
			System.out.println("Trimestre en que se imparte la unidad: " + this.division_anual + "° trimestre");
		}
		else if(tipoDivAnual == tipoDivAnual.SEMESTRAL)
		{
			System.out.println("Semestre en que se imparte la unidad: " + this.division_anual + "° semestre");
		}
		else
		{
			System.out.println("La unidad se imparte dentro del año dado que la asignatura es anual");
		}
		System.out.println("Estado: " + this.estado);
		System.out.println("********************************************************\n");
	} 
}
