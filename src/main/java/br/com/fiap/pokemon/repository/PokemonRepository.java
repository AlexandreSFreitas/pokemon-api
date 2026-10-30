package br.com.fiap.pokemon.repository;

import br.com.fiap.pokemon.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    Pokemon findByCodigo(Long codigo);
}
