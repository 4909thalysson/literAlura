package br.com.challenge.literalura;

import br.com.challenge.literalura.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("br.com.challenge.literalura.model") // <--- adiciona o pacote das entidades
public class LiteraluraApplication implements CommandLineRunner {

    private final Principal principal;

    public LiteraluraApplication(Principal principal) {
        this.principal = principal;
    }

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        principal.exibeMenu();
    }
}
