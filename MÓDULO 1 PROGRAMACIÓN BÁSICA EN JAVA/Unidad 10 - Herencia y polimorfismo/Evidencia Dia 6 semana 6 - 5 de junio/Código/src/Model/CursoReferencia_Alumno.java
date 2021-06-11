package Model;

public class CursoReferencia_Alumno {
	
	private Curso_Referencia refCursoReferencia;
	private Alumno refAlumno;
	
	
	public CursoReferencia_Alumno(Curso_Referencia refCursoReferencia, Alumno refAlumno) {
		super();
		this.refCursoReferencia = refCursoReferencia;
		this.refAlumno = refAlumno;
	}


	public Curso_Referencia getRefCursoReferencia() {
		return refCursoReferencia;
	}


	public void setRefCursoReferencia(Curso_Referencia refCursoReferencia) {
		this.refCursoReferencia = refCursoReferencia;
	}


	public Alumno getRefAlumno() {
		return refAlumno;
	}


	public void setRefAlumno(Alumno refAlumno) {
		this.refAlumno = refAlumno;
	}
	
	

	
	

}
