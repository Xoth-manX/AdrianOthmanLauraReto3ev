package modelo;

public class Alumno {
    private int id;
    private String nombre;
    private int edad;
    private String curso;
    private boolean repetidor;
	// Constructor vacío
    public Alumno() {}
    
    // Constructor completo
    public Alumno(int id, String nombre, int edad, String curso, boolean repetidor) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.curso = curso;
        this.repetidor = repetidor;
    }
    
   // Constructor sin ID (para INSERT)
    public Alumno(String nombre, int edad, String curso, boolean repetidor) {
        this(0, nombre, edad, curso, repetidor);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public boolean isRepetidor() { return repetidor; }
    public void setRepetidor(boolean repetidor) { this.repetidor = repetidor; }

    @Override
    public String toString() {
        return "Alumno id=" + id + ", nombre=" + nombre + " edad=" + edad + " curso=" + curso + " repetidor=" + repetidor;
    }
}
