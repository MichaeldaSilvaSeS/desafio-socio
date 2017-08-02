package br.com.api.socio.transfer;

import java.util.Collection;

import lombok.Data;

@Data
public class CampanhaAssociadoDto {
	private Long id_socio;
	private String nome_completo;
	private String email;
	private String data_de_nascimento;
	private Long id_time_do_coracao;
	private String status;
	private Collection<CampanhaDto> campanhas;
}
