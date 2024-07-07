package literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private Integer anoNascimento;
    private Integer anoFalecimento;
    @OneToMany(mappedBy = "autores", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livros> livros = new ArrayList<>();

    public Autores() {}

    public Autores(AutoresData autoresData) {
        this.name = autoresData.name();
        this.anoNascimento = autoresData.anoNascimento();
        this.anoFalecimento = autoresData.anoFalecimento();
    }

    public Autores(String name, Integer anoNascimento, Integer anoFalecimento) {
        this.name = name;
        this.anoNascimento = anoNascimento;
        this.anoFalecimento = anoFalecimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getanoNascimento() {
        return anoNascimento;
    }

    public void setanoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getanoFalecimento() {
        return anoFalecimento;
    }

    public void setanoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public List<Livros> getLivros() {
        return livros;
    }

    public void setLivros(List<Livros> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        return "------------------- Author -----------------" +
                "\nAutores: " + name +
                "\nano Nascimento: " + anoNascimento +
                "\nano Falecimento: " + anoFalecimento +
                "\nLivros: " + livros.stream().map(l -> l.getTitlo()).collect(Collectors.toList())+
                "\n-------------------------------------------\n";
    }
}
