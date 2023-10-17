package br.com.frutti.seguranca.repository;

import br.com.frutti.seguranca.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Long, Perfil> {
}
