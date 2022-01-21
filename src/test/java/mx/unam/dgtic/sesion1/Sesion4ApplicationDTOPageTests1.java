package mx.unam.dgtic.sesion1;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.TypedSort;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
//@Sql({"/schema.sql","/data.sql"})

// Luis Angel Garcia Cervantes
class Sesion4ApplicationDTOPageTests1 {

	public static final String MATERIA = "BD";
	public static final int CALIFICACION = 8;

	@Autowired
	AlumnoRepository repositorioAlummno;

	@Autowired
	CalificacionRepository repositorioCalificacion;

	@Test
	void buscarTodosDTOTest() {
		List<AlumnoCalificacionDTO> alumnos = repositorioAlummno.findAlumnoCalificacionDTO();
		System.out.println("findAlumnoCalificacionDTO");
		alumnos.forEach(System.out::println);
		assertThat(alumnos.size(), greaterThan(0));
	}
//	@Test
//	void buscarTodosOrdenadosTest() {
//		List<Alumno> alumnos = repositorioAlummno.findByOrderByNombreAscPaternoDesc();
//		System.out.println("findByOrderByNombreAscPaternoDesc");
//		alumnos.forEach(System.out::println);
//		alumnos =new ArrayList<Alumno>();
//		alumnos = repositorioAlummno.findByOrderByNombre();
//		System.out.println("findByOrderByNombre");
//		alumnos.forEach(System.out::println);
//		assertNotNull(alumnos);
//		
//		Iterable<Alumno> it = repositorioAlummno.findAll();
//		List<Alumno> alumnos2 =new ArrayList<Alumno>();
//		it.forEach(alumnos2::add);
//		
//		List<Alumno> alumnos3 = alumnos2.stream().sorted(Comparator.comparing(Alumno::getNombre)).collect(Collectors.toList());
//		assertEquals(alumnos, alumnos3);
//	}
//	@Test
//	void buscarTodosOrdenadosParametroTest() {
//		Iterable<Alumno> alumnos = repositorioAlummno.findAll(Sort.by("nombre"));
//		List<Alumno> alumnos2 =new ArrayList<Alumno>();
//		System.out.println("sort.by.nombre");
//		alumnos.forEach(alumnos2::add);
//		alumnos2.forEach(System.out::println);
//
//		alumnos = repositorioAlummno.findAll(Sort.by("nombre").descending());
//		alumnos2 =new ArrayList<Alumno>();
//		System.out.println("sort.by.nombre.descending()");
//		alumnos.forEach(alumnos2::add);
//		alumnos2.forEach(System.out::println);
//
//		alumnos = repositorioAlummno.findAll(Sort.by("estatura").descending());
//		alumnos2 =new ArrayList<Alumno>();
//		System.out.println("sort.by.estatura.descending()");
//		alumnos.forEach(alumnos2::add);
//		alumnos2.forEach(System.out::println);
//
//		alumnos = repositorioAlummno.findAll(Sort.by("nombre").ascending().and(Sort.by("paterno").descending()));
//		alumnos2 =new ArrayList<Alumno>();
//		System.out.println("sort.by.nombre.ascending().paterno.descending()");
//		alumnos.forEach(alumnos2::add);
//		alumnos2.forEach(System.out::println);
//		
//		
////		List<Alumno> alumnos3 = alumnos2.stream().sorted(Comparator.comparing(Alumno::getNombre)).collect(Collectors.toList());
//	//	assertEquals(alumnos, alumnos3);
//		assertThat(alumnos2.size(),greaterThan(0));
//	}
//	@Test
//	void buscarTodosOrdenadosTypedTest() {
//		TypedSort<Alumno> ordenar = Sort.sort(Alumno.class);
//		Sort ordenFinal = ordenar.by(Alumno::getNombre).ascending().and(ordenar.by(Alumno::getPaterno).ascending());
//		
//		Iterable<Alumno> it= repositorioAlummno.findAll(ordenFinal);
//		List<Alumno> alumnos2 =new ArrayList<Alumno>();
//		it.forEach(alumnos2::add);
//		System.out.println("TypedSort nombre");
//		alumnos2.forEach(System.out::println);
//		assertThat(alumnos2.size(),greaterThan(0));
//	}
//	@Test
//	void buscarTodosPaginadoTest() {
//
//		//Page<Alumno> pagina = repositorioAlummno.findAll(PageRequest.of(0,2));
//		Page<Alumno> pagina = repositorioAlummno.findAll(PageRequest.of(0,3,Sort.by("nombre").descending()));
//		
//		List<Alumno> alumnos2 =pagina.getContent();
//		System.out.println("Page(0,2)");
//		alumnos2.forEach(System.out::println);
//		
//		Page<Alumno> siguiente= repositorioAlummno.findAll(pagina.nextPageable());		
//		alumnos2=siguiente.getContent();
//		System.out.println("Page 1");
//		alumnos2.forEach(System.out::println);
//		
//		assertThat(alumnos2.size(),greaterThan(0));
//	}

}
