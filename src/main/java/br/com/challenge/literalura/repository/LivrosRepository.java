package br.com.challenge.literalura.repository;

import br.com.challenge.literalura.model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivrosRepository extends JpaRepository<Livros, Long> {

    boolean existsByTitulo(String titulo);

    // Buscar livros pelo título (contendo a palavra, case insensitive)
    List<Livros> findByTituloContainingIgnoreCase(String titulo);

    // Buscar livros pelo autor
    List<Livros> findByAutorContainingIgnoreCase(String autor);

    @Query("""
        SELECT l.idioma, COUNT(l)
        FROM Livros l
        GROUP BY l.idioma
    """)
    List<Object[]> quantidadeLivrosPorIdioma();

    List<Livros> findTop5ByOrderByNumeroDownloadsDesc();
}