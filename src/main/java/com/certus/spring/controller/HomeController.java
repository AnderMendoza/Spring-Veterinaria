package com.certus.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.certus.spring.models.Mascota;
import com.certus.spring.models.Response;
import com.certus.spring.service.inteface.IMascotaService;

@Controller
@RequestMapping("/app")
@SessionAttributes("mascota")
public class HomeController {

	@Value("${title.generic}")
	private String titlePage;

	@Value("${mensaje}")
	private String mensaje;

	@Autowired
	@Qualifier("servicio1")
	private IMascotaService InterfaceMascota1;

	@GetMapping({ "/home", "/inicio", "/", "/Home", "/Inicio" })
	public String Home(Model model) {
		model.addAttribute("TituloPagina", titlePage);
		model.addAttribute("titulo", "App de Veterinaria");
		model.addAttribute("Mensaje", mensaje);

		return "Home";

	}

	@GetMapping("/listar")
	public String ListarMascotas(Model model) {

		model.addAttribute("TituloPagina", titlePage);
		model.addAttribute("titulo", "App de Veterinaria");
		Response<Mascota> rspta = InterfaceMascota1.listarMascota();

		if (rspta.getEstado()) {
			model.addAttribute("Mensaje", rspta.getMensaje());
			model.addAttribute("listita", rspta.getListData());
			return "lista";
		} else {
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensajeError", rspta.getMensajeError());
			return "errores";
		}
	}

	@GetMapping("/crear")
	public String Formulario(Model model) {
		Mascota mascota = new Mascota();

		model.addAttribute("TituloPagina", titlePage);
		model.addAttribute("titulo", "App de Veterinaria - Crear Mascota");
		model.addAttribute("mascota", mascota);

		return "Formulario";
	}

	@GetMapping("/Editar/{idMascota}")
	public String EditarMascota(@PathVariable int idMascota, Model model) {

		model.addAttribute("TituloPagina", titlePage);

		Response<Mascota> rspta = InterfaceMascota1.editarMascota(idMascota);

		model.addAttribute("titulo", "App de Veterinaria - Editando la mascota " + rspta.getData().getNombres());

		model.addAttribute("mascota", rspta.getData());

		return "Formulario";
	}

	@GetMapping("/Elimnar/{idMascota}")
	public String ElimnarMascota(@PathVariable int idMascota, Model model) {

		Response<Mascota> rspta = InterfaceMascota1.eliminarMascota(idMascota);

		if (rspta.getEstado()) {
			return "redirect:/app/listar";
		} else {
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensajeError", rspta.getMensajeError());

			return "errores";
		}
	}

	@PostMapping("/form")
	public String CrearMascota(@Valid Mascota p, BindingResult result, Model model,
			@RequestParam("ImagenDelFormulario") MultipartFile fileRecibido, SessionStatus sStatus) {

		if (result.hasErrors()) {
			return "Formulario";
		}

		Response<Mascota> rspta = InterfaceMascota1.crearMascota(p, fileRecibido);

		if (rspta.getEstado()) {

			sStatus.setComplete();
			return "redirect:/app/listar";

		} else {
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensajeError", rspta.getMensajeError());
			return "errores";
		}

	}

}
