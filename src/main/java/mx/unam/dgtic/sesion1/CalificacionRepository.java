package mx.unam.dgtic.sesion1;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CalificacionRepository extends CrudRepository<Calificacion, Integer> {
	List<Calificacion> findByMateria(String materia);
	List<Calificacion> findByCalificacion(int calificicion);
	
	List<Calificacion> findByAlumnoNombre(String nombre);
	List<Calificacion> findByAlumnoPaterno(String paterno);
}
