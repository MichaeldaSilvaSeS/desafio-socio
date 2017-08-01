package br.com.api.socio.controller.dto;

import lombok.Data;

@Data
public class SocioDto {
	private Long id_socio;
	private String nome_completo;
	private String email;
	private String data_de_nascimento;
	private Long id_time_do_coracao;
	private String status;	
}
