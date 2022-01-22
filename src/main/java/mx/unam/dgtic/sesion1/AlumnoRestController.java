package mx.unam.dgtic.sesion1;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoRestController {
	@Autowired
	private AlumnoRepository alumnoRepository;

	/*
	 * @GetMapping public Iterable<Alumno> findAll() { return
	 * alumnoRepository.findAll(); }
	 */
	@GetMapping
	public Iterable<Alumno> findAll(@RequestParam Map<String, String> parametros) {
		Iterable<Alumno> alumnos = null;
		int leidos = 0;

		if (parametros.containsKey("nombre")) {
			alumnos = alumnoRepository.findByNombreContaining(parametros.get("nombre"));
			leidos = 1;
		}
		if (parametros.containsKey("paterno")) {
			alumnos = alumnoRepository.findByPaternoContaining(parametros.get("paterno"));
			leidos = 1;
		}
		if (parametros.containsKey("estura")) {
			alumnos = alumnoRepository.findByEstaturaGreaterThanEqual(Double.parseDouble(parametros.get("estatura")));
			leidos = 1;
		}

		if (leidos == 0) {
			alumnos = alumnoRepository.findAll();
		}

		if (parametros.containsKey("nombre") && parametros.containsKey("paterno")) {
			alumnos = alumnoRepository.getByNombreAndPaterno(parametros.get("nombre"), parametros.get("paterno"));
			leidos = 1;
		}

		return alumnos;
	}

	@GetMapping("/nombre/{nombre}")
	public List<Alumno> getByNombre(@PathVariable String nombre) {
		return alumnoRepository.findByNombreContaining(nombre);
	}

	@GetMapping("/{matricula}")
	public ResponseEntity<Alumno> buscarMatricula(@PathVariable String matricula) {
		Optional<Alumno> optional = alumnoRepository.findById(matricula);
		if (optional.isPresent()) {
			Alumno alumno = optional.get();
			return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
		} else {
			return new ResponseEntity<Alumno>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{matricula}")
	public ResponseEntity<Alumno> eliminarMatricula(@PathVariable String matricula) {
		Optional<Alumno> optional = alumnoRepository.findById(matricula);
		if (optional.isPresent()) {
			alumnoRepository.deleteById(matricula);
			return new ResponseEntity<Alumno>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Alumno>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void insertarMatricula(@RequestBody Alumno alumno) {
		alumnoRepository.save(alumno);
	}

	@PutMapping("/{matricula}")
	@ResponseStatus(HttpStatus.OK)
	public void acutalizarMatricula(@PathVariable String matricula, @RequestBody Alumno alumno) {
		Optional<Alumno> optional = alumnoRepository.findById(matricula);
		if (optional.isPresent()) {
			Alumno a = alumno;
			a.setMatricula(matricula);
			alumnoRepository.save(a);
		}
	}

	/*
	 * { "matricula": "5a", "nombre": "Javier", "paterno": "Amaro", "fnac":
	 * "2001-02-10T06:00:00.000+00:00", "estatura": 1.75, "calificaciones": [] }
	 */
}
