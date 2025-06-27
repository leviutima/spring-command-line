package br.com.alura.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.screenmatch.service.ApiConsume;

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
		json = apiConsume.obterDados("https://coffee.alexflipnote.dev/random.json");
		System.err.println(json);
	}

}
