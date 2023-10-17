package br.com.frutti.seguranca.repository;

import br.com.frutti.seguranca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Long, Usuario> {
    public Optional<Usuario> findByEmail(String email);
}
