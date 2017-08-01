package br.com.api.socio.model;

import lombok.Getter;

public enum SocioStatus {
	ATIVO("ATIVO"), INATIVO("INATIVO");
	
	@Getter
	private String value;
	
	private SocioStatus(String value) {
		this.value = value;
	}	
}
