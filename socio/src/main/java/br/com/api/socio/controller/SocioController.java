package br.com.api.socio.controller;

import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.socio.controller.dto.SocioDto;
import br.com.api.socio.model.Socio;
import br.com.api.socio.platform.mapper.MapModel;
import br.com.api.socio.platform.mapper.MapModel.Mapper;
import br.com.api.socio.service.SocioService;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/v1/socio")
@RestController
public class SocioController {
	
	@Autowired
	private SocioService socioService;
	
	@Autowired
	@MapModel(Mapper.DTO_TO_MODEL)
	private ModelMapper mapperDtoToModel;
	
	@Autowired
	@MapModel(Mapper.MODEL_TO_DTO)
	private ModelMapper mapperModelToDto;
	
	@ApiOperation(value = "Salva socio", produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value="/", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public void adiciona(@RequestBody SocioDto socioDto) {
		Socio socio = mapperDtoToModel.map(socioDto,Socio.class);
		socioService.adiciona(socio);
	}
	
	@ApiOperation(value = "Atualiza informacoes do socio", produces = MediaType.APPLICATION_JSON_VALUE)
    @PutMapping(value="/{idSocio}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public void atualiza(@PathVariable Long idSocio, @RequestBody SocioDto socioDto) {
    	socioDto.setId_socio(idSocio);
		socioService.atualiza(mapperDtoToModel.map(socioDto,Socio.class));
    }

	@ApiOperation(value = "Remove registro do socio", produces = MediaType.APPLICATION_JSON_VALUE)
    @DeleteMapping(value="/{idSocio}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public void remove(@PathVariable Long idSocio) {
    	socioService.remove(idSocio);
    }
    
	@ApiOperation(value = "Recupera informacoes do socio", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value="/{idSocio}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public SocioDto selecionaPorId(@PathVariable Long idSocio) {
    	return mapperModelToDto.map(socioService.selecionaPorId(idSocio),SocioDto.class);
    }
    
	@ApiOperation(value = "Recupera informacoes do socio por email", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(name="/email/{email}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public SocioDto selecionaPorEmail(@PathVariable String email) throws Exception {
	    return mapperModelToDto.map(socioService.selecionaPorEmail(email),SocioDto.class);    	
    }
    
	@ApiOperation(value = "Lista do todos socios ativos", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value="/", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Collection<SocioDto> lista(){
		return mapperModelToDto.map(socioService.lista(), new TypeToken<Collection<SocioDto>>() {}.getType());
	}
}