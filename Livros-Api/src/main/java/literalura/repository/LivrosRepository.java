package literalura.repository;

import literalura.model.Autores;
import literalura.model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivrosRepository extends JpaRepository<Livros, Long>{

    List<Livros> findByIdioma(String idioma);

    Integer countByIdioma(String idioma);

    @Query("SELECT b.downloadNumbers FROM Books b")
    List<Integer> searchDownloadsNumeros();

    @Query("SELECT a FROM Books b JOIN b.author a")
    List<Autores> searchAtores();

    @Query("SELECT a FROM Books b JOIN b.author a WHERE a.anoNascimento <= :year and a.anoFalecimento >= :year")
    List<Autores> searchAutoresVivos(Integer year);

    @Query("SELECT a FROM Books b JOIN b.author a WHERE a.name = :name")
    Autores findAuthorByName(String name);

    @Query("SELECT b FROM Books b WHERE b.language LIKE %:language%")
    List<Livros> findLivrosPorIdioma(String idioma);
}
