package br.com.fiap.pokemon.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "TB_POKEMON")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nome;
    private String tipo;
    private Double preco;
    private LocalDate dataDeNascimento;
    private LocalDate dataDeCaptura;
    private String urlImagem;

    public Pokemon() {
    }

    public Pokemon(PokemonDTO dto) {
        this.nome = dto.nome();
        this.tipo = dto.tipo();
        this.preco = dto.preco();
        this.dataDeNascimento = dto.dataDeNascimento();
        this.dataDeCaptura = dto.dataDeCaptura();
        this.urlImagem = dto.urlImagem();
    }

    public Long getCodigo() { return codigo; }
    public void setCodigo(Long codigo) { this.codigo = codigo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
    public LocalDate getDataDeNascimento() { return dataDeNascimento; }
    public void setDataDeNascimento(LocalDate d) { this.dataDeNascimento = d; }
    public LocalDate getDataDeCaptura() { return dataDeCaptura; }
    public void setDataDeCaptura(LocalDate d) { this.dataDeCaptura = d; }
    public String getUrlImagem() { return urlImagem; }
    public void setUrlImagem(String urlImagem) { this.urlImagem = urlImagem; }
}
