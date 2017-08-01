package br.com.api.socio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
@Table(name="tbl_socio")
public class Socio implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_socio")
	private Long idSocio;
	
	@Column(name="den_nome_completo")
	private String nomeCompleto;
	
	@Column(name="den_email")
	private String email;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dt_data_de_nascimento")
	private Date dataDeNascimento;
	
	@Column(name="id_time_do_coracao")
	private Long idTimeDoCoracao;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tip_status")
	private SocioStatus status;
	
	public void ativar(){
		this.status = SocioStatus.ATIVO;
	}
}