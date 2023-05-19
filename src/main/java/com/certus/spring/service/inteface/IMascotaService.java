package com.certus.spring.service.inteface;

import org.springframework.web.multipart.MultipartFile;

import com.certus.spring.models.Mascota;
import com.certus.spring.models.Response;
import com.certus.spring.models.dto.MascotaDTO;

public interface IMascotaService {
	
	public Response<Mascota> crearMascota(Mascota p,  MultipartFile fileRecibido);
	
	public Response<Mascota> crearMascotaAPI(MascotaDTO p);
	
	public Response<Mascota> editarMascota(Integer ID);	
	
	public Response<Mascota> eliminarMascota(Integer ID);	
	
	public Response<Mascota> listarMascota();

}
