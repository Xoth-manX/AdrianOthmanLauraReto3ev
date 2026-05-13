package ejemplo.modelo;

public class Veterinario extends Persona {
	protected int num_veterinario;
	protected String num_colegiado;
	public Veterinario(String dni, String nombre, int num_veterinario, String num_colegiado) {
		super(dni, nombre);
		this.num_veterinario = num_veterinario;
		this.num_colegiado = num_colegiado;
	}
	public Veterinario(String dni, String nombre, String num_colegiado) {
		super(dni, nombre);
		this.num_colegiado = num_colegiado;
	}
	public Veterinario(String dni, String nombre) {
		super(dni, nombre);
	}
	public int getNum_veterinario() {
		return num_veterinario;
	}
	public void setNum_veterinario(int num_veterinario) {
		this.num_veterinario = num_veterinario;
	}
	public String getNum_colegiado() {
		return num_colegiado;
	}
	public void setNum_colegiado(String num_colegiado) {
		this.num_colegiado = num_colegiado;
	}
	@Override
	public String toString() {
		return "Veterinario [num_veterinario=" + num_veterinario + ", num_colegiado=" + num_colegiado + "]";
	}
}
