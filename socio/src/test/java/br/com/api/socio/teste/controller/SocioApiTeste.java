package br.com.api.socio.teste.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import br.com.api.socio.teste.ApiTeste;
import br.com.api.socio.transfer.SocioDto;

public class SocioApiTeste extends ApiTeste {
	
	@Test
	public void deveAdicionarSocioERetornarHttp200(){
		SocioDto socioDto = new SocioDto();
		socioDto.setId_socio(null);
		socioDto.setEmail("michael@gmail.com");
		socioDto.setData_de_nascimento("2010-10-10");
		socioDto.setId_time_do_coracao(1L);
		socioDto.setNome_completo("Michael");
		socioDto.setStatus(null);
		
		String url = context();
		ResponseEntity<String> responseEntity = getRestTemplate()
			.postForEntity(url, socioDto , String.class);
		 
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
	}
	
	@Test
	public void deveRetornarHttp400(){
		SocioDto socioDto = new SocioDto();
		socioDto.setId_socio(null);
		socioDto.setEmail("anasilva@gmail.com");
		socioDto.setData_de_nascimento("1990-11-10");
		socioDto.setId_time_do_coracao(1L);
		socioDto.setNome_completo("Ana Silva");
		socioDto.setStatus(null);
		
		String url = context();
		ResponseEntity<String> responseEntity = getRestTemplate()
				.postForEntity(url, socioDto , String.class);
				 
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(400, responseEntity.getStatusCodeValue());
	}

	@Test
	public void deveAlterarSocioERetornarHttp200(){
		Map<String,String> urlsParam = new HashMap<>();
		urlsParam.put("idSocio", "3");
		
		SocioDto socioDto = new SocioDto();
		socioDto.setId_socio(null);
		socioDto.setEmail("mariasilva@gmail.com");
		socioDto.setData_de_nascimento("2010-12-10");
		socioDto.setId_time_do_coracao(1L);
		socioDto.setNome_completo("Maria Silvinha");
		socioDto.setStatus(null);
		
		String url = context().concat("{idSocio}");
		ResponseEntity<String> responseEntity = getRestTemplate()
			.exchange(url, HttpMethod.DELETE, getHttpSimpleEntity(), String.class , urlsParam);
		 
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
	}
	
	@Test
	public void deveRemoverSocioERetornarHttp200(){
		Map<String,String> urlsParam = new HashMap<>();
		urlsParam.put("idSocio", "1");
		
		String url = context().concat("{idSocio}");
		ResponseEntity<String> responseEntity = getRestTemplate()
			.exchange(url, HttpMethod.DELETE, getHttpSimpleEntity() , String.class,urlsParam);
		
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
	}
	
	@Test
	public void deveListarSociosAtivosERetornarHttp200(){
		String url = context();
		ResponseEntity<String> responseEntity = getRestTemplate()
			.exchange(url, HttpMethod.GET, getHttpSimpleEntity() , String.class);
		
        Assert.assertNotNull(responseEntity);
        Assert.assertFalse(responseEntity.getBody().isEmpty());
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
	}
	
	@Test
	public void deveRetornarSocioComHttp200(){
		Map<String,String> urlsParam = new HashMap<>();
		urlsParam.put("idSocio", "1");
		
		String url = context().concat("{idSocio}");
		ResponseEntity<String> responseEntity = getRestTemplate()
			.exchange(url, HttpMethod.GET, getHttpSimpleEntity() , String.class,urlsParam);
		
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
	}
	
	@Test
	public void deveRetornarHttp404(){
		Map<String,String> urlsParam = new HashMap<>();
		urlsParam.put("idSocio", "-1");
		
		String url = context().concat("{idSocio}");
		ResponseEntity<String> responseEntity = getRestTemplate()
			.exchange(url, HttpMethod.GET, getHttpSimpleEntity() , String.class,urlsParam);
		
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(404, responseEntity.getStatusCodeValue());
	}

	@Override
	public String context() {
		return super.context().concat("socio/");
	}
}
