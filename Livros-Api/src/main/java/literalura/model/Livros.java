package literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titlo;
    @ManyToOne(cascade = CascadeType.ALL)
    private Autores autor;
    private String idioma;
    private Integer downloadsNumeros;

    public Livros() {}

    public Livros(LivrosData livrosData) {
        this.titlo = livrosData.titlo();
        Autores autores = new Autores(livrosData.autores().get(0));
        this.autor = autores;
        this.idioma = livrosData.idioma().get(0);
        this.downloadsNumeros = livrosData.downloadsNumeros();
    }

    public Livros(Long id, String titlo, Autores autores, String idioma, Integer downloadsNumeros) {
        this.titlo = titlo;
        this.autor = autores;
        this.idioma = idioma;
        this.downloadsNumeros = downloadsNumeros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitlo() {
        return titlo;
    }

    public void setTitlo(String titlo) {
        this.titlo = titlo;
    }

    public Autores getAutor() {
        return autor;
    }

    public void setAutor(Autores author) {
        this.autor = author;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDownloadsNumeros() {
        return downloadsNumeros;
    }

    public void setDownloadsNumeros(Integer downloadsNumeros) {
        this.downloadsNumeros = downloadsNumeros;
    }

    @Override
    public String toString() {
        return
                "\nTitlo: " + titlo +
                "\nAutor: " + autor.getName() +
                "\nidioma: " + idioma +
                "\nDownloads Numeros: " + downloadsNumeros;
    }
}
