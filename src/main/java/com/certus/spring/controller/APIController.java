package com.certus.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certus.spring.models.Mascota;
import com.certus.spring.models.Response;
import com.certus.spring.models.dto.MascotaDTO;
import com.certus.spring.service.inteface.IMascotaService;

@RestController
@RequestMapping("/Api")
public class APIController {
	
	@Autowired
	@Qualifier("servicio1")
	private IMascotaService InterfaceMascota1;
	
	@GetMapping("/listar")
	public Response<Mascota> listarMascotas (){		
		Response<Mascota> rspta = InterfaceMascota1.listarMascota();	
		return rspta;
	}
	
	@PutMapping("/editar/{id}")
	public Response<Mascota> editarMascota(@RequestBody MascotaDTO per, @PathVariable  int id){
		Response<Mascota> rspta = new Response<>();
		
		Response<Mascota> rsptaAux = InterfaceMascota1.editarMascota(id);
		
		if (rsptaAux.getEstado()) { //Si existe o no la mascota			
			per.setIdMascota(rsptaAux.getData().getIdMascota());			
			rspta = InterfaceMascota1.crearMascotaAPI(per);
		}else {
			rspta = rsptaAux;
		}
		
		return rspta;
	}
	
	@PostMapping("/crear")
	public Response<Mascota> crearMascota (@RequestBody MascotaDTO per){
		
		Response<Mascota> rspta = InterfaceMascota1.crearMascotaAPI(per);
		return rspta;
	}
	
	@DeleteMapping("/eliminar/{id}")
	public Response<Mascota> eliminarMascota (@PathVariable int id){		
		Response<Mascota> rspta = InterfaceMascota1.eliminarMascota(id);		
		return rspta;
	}
	
	

}
