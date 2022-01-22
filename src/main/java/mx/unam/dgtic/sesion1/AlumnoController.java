package mx.unam.dgtic.sesion1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

	@RequestMapping(value = "buscartodos", params = "orden")
	public String buscarTodos(@RequestParam(name = "orden", defaultValue = "matricula") String orden, Model modelo) {
		Iterable<Alumno> alumnos = null;
		alumnos = repositorioAlumno.findAll(Sort.by(orden));
		modelo.addAttribute("alumnos", alumnos);
		return "alumnos";
	}

	// crud

	@RequestMapping("/nuevo")
	public String nuevoAlumno(Model modelo) {
		Alumno alumno = new Alumno();
		modelo.addAttribute("alumno", alumno);
		return "alumno_nuevo";
	}

	@RequestMapping("/editar/{matricula}")
	public ModelAndView editarAlumno(@PathVariable(name = "matricula") String matricula) {

		ModelAndView mav = new ModelAndView("alumno_editar");
		Alumno alumno = null;

		Optional<Alumno> o = repositorioAlumno.findById(matricula);
		if (o.isPresent()) {
			alumno = o.get();
		}

		mav.addObject("alumno", alumno);

		return mav;
	}

	@RequestMapping("/eliminar/{matricula}")
	public String eliminarAlumno(@PathVariable(name = "matricula") String matricula) {
		Optional<Alumno> o = repositorioAlumno.findById(matricula);
		if (o.isPresent()) {
			repositorioAlumno.deleteById(matricula);
		}
		return "redirect:/alumnos/buscartodos";
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String nuevoAlumno(@ModelAttribute("alumno") Alumno alumno) {
		repositorioAlumno.save(alumno);
		return "redirect:/buscartodos";
	}

}
