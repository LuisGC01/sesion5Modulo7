package mx.unam.dgtic.sesion1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {
	@Autowired
	private AlumnoRepository repositorioAlumno;

	@RequestMapping(value = "/buscartodos")
	public String buscarTodos(Model modelo) {
		Iterable<Alumno> alumnos = null;
		alumnos = repositorioAlumno.findAll();
		modelo.addAttribute("alumnos", alumnos);
		return "alumnos";
	}

	@RequestMapping(value = "buscartodos", params = "nombre")
	public String buscarTodosPorNombre(String nombre, Model modelo) {
		// Iterable<Alumno> alumnos = null;
		List<Alumno> alumnos = repositorioAlumno.getByNombre(nombre);
		modelo.addAttribute("alumnos", alumnos);
		return "alumnos";
	}

	@RequestMapping(value = "buscartodos", params = "paterno")
	public String buscarTodosPorPaterno(String paterno, Model modelo) {
		// Iterable<Alumno> alumnos = null;
		List<Alumno> alumnos = repositorioAlumno.searchByPaterno(paterno);
		modelo.addAttribute("alumnos", alumnos);
		return "alumnos";
	}

	@RequestMapping(value = "buscartodos", params = { "nombre", "paterno" })
	public String buscarTodosPorNombrePaterno(String nombre, String paterno, Model modelo) {
		// Iterable<Alumno> alumnos = null;
		List<Alumno> alumnos = repositorioAlumno.buscarPorNombreAndPaterno(nombre, paterno);
		modelo.addAttribute("alumnos", alumnos);
		return "alumnos";
	}
	@RequestMapping(value = "buscartodos", params = "orden" )
	public String buscarTodos(@RequestParam(name="orden", defaultValue = "matricula") String orden, Model modelo) {
		Iterable<Alumno> alumnos = null;
		alumnos = repositorioAlumno.findAll(Sort.by(orden));
		modelo.addAttribute("alumnos", alumnos);
		return "alumnos";
	}

}
