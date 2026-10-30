package br.com.fiap.pokemon.controller;

import br.com.fiap.pokemon.model.Pokemon;
import br.com.fiap.pokemon.model.PokemonDTO;
import br.com.fiap.pokemon.repository.PokemonRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/** Front-end Thymeleaf + Bootstrap. */
@Controller
public class PokemonController {

    private final PokemonRepository pokemonRepository;

    public PokemonController(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @GetMapping("/")
    public ModelAndView raiz() {
        return new ModelAndView("redirect:/pokemon");
    }

    @GetMapping("/pokemon")
    public ModelAndView consultar() {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        ModelAndView mv = new ModelAndView("pokemons");
        mv.addObject("pokemons", pokemons);
        return mv;
    }

    @GetMapping("/pokemon/novo")
    public ModelAndView novo() {
        return new ModelAndView("formulario");
    }

    @PostMapping("/pokemon")
    public ModelAndView cadastrar(@Valid PokemonDTO pokemonDTO, BindingResult result) {
        if (result.hasErrors()) {
            return formularioComErros(new Pokemon(pokemonDTO), null, result);
        }
        pokemonRepository.save(new Pokemon(pokemonDTO));
        return new ModelAndView("redirect:/pokemon");
    }

    @GetMapping("/pokemon/editar/{codigo}")
    public ModelAndView consultarPorCodigo(@PathVariable Long codigo) {
        Pokemon pokemon = pokemonRepository.findByCodigo(codigo);
        if (pokemon == null) {
            return new ModelAndView("redirect:/pokemon");
        }
        ModelAndView mv = new ModelAndView("formulario");
        mv.addObject("pokemon", pokemon);
        mv.addObject("acao", "/pokemon/atualizar/" + codigo);
        return mv;
    }

    @PostMapping("/pokemon/atualizar/{codigo}")
    public ModelAndView atualizar(@PathVariable Long codigo, @Valid PokemonDTO pokemonDTO,
                                  BindingResult result) {
        if (result.hasErrors()) {
            return formularioComErros(new Pokemon(pokemonDTO), "/pokemon/atualizar/" + codigo, result);
        }
        Pokemon pokemon = new Pokemon(pokemonDTO);
        pokemon.setCodigo(codigo);
        pokemonRepository.save(pokemon);
        return new ModelAndView("redirect:/pokemon");
    }

    // O verbo é GET porque formulários/links HTML não suportam DELETE (ainda assim é uma exclusão).
    @GetMapping("/pokemon/excluir/{codigo}")
    public ModelAndView excluir(@PathVariable Long codigo) {
        pokemonRepository.deleteById(codigo);
        return new ModelAndView("redirect:/pokemon");
    }

    private ModelAndView formularioComErros(Pokemon pokemon, String acao, BindingResult result) {
        ModelAndView mv = new ModelAndView("formulario");
        mv.addObject("pokemon", pokemon);
        mv.addObject("acao", acao == null ? "/pokemon" : acao);
        mv.addObject("erros", result.getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage()).toList());
        return mv;
    }
}
