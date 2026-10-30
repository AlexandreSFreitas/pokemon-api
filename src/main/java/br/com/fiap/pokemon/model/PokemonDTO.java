package br.com.fiap.pokemon.model;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PokemonDTO(
        @NotBlank
        String nome,
        @NotBlank
        String tipo,
        @NotNull
        @PositiveOrZero
        Double preco,
        @NotNull
        @PastOrPresent
        LocalDate dataDeNascimento,
        @NotNull
        @PastOrPresent
        LocalDate dataDeCaptura,
        String urlImagem
) {
}
