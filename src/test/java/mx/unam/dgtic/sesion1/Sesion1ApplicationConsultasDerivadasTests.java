package mx.unam.dgtic.sesion1;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
class Sesion1ApplicationConsultasDerivadasTests {

	private static final String NOMBRE = "Maria";
	private static final String SUFIX = "Paterno";
	private static final double Estatura = 1.65;

	@Autowired
	AlumnoRepository repositorioAlummno;

	@Test
	void buscarPorNombreTest() {
		// Iterable<Alumno> iterable = repositorioAlummno.findByNombre(NOMBRE);
		Iterable<Alumno> iterable = repositorioAlummno.getByNombre(NOMBRE);
		List<Alumno> alumnos = new ArrayList<Alumno>();
		System.out.println("getByNombre");
		iterable.forEach(alumnos::add);
		iterable.forEach(System.out::println);
		assertThat(alumnos, hasItem(new Alumno("G1")));
		// assertThat(alumnos.size(), greaterThan(1));

	}

	@Test
	void buscarPorPaternoTest() {
		// Iterable<Alumno> iterable = repositorioAlummno.findByNombre(NOMBRE);
		Iterable<Alumno> iterable = repositorioAlummno.searchByPaterno("Madero");
		List<Alumno> alumnos = new ArrayList<Alumno>();
		System.out.println("searchByPaterno");
		iterable.forEach(alumnos::add);
		iterable.forEach(System.out::println);
		// assertThat(alumnos, hasItem(new Alumno("G1")));
		assertThat(alumnos.size(), greaterThan(0));

	}

	@Test
	void buscarPorEstaturaTest() {
		// Iterable<Alumno> iterable = repositorioAlummno.findByNombre(NOMBRE);
		Iterable<Alumno> iterable = repositorioAlummno.streamByEstatura(Estatura);
		List<Alumno> alumnos = new ArrayList<Alumno>();
		System.out.println("streamByEstatura");
		iterable.forEach(alumnos::add);
		iterable.forEach(System.out::println);
		// assertThat(alumnos, hasItem(new Alumno("G1")));
		assertThat(alumnos.size(), greaterThan(0));

	}

	@Test
	void buscarPorNotNombreTest() {
		// Iterable<Alumno> iterable = repositorioAlummno.findByNombre(NOMBRE);
		Iterable<Alumno> iterable = repositorioAlummno.findByNombreNot(NOMBRE);
		List<Alumno> alumnos = new ArrayList<Alumno>();
		System.out.println("findByNombreNot");
		iterable.forEach(alumnos::add);
		iterable.forEach(System.out::println);
		// assertThat(alumnos, hasItem(new Alumno("G1")));
		assertThat(alumnos.size(), greaterThan(0));
		System.out.println("Not Nombre encontrando " + repositorioAlummno.findByNombreNot(NOMBRE).size());
	}

	@Test
	void buscarPorPaternoNullTest() {
		// Iterable<Alumno> iterable = repositorioAlummno.findByNombre(NOMBRE);
		Iterable<Alumno> iterable = repositorioAlummno.findByPaternoIsNull();
		List<Alumno> alumnos = new ArrayList<Alumno>();
		System.out.println("findByPaternoIsNull");
		iterable.forEach(alumnos::add);
		iterable.forEach(System.out::println);
		// assertThat(alumnos, hasItem(new Alumno("G1")));
		assertThat(alumnos.size(), greaterThan(0));
	}

	@Test
	void buscarPorPaternoSimilitudTest() {
		// Iterable<Alumno> iterable = repositorioAlummno.findByNombre(NOMBRE);
		Iterable<Alumno> iterable = repositorioAlummno.findByPaternoStartingWith("P");
		List<Alumno> alumnos = new ArrayList<Alumno>();
		System.out.println("findByPaternoStartingWith");
		iterable.forEach(alumnos::add);
		iterable.forEach(System.out::println);
		// assertThat(alumnos, hasItem(new Alumno("G1")));

		iterable = repositorioAlummno.findByPaternoEndingWith("P");
		System.out.println("findByPaternoEndingWith");
		iterable.forEach(alumnos::add);
		iterable.forEach(System.out::println);

		iterable = repositorioAlummno.findByPaternoContaining("P");
		System.out.println("findByPaternoContaining");
		iterable.forEach(alumnos::add);
		iterable.forEach(System.out::println);

		assertThat(alumnos.size(), greaterThan(0));
	}

	@Test
	void buscarPorPaternoLikeTest() {
		// Iterable<Alumno> iterable = repositorioAlummno.findByNombre(NOMBRE);
		Iterable<Alumno> iterable = repositorioAlummno.findByPaternoLike("P% %3");
		List<Alumno> alumnos = new ArrayList<Alumno>();
		System.out.println("findByPaternoLike");
		iterable.forEach(alumnos::add);
		iterable.forEach(System.out::println);

		iterable = repositorioAlummno.findByPaternoNotLike("P% %3");
		alumnos = new ArrayList<Alumno>();

		System.out.println("findByPaternoNotLike");
		iterable.forEach(alumnos::add);
		iterable.forEach(System.out::println);

		// assertThat(alumnos, hasItem(new Alumno("G1")));
		assertThat(alumnos.size(), greaterThan(0));

	}

	@Test
	void buscarPorEstaturaMayorTest() {
		// Iterable<Alumno> iterable = repositorioAlummno.findByNombre(NOMBRE);
		Iterable<Alumno> iterable = repositorioAlummno.findByEstaturaGreaterThan(Estatura);
		List<Alumno> alumnos = new ArrayList<Alumno>();
		System.out.println("streamByEstatura");
		iterable.forEach(alumnos::add);
		iterable.forEach(System.out::println);
		// assertThat(alumnos, hasItem(new Alumno("G1")));
		assertThat(alumnos.size(), greaterThan(0));

	}

	@Test
	void buscarPorEstaturaRangoInTest() {
		// Iterable<Alumno> iterable = repositorioAlummno.findByNombre(NOMBRE);
		Iterable<Alumno> iterable = repositorioAlummno.findByEstaturaBetween(Estatura, Estatura + 0.9);
		List<Alumno> alumnos = new ArrayList<Alumno>();
		System.out.println("findByEstaturaBetween");
		iterable.forEach(alumnos::add);
		iterable.forEach(System.out::println);

		final List<Double> estaturas = Arrays.asList(1.53, Estatura, 1.68);

		iterable = repositorioAlummno.findByEstaturaIn(estaturas);
		alumnos = new ArrayList<Alumno>();
		System.out.println("findByEstaturaIn");
		iterable.forEach(alumnos::add);
		iterable.forEach(System.out::println);

		iterable = repositorioAlummno.findByEstaturaNotIn(estaturas);
		alumnos = new ArrayList<Alumno>();
		System.out.println("findByEstaturaNotIn");
		iterable.forEach(alumnos::add);
		iterable.forEach(System.out::println);

		// assertThat(alumnos, hasItem(new Alumno("G1")));
		assertThat(alumnos.size(), greaterThan(0));

	}

	@Test
	void buscarPorFechaTest() {
		// Iterable<Alumno> iterable = repositorioAlummno.findByNombre(NOMBRE);
		List<Alumno> iterable;
		try {
			iterable = repositorioAlummno.findByFnacBefore(new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-08"));
			System.out.println("findByFnacBefore");
			iterable.forEach(System.out::println);
			// assertThat(alumnos, hasItem(new Alumno("G1")));

			iterable = repositorioAlummno.findByFnacAfter(new SimpleDateFormat("yyyy-MM-dd").parse("2021-03-20"));
			System.out.println("findByFnacAfter");
			iterable.forEach(System.out::println);

			assertThat(iterable.size(), greaterThan(0));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void buscarPorNombrePaternoTest() {
		// Iterable<Alumno> iterable = repositorioAlummno.findByNombre(NOMBRE);
		List<Alumno> alumnos = repositorioAlummno.getByNombreAndPaterno(NOMBRE, "Rios");

		System.out.println("getByNombreAndPaterno");
		alumnos.forEach(System.out::println);

		alumnos = repositorioAlummno.findByNombreOrPaterno(NOMBRE, "Rios");

		System.out.println("findByNombreOrPaterno");
		alumnos.forEach(System.out::println);
		// assertThat(alumnos, hasItem(new Alumno("G1")));
		assertThat(alumnos.size(), greaterThan(1));

	}

	@Test
	void buscarPorNombreOrdenPaternoTest() {
		// Iterable<Alumno> iterable = repositorioAlummno.findByNombre(NOMBRE);
		List<Alumno> alumnos = repositorioAlummno.findByNombreOrderByPaternoDesc(NOMBRE);

		System.out.println("findByNombreOrderByPaternoDesc");
		alumnos.forEach(System.out::println);

		alumnos = repositorioAlummno.findByNombreOrderByPaterno(NOMBRE);

		System.out.println("findByNombreOrderByPaterno");
		alumnos.forEach(System.out::println);
		// assertThat(alumnos, hasItem(new Alumno("G1")));
		assertThat(alumnos.size(), greaterThan(1));

	}

	@Test
	void LimiteTest() {
		// Iterable<Alumno> iterable = repositorioAlummno.findByNombre(NOMBRE);
		List<Alumno> alumnos = repositorioAlummno.findFirstByOrderByEstatura();

		System.out.println("findFirstByOrderByEstatura");
		alumnos.forEach(System.out::println);

		alumnos = repositorioAlummno.findTopByOrderByEstaturaDesc();

		System.out.println("findTopByOrderByEstaturaDesc");
		alumnos.forEach(System.out::println);
		// assertThat(alumnos, hasItem(new Alumno("G1")));
		assertThat(alumnos.size(), greaterThan(0));
	}

	@Test
	void ContarTest() {
		// Iterable<Alumno> iterable = repositorioAlummno.findByNombre(NOMBRE);
		long count = repositorioAlummno.countByEstatura(Estatura);
		System.out.println("countByEstatura " + count);

		count = repositorioAlummno.countByEstaturaGreaterThan(Estatura);
		System.out.println("countByEstaturaGreaterThan " + count);
		count = repositorioAlummno.countByNombreEndingWith("a");
		System.out.println("countByNombreEndingWith " + count);
		count = repositorioAlummno.countByNombreLike("%i%");
		System.out.println("countByNombreLike " + count);

		assertTrue(count > 0);
		// assertThat(alumnos, hasItem(new Alumno("G1")));
		// assertThat(alumnos.size(), greaterThan(0));
	}
	@Test
	void distinctTest() {
		// Iterable<Alumno> iterable = repositorioAlummno.findByNombre(NOMBRE);
		List<Alumno> alumnos  = repositorioAlummno.findAlumnoDistinctByNombre(NOMBRE);

		System.out.println("findAlumnoDistinctByNombre");
		alumnos.forEach(System.out::println);
		//assertThat(alumnos, hasItem(new Alumno("G1")));
		 assertThat(alumnos.size(), greaterThan(0));

	}
}
