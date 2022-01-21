package mx.unam.dgtic.sesion1;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql({"/schema.sql","/data.sql"})

// Luis Angel Garcia Cervantes
class Sesion1ApplicationTests {

	@Autowired
	AlumnoRepository repositorioAlummno;

	@Test
	void buscarTodosTest() {
		Iterable<Alumno> iterable = repositorioAlummno.findAll();
		List<Alumno> alumnos = new ArrayList<Alumno>();
		System.out.println("findAll");
		iterable.forEach(alumnos::add);
		iterable.forEach(System.out::println);
		assertThat(alumnos.size(), greaterThan(6));
	}

	@Test
	void buscarTodosUnoTest() {
		Optional<Alumno> optional = repositorioAlummno.findById("2A");
		System.out.println("findById");
		if (optional.isPresent()) {
			assertThat(optional.get().getNombre(), equalTo("Nadia"));
			System.out.println(optional.get().toString());
		}
	}

	@Test
	void InsertarUnoTest() {
		Alumno alumno = new Alumno("1F", "Luis", "Garcia", new Date(), 0);
		repositorioAlummno.save(alumno);
		Optional<Alumno> optional = repositorioAlummno.findById("1F");

		System.out.println("Save");
		if (optional.isPresent()) {
			assertThat(optional.get().getNombre(), equalTo("Luis"));
			System.out.println(optional.get().toString());
		}
	}

	@Test
	void borrarUnoTest() {
		Alumno alumno = new Alumno("1F");
		repositorioAlummno.delete(alumno);
		Optional<Alumno> optional = repositorioAlummno.findById("1F");
		System.out.println("Delete");
		assertFalse(optional.isPresent());
	}

	@Test
	void actualizarrUnoTest() {
		// Alumno alumno = new Alumno("3B");
		Optional<Alumno> optional = repositorioAlummno.findById("3B");
		if (optional.isPresent()) {
			Alumno alumno = new Alumno();
			alumno = optional.get();
			alumno.setPaterno("Rios");
			repositorioAlummno.save(alumno);

			System.out.println("Update");
			optional = repositorioAlummno.findById("3B");
			System.out.println(optional.get().toString());
			assertThat(optional.get().getPaterno(), equalTo("Rios"));

		}
	}

	@Test
	void buscarVarioTest() {
		Iterable<Alumno> iterable = repositorioAlummno.findAllById(List.of("2A", "3B"));
		List<Alumno> alumnos = new ArrayList<Alumno>();
		System.out.println("findAllById");
		iterable.forEach(alumnos::add);
		iterable.forEach(System.out::println);
		assertThat(alumnos.get(0).getNombre(), equalTo("Nadia"));
	}
	
	@Test
	void InsertarVariosTest() {

		List<Alumno> listaAlumnos = new ArrayList<Alumno>();
		for (int i = 0; i < 6; i++) {
			listaAlumnos.add(new Alumno("G"+i,"Maria","Paterno "+i,new Date(),1.60+(i/10)));
		}
		repositorioAlummno.saveAll(listaAlumnos);
		//Alumno alumno = new Alumno("1F", "Luis", "Garcia", new Date(), 0);
		//repositorioAlummno.save(alumno);
		Optional<Alumno> optional = repositorioAlummno.findById("G1");

		System.out.println("SaveAll");
		if (optional.isPresent()) {
			assertThat(optional.get().getNombre(), equalTo("Maria"));
			System.out.println("Numero de alumnos: "+repositorioAlummno.count());
		}
	}
	
}
