package br.com.api.socio.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import br.com.api.socio.transfer.CampanhaDto;
import br.com.api.socio.transfer.PossuiCampanhasDto;
import lombok.Getter;

@Service
public class CampanhaService {
	@Autowired
	private RestTemplate restTemplate;
	
	@Getter
	private HttpEntity<String> httpSimpleEntity;

	public CampanhaService() {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<>();
		requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		this.httpSimpleEntity = new HttpEntity<>(requestHeaders);
	}
	
	public boolean verificaSeExiste(Long idSocio){
		Map<String,String> urlsParam = new HashMap<>();
		urlsParam.put("idSocio", "1");
		
		try {
			ResponseEntity<PossuiCampanhasDto> responseEntity =
					restTemplate.exchange("http://localhost:8181/campanha/associado/{idSocio}", HttpMethod.GET, getHttpSimpleEntity(), PossuiCampanhasDto.class , urlsParam);
			
			if(responseEntity == null ||  responseEntity.getStatusCodeValue() < 200 || responseEntity.getStatusCodeValue() > 299)
				return false;
				
			return responseEntity.getBody().isPossui_campanhas();
		} catch (Exception e) {
			return false;
		}
	}
	
	public Collection<CampanhaDto> listarCampanhas(Long idSocio){
		try {
			ResponseEntity<Collection<CampanhaDto>> responseEntity =
					restTemplate.exchange("http://localhost:8181/v1/campanha/", HttpMethod.GET, getHttpSimpleEntity(), 
							new ParameterizedTypeReference<Collection<CampanhaDto>>(){});
			
			if(responseEntity.getStatusCodeValue() < 200 || responseEntity.getStatusCodeValue() > 299)
				return Collections.<CampanhaDto>emptyList();
			
			return responseEntity.getBody(); 
		} catch (Exception e) {
			return Collections.<CampanhaDto>emptyList();
		}
	}
	
}