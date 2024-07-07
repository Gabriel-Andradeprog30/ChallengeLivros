package literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutoresData(@JsonAlias("nome") String name, @JsonAlias("ano_nascimento") Integer anoNascimento,
                          @JsonAlias("ano_falecimento") Integer anoFalecimento) {

}
