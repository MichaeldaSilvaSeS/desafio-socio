package br.com.api.socio.controller.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class SocioDto {
	@Null
	private Long id_socio;
	@NotBlank
	@Length(max=255)
	private String nome_completo;
	@NotBlank
	@Length(max=255)
	@Email
	private String email;
	@NotBlank
	@Length(min=10,max=10)
	private String data_de_nascimento;
	@NotNull
	private Long id_time_do_coracao;
	@Null
	private String status;	
}
