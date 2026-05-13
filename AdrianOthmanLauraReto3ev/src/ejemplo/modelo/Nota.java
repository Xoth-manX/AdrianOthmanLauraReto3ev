package modelo;

public class Nota {
	private int id;
	private String asignatura;
	private double calificacion;
	private int idAlumno;
	// constructores, getters, setters, toString...
	public Nota(int id, String asignatura, double calificacion, int idAlumno) {
		super();
		this.id = id;
		this.asignatura = asignatura;
		this.calificacion = calificacion;
		this.idAlumno = idAlumno;
	}
	public Nota() {
		super();
	}
	public Nota(String asignatura, double calificacion, int idAlumno) {
		super();
		this.asignatura = asignatura;
		this.calificacion = calificacion;
		this.idAlumno = idAlumno;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	public double getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}
	public int getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}
	@Override
	public String toString() {
		return "Nota [id=" + id + ", asignatura=" + asignatura + ", calificacion=" + calificacion + ", idAlumno="
				+ idAlumno + "]";
	}
	
}
