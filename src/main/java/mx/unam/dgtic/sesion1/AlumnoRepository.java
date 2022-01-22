package mx.unam.dgtic.sesion1;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlumnoRepository extends PagingAndSortingRepository<Alumno, String> {
	// Metodos de busqueda

	// public List<Alumno> findByNombre(String nombre);
	public List<Alumno> getByNombre(String nombre);

	// public List<Alumno> getByNombreIs(String nombre);
	// public List<Alumno> getByNombreEquals(String nombre);
	// public List<Alumno> findByNombreIs(String nombre);

	public List<Alumno> searchByPaterno(String paterno);

	public List<Alumno> streamByEstatura(double estatura);

	// Negacion
	public List<Alumno> findByNombreNot(String nombre);

	// Null
	public List<Alumno> findByPaternoIsNotNull();

	public List<Alumno> findByPaternoIsNull();

	// similitud
	public List<Alumno> findByPaternoStartingWith(String prefix);

	public List<Alumno> findByPaternoEndingWith(String prefix);

	public List<Alumno> findByPaternoContaining(String prefix);
	public List<Alumno> findByNombreContaining(String prefix);

	public List<Alumno> findByPaternoLike(String prefix);

	public List<Alumno> findByPaternoNotLike(String prefix);

	// comparacion
	// public List<Alumno> findByEstaturaIsLessThan(double estatura);
	public List<Alumno> findByEstaturaLessThan(double estatura);

	public List<Alumno> findByEstaturaLessThanEqual(double estatura);

	public List<Alumno> findByEstaturaGreaterThan(double estatura);

	public List<Alumno> findByEstaturaGreaterThanEqual(double estatura);

	public List<Alumno> findByEstaturaBetween(double estaturaini, double estaturafin);

	public List<Alumno> findByEstaturaIn(Collection<Double> estatura);

	public List<Alumno> findByEstaturaNotIn(Collection<Double> estatura);

	// fecha
	// public List<Alumno> findByFnacIsAfter(Date fecha);
	public List<Alumno> findByFnacAfter(Date fecha);

	public List<Alumno> findByFnacBefore(Date fecha);

	// AND / OR
	public List<Alumno> getByNombreAndPaterno(String nombre, String paterno);

	public List<Alumno> findByNombreOrPaterno(String nombre, String paterno);

	// orden
	public List<Alumno> findByNombreOrderByPaterno(String nombre);

	public List<Alumno> findByNombreOrderByPaternoDesc(String nombre);

	// Limit
	public List<Alumno> findFirstByOrderByEstatura();

	public List<Alumno> findTopByOrderByEstaturaDesc();

	// count
	public long countByEstatura(double estatura);

	public long countByEstaturaGreaterThan(double estatura);

	public long countByNombreEndingWith(String termina);

	public long countByNombreLike(String pattern);

	// Distinct

	public List<Alumno> findAlumnoDistinctByNombre(String nombre);

	// NamedQuery
	public List<Alumno> buscarAltos();

	public List<Alumno> buscarAltosConFecha(Date fecha);

	public List<Alumno> buscarPorNombre(String nombre);

	public List<Alumno> buscarPorNombreAndPaterno(String nombre, String paterno);

	// Query
	@Query("select avg(a.estatura) from Alumno a")
	public double buscarEstaturaPromedioAlumnos();

	@Query(value = "select * from Alumnos order by nombre", nativeQuery = true)
	public List<Alumno> buscarTodosNative();

	@Query(value = "select * from Alumnos where nombre = ? and paterno = ? order by nombre", nativeQuery = true)
	public List<Alumno> buscarPorNombreAndPaternoNativeNative(String nombre, String paterno);

	// @NamedNativeQuery
	public List<Alumno> buscarAlturaMayorPromedio();

	// join fetch
	public List<Alumno> buscarTodoConCalificaciones();

	//DTO
	@Query(value = "select distinct new mx.unam.dgtic.sesion1.AlumnoCalificacionDTO(a.nombre,a.paterno,c.materia,c.calificacion) from Alumno a, Calificacion c where a.matricula = c.alumno")
	public List<AlumnoCalificacionDTO> findAlumnoCalificacionDTO();
	
	//orden 2
	public List<Alumno> findByOrderByNombre();

	public List<Alumno> findByOrderByNombreAscPaternoDesc();

}
