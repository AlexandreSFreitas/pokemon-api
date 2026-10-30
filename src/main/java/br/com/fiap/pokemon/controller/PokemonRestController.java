package br.com.fiap.pokemon.controller;

import br.com.fiap.pokemon.model.Pokemon;
import br.com.fiap.pokemon.model.PokemonDTO;
import br.com.fiap.pokemon.repository.PokemonRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** API REST (JSON) - testada pelo Insomnia. */
@RestController
@RequestMapping("/api/pokemon")
public class PokemonRestController {

    private final PokemonRepository pokemonRepository;

    public PokemonRestController(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PokemonDTO pokemonDTO) {
        try {
            Pokemon pokemon = new Pokemon(pokemonDTO);
            pokemonRepository.save(pokemon);
            return ResponseEntity.status(HttpStatus.CREATED).body(pokemon);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar o pokémon.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Pokemon>> consultar() {
        return ResponseEntity.ok(pokemonRepository.findAll());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> consultarPorCodigo(@PathVariable Long codigo) {
        Pokemon pokemon = pokemonRepository.findByCodigo(codigo);
        if (pokemon != null) {
            return ResponseEntity.status(HttpStatus.OK).body(pokemon);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokémon não encontrado");
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<String> excluir(@PathVariable Long codigo) {
        if (pokemonRepository.existsById(codigo)) {
            pokemonRepository.deleteById(codigo);
            return ResponseEntity.ok("Pokémon deletado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokémon não encontrado");
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<String> atualizar(@PathVariable Long codigo,
                                            @RequestBody @Valid PokemonDTO pokemonDTO) {
        if (!pokemonRepository.existsById(codigo)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokémon não encontrado");
        }
        try {
            Pokemon pokemon = new Pokemon(pokemonDTO);
            pokemon.setCodigo(codigo);
            pokemonRepository.save(pokemon);
            return ResponseEntity.ok("Pokémon atualizado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar o pokémon.");
        }
    }
}
