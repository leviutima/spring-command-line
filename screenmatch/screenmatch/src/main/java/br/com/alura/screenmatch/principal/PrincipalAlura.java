package br.com.alura.screenmatch.principal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSeries;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.ApiConsume;
import br.com.alura.screenmatch.service.ConverterDados;

public class PrincipalAlura {
        private Scanner leitura = new Scanner(System.in);
        private ApiConsume consumo = new ApiConsume();
        private ConverterDados conversor = new ConverterDados();

        private final String ENDERECO = "https://www.omdbapi.com/?t=";

        private final String API_KEY = "&apikey=6585022c";

        public void exibeMenu() {
                System.err.println("Digite o nome da série para busca");
                var nomeSerie = leitura.nextLine();
                var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") +
                                API_KEY);
                DadosSeries dadosSeries = conversor.obterDados(json, DadosSeries.class);
                System.err.println(dadosSeries);

                List<DadosTemporada> temporadas = new ArrayList<>();

                for (int i = 1; i <= dadosSeries.temporadas(); i++) {
                        var jsonTemporadas = consumo
                                        .obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
                        DadosTemporada dadosTemporada = conversor.obterDados(jsonTemporadas, DadosTemporada.class);
                        temporadas.add(dadosTemporada);
                }
                // temporadas.forEach(t -> t.episodios().forEach(e ->
                // System.out.println(e.titulo())));

                // List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                // .flatMap(t -> t.episodios().stream())
                // .collect(Collectors.toList());
                // System.err.println("Top 5 episódios");
                // // dadosEpisodios.stream()
                // // .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                // // .peek(e -> System.out.println("Primeiro filtro(N/A) " + e))
                // // .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                // // .peek(e -> System.out.println("Ordenação " + e))
                // // .limit(10)
                // // .peek(e -> System.out.println("Limite " + e))
                // // .map(e -> e.titulo().toUpperCase())
                // // .peek(e -> System.out.println("Mapeamento " + e))
                // // .forEach(System.out::println);

                List<Episodio> episodios = temporadas.stream()
                                .flatMap(t -> t.episodios().stream().map(d -> new Episodio(t.numero(), d)))
                                .collect(Collectors.toList());
                episodios.forEach(System.out::println);

                // System.out.println("Digite_o_nome_do_título_do_episódio");
                // var trechoTitulo = leitura.nextLine();
                // Optional<Episodio> episodioBuscado = episodios.stream()
                // .filter(e ->
                // e.getTitulo().toLowerCase().contains(trechoTitulo.toLowerCase()))
                // .findAny();
                // if (episodioBuscado.isPresent()) {
                // System.out.println("Episodio encontrado");
                // System.out.println("Temporada" + episodioBuscado.get().getTemporada());
                // } else {
                // System.out.println("Episódio não encontrado");
                // }

                Map<Integer, Double> avaliacoesTemporada = episodios.stream()
                                .filter(e -> e.getAvaliacao() > 0.0)
                                .collect(Collectors.groupingBy(Episodio::getTemporada,
                                                Collectors.averagingDouble(Episodio::getAvaliacao)));
                System.out.println(avaliacoesTemporada);

                DoubleSummaryStatistics est = episodios.stream()
                                                       .filter(e -> e.getAvaliacao() > 0.0)
                                                       .collect(Collectors.summarizingDouble(e -> e.getAvaliacao()));
                                                       System.out.println("média: " + est.getAverage());
                                                       System.out.println("Melhor episódio: " + est.getMax());
                                                       System.out.println("Pior episódio: " + est.getMin());

                // System.err.println("A partir de que ano você deseja ver os episódios");
                // var ano = leitura.nextInt();
                // leitura.nextLine();
                // LocalDate dataBusca = LocalDate.of(ano, 1, 1);

                // DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                // episodios.stream()
                // .filter(e -> e.getDataLancamento() != null &&
                // e.getDataLancamento().isAfter(dataBusca))
                // .forEach(e -> System.out.println(
                // "Temporada:" + e.getTemporada() +
                // "Episódio: " + e.getTitulo() +
                // "Data Lançamento: " + e.getDataLancamento().format(df)
                // ));
        }
}
