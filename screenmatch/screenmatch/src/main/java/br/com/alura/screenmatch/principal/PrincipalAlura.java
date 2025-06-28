package br.com.alura.screenmatch.principal;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import br.com.alura.screenmatch.service.ApiConsume;
import br.com.alura.screenmatch.service.ConverterDados;

public class PrincipalAlura {
    private Scanner leitura = new Scanner(System.in);
    private ApiConsume consumo = new ApiConsume();
    private ConverterDados conversor = new ConverterDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";

    private final String API_KEY = "&apikey=6585022c";

    public void exibeMenu() {
        // System.err.println("Digite o nome da série para busca");
        // var nomeSerie = leitura.nextLine();
        // var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        // DadosSeries dadosSeries = conversor.obterDados(json, DadosSeries.class);
        // System.err.println(dadosSeries);

        // List<DadosTemporada> temporadas = new ArrayList<>();


        // for (int i = 1; i <= dadosSeries.temporadas(); i++) {
        //     var jsonTemporadas = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
        //     DadosTemporada dadosTemporada = conversor.obterDados(jsonTemporadas, DadosTemporada.class);
        //     temporadas.add(dadosTemporada);
        // }
        // temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
        

        // // for( int i = 0; i < dadosSeries.temporadas(); i++) {
        // //     List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
        // //     for(int j = 0; j < episodiosTemporada.size(); j++) {
        // //         System.err.println(episodiosTemporada.get(j).titulo());
        // //     }
        // // }

        List<String> nomes = Arrays.asList("Jacque", "Iasmin", "Paulo", "Levi");
        nomes.stream().sorted().limit(3).filter(l -> l.startsWith("L")).map(l -> l.toUpperCase()).forEach(System.out::println);
               
        
    }
}
