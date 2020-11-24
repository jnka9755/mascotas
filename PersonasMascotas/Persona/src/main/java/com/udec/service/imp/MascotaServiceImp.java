package com.udec.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.udec.dto.MascotaCuidadorDto;
import com.udec.entity.Mascota;
import com.udec.entity.MascotaCuidador;
import com.udec.exception.ArgumentRequiredException;
import com.udec.exception.FoundModelException;
import com.udec.exception.NotFoundModelException;
import com.udec.repository.IMascotaCuidadorRepo;
import com.udec.repository.IMascotaRepo;
import com.udec.repository.IPersonaRepo;
import com.udec.service.IMascotaService;

@Service
public class MascotaServiceImp implements IMascotaService {

	@Autowired
	private IMascotaRepo repo;
	@Autowired
	private IPersonaRepo repoPersona;
	@Autowired
	private IMascotaCuidadorRepo repoMascotaCuidador;
	
	@Override
	public Page<Mascota> listarPaginado(Integer page, Integer size) {

		return repo.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "nombre").ascending()));
	}

	@Override
	public Mascota listar(Integer id) {

		Mascota mascota = repo.findById(id).orElseThrow(() -> new NotFoundModelException("Mascota no encontrada"));
		return mascota;
	}

	@Override
	public void guardar(Mascota entity) {

		if (entity.getPersona() != null && entity.getPersona().getId() != null) {
			if (repoPersona.buscaPersonaId(entity.getPersona().getId()) == 0) {
				throw new NotFoundModelException("Persona no se encuentra registrada");
			} else {
				repo.save(entity);
			}
		} else {
			throw new ArgumentRequiredException("Id persona requerido");
		}
	}

	@Override
	public void guardarMascotaCuidador(MascotaCuidadorDto dto) {
		
		if(dto.getCuidador() == null)
			throw new ArgumentRequiredException("id Cuidador es requerido");
		if(dto.getMascota() == null)
			throw new ArgumentRequiredException("id Mascota es requerido");
		if(repoMascotaCuidador.buscaMascotaCuidador(dto.getCuidador().getId(), dto.getMascota().getId()) > 0)
			throw new FoundModelException("Esta mascota ya tiene asociado este cuidador");
		else
			repoMascotaCuidador.guardar(dto.getInfoAdicional(), dto.getCuidador().getId(), dto.getMascota().getId());
	}
	
	@Override
	public List<MascotaCuidador> listarMascotaCuidador(Integer idMascota){
		
		List<MascotaCuidador> listaMascotas = repoMascotaCuidador.listarMascotaCuidador(idMascota);
		if(listaMascotas != null) {
			
			for (MascotaCuidador item : listaMascotas) {
				item.setMascota(null);
			}
			return listaMascotas;
		}else {
			throw new NotFoundModelException("Mascota no encontrada");
		}
		
	}

	@Override
	public void eliminar(Integer id) {

		this.listar(id);
		repo.deleteById(id);
	}

	@Override
	public void editar(Mascota entity) {

		if (entity.getId() == null) {
			throw new ArgumentRequiredException("id mascota es requerido");
		}
		Mascota mascota = repo.findById(entity.getId())
				.orElseThrow(() -> new NotFoundModelException("Mascota no encontrada"));

		mascota.setNombre(entity.getNombre());
		mascota.setTipo(entity.getTipo());

		repo.save(mascota);
	}
}
