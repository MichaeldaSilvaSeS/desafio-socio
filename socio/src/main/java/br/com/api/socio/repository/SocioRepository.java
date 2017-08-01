package br.com.api.socio.repository;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.api.socio.model.Socio;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long>{
	@Query("SELECT s FROM Socio s WHERE s.status = 'ATIVO' ")
	public Collection<Socio> listar();
	
	@Query("SELECT CASE WHEN COUNT(s.idSocio) > 0 THEN true ELSE false END FROM Socio s WHERE s.idSocio = :paramIdSocio")
	public boolean verificarSeExiste(@Param("paramIdSocio") Long idSocio);
	
	@Query("SELECT CASE WHEN COUNT(s.idSocio) > 0 THEN true ELSE false END FROM Socio s WHERE s.email = :paramEmail and s.status = 'ATIVO' ")
	public boolean verificarSeExisteAtivo(@Param("paramEmail") String email);
	
	@Query("SELECT s FROM Socio s WHERE s.email = :paramEmail")
	public Socio selecionarPorEmail(@Param("paramEmail") String email);
	
	@Query("SELECT s FROM Socio s WHERE s.idSocio = :paramIdSocio")
	public Socio selecionar(@Param("paramIdSocio") Long idSocio);

	@Query("SELECT CASE WHEN COUNT(s.idSocio) > 0 THEN true ELSE false END FROM Socio s WHERE s.email = :paramEmail")
	public boolean verificarSeExistePorEmail(@Param("paramEmail") String email);
}