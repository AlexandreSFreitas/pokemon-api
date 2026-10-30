package br.com.fiap.pokemon.config;

import br.com.fiap.pokemon.model.Pokemon;
import br.com.fiap.pokemon.model.PokemonDTO;
import br.com.fiap.pokemon.repository.PokemonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DadosIniciais implements CommandLineRunner {

    private final PokemonRepository pokemonRepository;

    public DadosIniciais(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public void run(String... args) {
        if (pokemonRepository.count() > 0) {
            return;
        }
        String img = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/";
        List<PokemonDTO> iniciais = List.of(
                new PokemonDTO("Pikachu", "Elétrico", 59.90,
                        LocalDate.of(2020, 2, 27), LocalDate.of(2024, 5, 10), img + "025.png"),
                new PokemonDTO("Charmander", "Fogo", 45.50,
                        LocalDate.of(2021, 6, 15), LocalDate.of(2024, 7, 20), img + "004.png"),
                new PokemonDTO("Bulbasaur", "Planta", 39.99,
                        LocalDate.of(2021, 3, 8), LocalDate.of(2024, 8, 5), img + "001.png"),
                new PokemonDTO("Squirtle", "Água", 42.00,
                        LocalDate.of(2022, 1, 12), LocalDate.of(2024, 9, 1), img + "007.png"),
                new PokemonDTO("Eevee", "Normal", 55.00,
                        LocalDate.of(2022, 9, 30), LocalDate.of(2024, 10, 3), img + "133.png")
        );
        iniciais.forEach(dto -> pokemonRepository.save(new Pokemon(dto)));
    }
}