package literalura;

import literalura.main.Main;
import literalura.repository.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LivrosApi implements CommandLineRunner {
	@Autowired
	private LivrosRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(LivrosApi.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(repository);
		main.showMenu();
	}

}
