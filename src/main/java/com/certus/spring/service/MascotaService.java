package com.certus.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.certus.spring.config.MvcConfig;
import com.certus.spring.models.Mascota;
import com.certus.spring.models.Response;
import com.certus.spring.models.ResponseFile;
import com.certus.spring.models.dto.MascotaDTO;
import com.certus.spring.repository.MascotaDAO;
import com.certus.spring.service.inteface.IFileGeneric;
import com.certus.spring.service.inteface.IMascotaService;

@Component("servicio1")
public class MascotaService implements IMascotaService {
	
	@Autowired
	MascotaDAO mascotaRepository;
		
	@Autowired
	IFileGeneric fileGeneric;
	
	@Autowired
	MvcConfig config;

	@Override
	public Response<Mascota> crearMascota(Mascota p,  MultipartFile fileRecibido) {		
		
		Response<Mascota> response = new Response<>();		
		try {
			
			if (!fileRecibido.isEmpty()) {	
				if (p.getUriImagen() != null) {						
					fileGeneric.eliminarFile(p.getUriImagen());
				}
				
				ResponseFile respuesta = fileGeneric.crearFile(fileRecibido);
				if (respuesta.isEstado()) {
					p.setUriImagen(respuesta.getNombreFile());
				}else {
					response.setEstado(false);
					response.setMensaje("Error al procesar el archivo "+respuesta.getNombreFile());
					response.setMensajeError(respuesta.getMensajeError());	
					return response;
				}				
			}
			
			Mascota mascota = mascotaRepository.save(p);			
			response.setEstado(true);
			response.setMensaje("La Mascota "+mascota.getNombres()+" ha sido guardado correctamente");
			
		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("Error al crear la mascota "+p.getNombres());
			response.setMensajeError(e.getStackTrace().toString());
		}		
		return response;
	}

	
	@Override
	public Response<Mascota> editarMascota( Integer ID) {

		Response<Mascota> response = new Response<>();
		
		try {
			Optional<Mascota> p = mascotaRepository.findById(ID);
			response.setEstado(true);
			response.setData(p.get());
			
		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("Se produjo un error o la mascota no existe");
			response.setMensajeError(e.getStackTrace().toString());
		}
		
		return response;
	}
	
	
	@Override
	public Response<Mascota> eliminarMascota(Integer ID) {		
		Response<Mascota> response = new Response<>();		
		try {
			Optional<Mascota> p = mascotaRepository.findById(ID);			
			if (p.get().getUriImagen() != null) {						
				fileGeneric.eliminarFile(p.get().getUriImagen());
			}
			
			mascotaRepository.deleteById(ID);
			response.setEstado(true);
			response.setMensaje("La mascota "+p.get().getNombres()+" ha sido eliminado");
			
		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("Se produjo un error o la mascota no existe");
			response.setMensajeError(e.getStackTrace().toString());
		}
				
		return response;
	}


	@Override
	public Response<Mascota> listarMascota() {		
		Response<Mascota> response = new Response<>();		
		try {
			
			response.setListData((List<Mascota>) mascotaRepository.findAll());
			response.setEstado(true);
			response.setMensaje("Mascotas obtenidas correctamente");
			
		} catch (Exception e) {			
			response.setEstado(false);
			response.setMensaje("Error al obtener las mascotas");
			response.setMensajeError(e.getStackTrace().toString());
		}
		return response;
	}

	@Override
	public Response<Mascota> crearMascotaAPI(MascotaDTO p) {		
		
		Response<Mascota> response = new Response<>();		
		try {
			
			if (!p.getFileBase64().isEmpty()) {	
				
				if (p.getUriImagen() != null) {						
					fileGeneric.eliminarFile(p.getUriImagen());
				}
				
				ResponseFile respuesta = fileGeneric.crearFileAPI(p.getFileBase64(), p.getNombreFileExtension());
				if (respuesta.isEstado()) {
					p.setUriImagen(respuesta.getNombreFile());
				}else {
					response.setEstado(false);
					response.setMensaje("Error al procesar el archivo "+respuesta.getNombreFile());
					response.setMensajeError(respuesta.getMensajeError());	
					return response; 
				}				
			}
			
			Mascota Msct = new Mascota(); 
						
			Msct.setIdMascota(p.getIdMascota());
			Msct.setNombres(p.getNombres());
			Msct.setRaza(p.getRaza());
			Msct.setEdad(p.getEdad());
			Msct.setDueño(p.getDueño());
			Msct.setCaso(p.getCaso());
			Msct.setIngreso(p.getIngreso());
			Msct.setUriImagen(p.getUriImagen());
			
			Mascota mascota = mascotaRepository.save(Msct);			
			response.setEstado(true);
			response.setMensaje("La Mascota "+mascota.getNombres()+" ha sido guardado correctamente");
			
		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("Error al crear la mascota "+p.getNombres());
			response.setMensajeError(e.getStackTrace().toString());
		}		
		return response;
	}


	

}
