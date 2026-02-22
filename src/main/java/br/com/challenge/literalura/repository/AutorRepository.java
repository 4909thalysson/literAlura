package br.com.challenge.literalura.repository;

import br.com.challenge.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Buscar autores pelo nome (case insensitive)
    List<Autor> findByNomeContainingIgnoreCase(String nome);

    @Query("""
        SELECT a FROM Autor a
        WHERE YEAR (a.nasc) <= :ano
        AND (a.morte IS NULL OR YEAR (a.morte) >= :ano)
    """)
    List<Autor> autoresVivosNoAno(@Param("ano") Integer ano);
}