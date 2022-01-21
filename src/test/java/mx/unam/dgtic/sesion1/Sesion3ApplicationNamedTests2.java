package mx.unam.dgtic.sesion1;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
//@Sql({"/schema.sql","/data.sql"})

// Luis Angel Garcia Cervantes
class Sesion3ApplicationNamedTests2 {

	public static final String MATERIA = "BD";
	public static final int CALIFICACION = 8;

	@Autowired
	AlumnoRepository repositorioAlummno;

	@Autowired
	CalificacionRepository repositorioCalificacion;

	@Test
	void buscarAltosTest() {
		List<Alumno> alumnos = repositorioAlummno.buscarAltos();
		System.out.println("buscarAltos");
		alumnos.forEach(System.out::println);

		try {
			alumnos = repositorioAlummno.buscarAltosConFecha(new SimpleDateFormat("yyyy-MM-dd").parse("2001-01-01"));
			System.out.println("buscarAltosConFecha");
			alumnos.forEach(System.out::println);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertThat(alumnos.size(), greaterThan(0));
	}

	@Test
	void calcularMediaAlturaAlumnoTest() {
		double media = repositorioAlummno.buscarEstaturaPromedioAlumnos();
		System.out.println("buscarEstaturaPromedioAlumnos " + media);
		assertThat(media, greaterThan(1.0));
	}

	@Test
	void buscarTodosNativo() {
		List<Alumno> alumnos = repositorioAlummno.buscarTodosNative();
		System.out.println("buscarTodosNative");
		alumnos.forEach(System.out::println);

		alumnos = repositorioAlummno.buscarPorNombreAndPaternoNativeNative("Perla", "Calles");
		System.out.println("buscarPorNombreAndPaternoNativeNative");
		alumnos.forEach(System.out::println);

		assertThat(alumnos.size(), greaterThan(0));
	}

	@Test
	void buscarAlturaMayorPromedioTest() {
		List<Alumno> alumnos = repositorioAlummno.buscarAlturaMayorPromedio();
		System.out.println("buscarAlturaMayorPromedio");
		alumnos.forEach(System.out::println);

		assertThat(alumnos.size(), greaterThan(0));
	}
	@Test
	void buscarTodoCalificacionesTest() {
		List<Alumno> alumnos = repositorioAlummno.buscarTodoConCalificaciones();
		System.out.println("buscarTodoConCalificaciones");
		alumnos.forEach(System.out::println);
		
		for (Alumno alumno : alumnos) {
			System.out.println(alumno.getNombre());
			List<Calificacion> calificacions = alumno.getCalificaciones();
			//caclificacions.forEach(System.out::println);
			for (Calificacion c: calificacions) {
				System.out.println(c.getMateria()+" "+c.getCalificacion());
			}
		}
		
		assertThat(alumnos.size(), greaterThan(0));
	}

}
