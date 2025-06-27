package br.com.alura.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSeries;
import br.com.alura.screenmatch.service.ApiConsume;
import br.com.alura.screenmatch.service.ConverterDados;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var apiConsume = new ApiConsume();
		var json = apiConsume.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
		System.err.println(json);
		ConverterDados conversor = new ConverterDados();
		DadosSeries dadosSeries = conversor.obterDados(json, DadosSeries.class);
		System.err.println(dadosSeries);
		var jsonEpisode = apiConsume.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=6585022c");
		DadosEpisodio dadosEpisodio = conversor.obterDados(jsonEpisode, DadosEpisodio.class);
		System.err.println(dadosEpisodio);

	}

}
