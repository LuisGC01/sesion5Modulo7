package mx.unam.dgtic.sesion1;

import java.util.Objects;

public class AlumnoCalificacionDTO {
	private String nombre;
	private String paterno;
	private String materia;
	private int calificacion;

	public AlumnoCalificacionDTO() {
		// TODO Auto-generated constructor stub
	}

	public AlumnoCalificacionDTO(String nombre, String paterno, String materia, int calificacion) {
		this.nombre = nombre;
		this.paterno = paterno;
		this.materia = materia;
		this.calificacion = calificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPaterno() {
		return paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	@Override
	public String toString() {
		return "AlumnoCalificacionDTO [nombre=" + nombre + ", paterno=" + paterno + ", materia=" + materia
				+ ", calificacion=" + calificacion + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(calificacion, materia, nombre, paterno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlumnoCalificacionDTO other = (AlumnoCalificacionDTO) obj;
		return calificacion == other.calificacion && Objects.equals(materia, other.materia)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(paterno, other.paterno);
	}
}
