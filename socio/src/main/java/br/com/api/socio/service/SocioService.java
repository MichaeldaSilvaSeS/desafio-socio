package br.com.api.socio.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.api.socio.exception.SocioJaExiste;
import br.com.api.socio.exception.SocioNaoExiste;
import br.com.api.socio.model.Socio;
import br.com.api.socio.repository.SocioRepository;

@Service
public class SocioService {
	@Autowired
	private SocioRepository socioRepository;
	/*
	@Autowired
	private CampanhaServico campanhaServico;*/

	@Transactional
    public void adiciona(Socio socio) {
		System.out.println(socio);
		if(socioRepository.verificarSeExisteAtivo(socio.getEmail()))
			throw new SocioJaExiste();
		
		socio.setIdSocio(null);
		socio.ativar();
		socio = socioRepository.save(socio);
	}
	
    @Transactional
    public void atualiza(Socio socio) {   
    	if(!socioRepository.verificarSeExiste(socio.getIdSocio()))
    		throw new SocioNaoExiste();
    	
    	socioRepository.save(socio);
    }

    @Transactional
    public void remove(Long idSocio) {
    	if(!socioRepository.verificarSeExiste(idSocio))
    		throw new SocioNaoExiste();    		
    	
    	socioRepository.delete(idSocio);
    }
    
    public Socio selecionaPorId(Long idSocio) {
    	if(!socioRepository.verificarSeExiste(idSocio))
    		throw new SocioNaoExiste(); 
    	
    	return socioRepository.selecionar(idSocio);
    }
	
    public Socio selecionaPorEmail(String email) throws Exception {
    	if(!socioRepository.verificarSeExistePorEmail(email))
    		throw new SocioNaoExiste();
    	
    	return socioRepository.selecionarPorEmail(email);
    }
    
	public Collection<Socio> lista(){
		return socioRepository.listar();
	}
}