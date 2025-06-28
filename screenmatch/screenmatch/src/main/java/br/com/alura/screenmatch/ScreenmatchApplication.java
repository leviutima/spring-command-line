package br.com.alura.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.screenmatch.principal.PrincipalAlura;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PrincipalAlura principal = new PrincipalAlura();
		principal.exibeMenu();
		// List<DadosTemporada> temporadas = new ArrayList<>();

		// for (int i = 1; i<=dadosSeries.temporadas(); i++) {
		// 	var jsonTemporadas = apiConsume.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=6585022c");
		// 	DadosTemporada dadosTemporada = conversor.obterDados(jsonTemporadas, DadosTemporada.class);
		// 	temporadas.add(dadosTemporada);
		// }
		// System.out.println(temporadas);
	}

}
