package br.com.alura.screenmatch.principal;

import java.util.Scanner;

import br.com.alura.screenmatch.model.DadosSeries;
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
		var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
		DadosSeries dadosSeries = conversor.obterDados(json, DadosSeries.class);
        System.err.println(dadosSeries);
    }
}
