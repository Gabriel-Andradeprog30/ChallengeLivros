package literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivrosData(@JsonAlias("titlo") String titlo, @JsonAlias("autores") List<AutoresData> autores,
                         @JsonAlias("idioma") List<String> idioma,
                         @JsonAlias("download") Integer downloadsNumeros) {

}
