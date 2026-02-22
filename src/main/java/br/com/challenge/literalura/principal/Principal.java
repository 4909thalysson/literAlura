package br.com.challenge.literalura.principal;

import br.com.challenge.literalura.service.AutorService;
import br.com.challenge.literalura.service.EstatisticaService;
import br.com.challenge.literalura.service.LivroService;
import br.com.challenge.literalura.model.Livros;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    private final Scanner scanner = new Scanner(System.in);
    private final LivroService livroService;
    private final AutorService autorService;
    private final EstatisticaService estatisticaService;

    public Principal(LivroService livroService,
                     AutorService autorService,
                     EstatisticaService estatisticaService) {

        this.livroService = livroService;
        this.autorService = autorService;
        this.estatisticaService = estatisticaService;
    }

    public void exibeMenu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("""
                -------------------------
                LITERALURA - MENU
                -------------------------
                1 - Listar livro por título
                2 - Listar livro por autor
                3 - Listar autores
                4 - Informe um trecho do título do livro:
                5 - Listar livros salvos
                6 - Estatísticas
                0 - Sair
                """);

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> buscarLivroPorTitulo();
                case 2 -> buscarLivroPorAutor();
                case 3 -> autorService.buscaAutores();
                case 4 -> buscarLivroPorTituloNaApi();
                case 5 -> listarLivrosSalvos();
                case 6 -> menuEstatisticas();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida");
            }
        }
    }


    private void buscarLivroPorTitulo() {
        System.out.print("Informe o título do livro: ");
        String titulo = scanner.nextLine();

        List<Livros> livros = livroService.buscarPorTitulo(titulo);

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado com o título: " + titulo);
        } else {
            System.out.println("----- LIVROS ENCONTRADOS -----");
            for (Livros l : livros) {
                System.out.println(
                        "ID: " + l.getId() +
                                " | Título: " + l.getTitulo() +
                                " | Autor: " + l.getAutor() +
                                " | Idioma: " + l.getIdioma() +
                                " | Downloads: " + l.getNumeroDownloads()
                );
            }
        }
    }


    private void buscarLivroPorAutor() {
        System.out.print("Informe o nome do autor: ");
        String autor = scanner.nextLine();

        List<Livros> livros = livroService.buscarPorAutor(autor);

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado do autor: " + autor);
        } else {
            System.out.println("----- LIVROS ENCONTRADOS -----");
            for (Livros l : livros) {
                System.out.println(
                        "ID: " + l.getId() +
                                " | Título: " + l.getTitulo() +
                                " | Autor: " + l.getAutor() +
                                " | Idioma: " + l.getIdioma() +
                                " | Downloads: " + l.getNumeroDownloads()
                );
            }
        }
    }


    private void buscarLivroPorTituloNaApi() {
        System.out.print("Informe o título do livro: ");
        String titulo = scanner.nextLine();

        List<Livros> livros = livroService.buscarESalvarLivrosPorTitulo(titulo);

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para: " + titulo);
        } else {
            System.out.println("----- LIVROS ENCONTRADOS NO GUTENDEX -----");
            for (Livros l : livros) {
                System.out.println(
                        "ID: " + l.getId() +
                                " | Título: " + l.getTitulo() +
                                " | Autor: " + l.getAutor() +
                                " | Idioma: " + l.getIdioma() +
                                " | Downloads: " + l.getNumeroDownloads()
                );
            }
        }
    }


    private void listarLivrosSalvos() {
        List<Livros> livros = livroService.listarTodos();

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro salvo.");
        } else {
            System.out.println("----- LIVROS SALVOS -----");
            for (Livros l : livros) {
                System.out.println(
                        "ID: " + l.getId() +
                                " | Título: " + l.getTitulo() +
                                " | Autor: " + l.getAutor() +
                                " | Idioma: " + l.getIdioma() +
                                " | Downloads: " + l.getNumeroDownloads()
                );
            }
        }
    }

    private void menuEstatisticas() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("""
            -------- ESTATÍSTICAS --------
            1 - Autores vivos em determinado ano
            2 - Quantidade de livros por idioma
            3 - Top 05 livros mais baixados
            0 - Voltar
            """);

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1 -> {
                    System.out.print("Digite o ano: ");
                    int ano = scanner.nextInt();
                    scanner.nextLine();

                    var autores = estatisticaService.autoresVivosNoAno(ano);

                    if (autores.isEmpty()) {
                        System.out.println("Nenhum autor encontrado.");
                    } else {
                        autores.forEach(a ->
                                System.out.println(
                                        a.getNome() + " (" +
                                                a.getNasc() + " - " +
                                                (a.getMorte() == null ? "Vivo" : a.getMorte()) +
                                                ")"
                                )
                        );
                    }
                }

                case 2 -> {
                    var resultado = estatisticaService.quantidadeLivrosPorIdioma();

                    resultado.forEach(linha -> {
                        String idioma = (String) linha[0];
                        Long quantidade = (Long) linha[1];

                        System.out.println("Idioma: " + idioma +
                                " | Quantidade: " + quantidade);
                    });
                }

                case 3 -> {
                    var livros = estatisticaService.top5LivrosMaisBaixados();

                    if (livros.isEmpty()) {
                        System.out.println("Nenhum livro encontrado.");
                    } else {
                        System.out.println("----- TOP 5 LIVROS MAIS BAIXADOS -----");

                        int posicao = 1;
                        for (var l : livros) {
                            System.out.println(
                                    posicao++ + "º - " +
                                            l.getTitulo() +
                                            " | Downloads: " +
                                            l.getNumeroDownloads()
                            );
                        }
                    }
                }

                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida");
            }
        }
    }
}