package literalura.main;
import literalura.model.Autores;
import literalura.model.Livros;
import literalura.model.LivrosData;
import literalura.repository.LivrosRepository;
import literalura.service.ConsumeAPI;
import literalura.service.DataConversao;
import literalura.service.IdiomaConversao;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final LivrosRepository repository;
    private final String API_URL = "https://gutendex.com/books/?search=";

    private Scanner leitura = new Scanner(System.in);
    private ConsumeAPI consumeAPI = new ConsumeAPI();
    private DataConversao dataConversao = new DataConversao();
    private IdiomaConversao IdiomaConversao = new IdiomaConversao();

    public Main(LivrosRepository repository) {
        this.repository = repository;
    }

    public void showMenu() {
        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                  
                    1- buscar livro pelo titulo
                    2- listar livros registrados
                    3- listar autores registrados
                    4- listar autores vivos em um determinado ano
                    5- listar livros em um determinado idioma
                   
                    0 - Exit
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivrosTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    lisarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosAno();
                    break;
                case 5:
                    listarLivrosIdioma();
                    break;
                case 0:
                    System.out.println("Sair");
                    break;
                default:
                    System.out.println("Invalido");
            }
        }
    }

    private void buscarLivrosTitulo() {
        System.out.println("insira um titulo de livro: ");
        var livroNome = leitura.nextLine();
        String searchUrl = API_URL.concat(livroNome.replace(" ", "+").toLowerCase().trim());

        String json = consumeAPI.getData(searchUrl);
        String jsonLivro = dataConversao.extractObjectFromJson(json, "resultado");

        List<LivrosData> livroDto = dataConversao.getList(jsonLivro, LivrosData.class);

        if (livroDto.size() > 0) {
            Livros livros = new Livros(livroDto.get(0));

            Autores autor = repository.findAuthorByName(livros.getAutor().getName());
            if (autor != null) {
                livros.setAutor(null);
                repository.save(livros);
                livros.setAutor(autor);
            }
            livros = repository.save(livros);
            System.out.println(livros);
        } else {
            System.out.println("Livro nao encontrado");
        }
    }

    private void listarLivrosRegistrados() {
        List<Livros> livros = repository.findAll();
        livros.forEach(System.out::println);
    }

    private void lisarAutoresRegistrados() {
        List<Autores> autores = repository.searchAtores();
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivosAno() {
        try {
            System.out.println("Digite um ano: ");
            var ano = leitura.nextInt();
            leitura.nextLine();

            List<Autores> autores = repository.searchAutoresVivos(ano);
            autores.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Invalido, insira um numero inteiro.");
            leitura.nextLine();
        }
    }

    private void listarLivrosIdioma() {
        System.out.println("insira um idioma: ");
        var idioma = IdiomaConversao.converteIdioma(leitura.nextLine());
        List<Livros> livros = repository.findLivrosPorIdioma(idioma);
        if (!livros.isEmpty()) {
            livros.forEach(System.out::println);
        } else {
            System.out.printf("Nao tem no livros no%s idioma %n", idioma);
        }
    }
}
