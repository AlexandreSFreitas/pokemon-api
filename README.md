# Pokémon API — Praticando 

API Restful em Spring Boot 3.3 / Java 21 / Oracle com front-end Thymeleaf + Bootstrap.

## Conceitos aplicados
| Aula | Conceito | Onde está |
|---|---|---|
| 44–46 | CRUD REST, `ResponseEntity`, `@PathVariable`, `@RequestBody`, status 200/201/400/404 | `PokemonRestController` |
| 45 | Bean Validation (`@NotBlank`, `@NotNull`, `@PositiveOrZero`, `@PastOrPresent`) + `@Valid` | `PokemonDTO` (record) |
| 45 | `findByCodigo` no repositório | `PokemonRepository` |
| 46 | Requisições no Insomnia | `insomnia-pokemon.json` |
| 47 | Thymeleaf + Bootstrap, `ModelAndView`, `BindingResult`, redirect | `PokemonController`, `templates/` |
| 48 | Excluir (GET) e atualizar (POST) pelo front, ícones Bootstrap Icons, `if` simplificado | `pokemons.html`, `formulario.html` |
| 49 | Formatação de moeda/data (`#numbers`, `#temporals`), build `.jar`, Dockerfile, variáveis de ambiente, Git, Render | `pokemons.html`, `Dockerfile`, `application.properties` |

## Rodar localmente
Maven Wrapper (necessário para o Dockerfile): se a pasta não tiver `mvnw`, gere com `mvn -N wrapper:wrapper`
(ou crie o projeto em start.spring.io e copie `src/`, `pom.xml` e `Dockerfile` por cima).

```bash
export DB_URL=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
export DB_USER=SEU_RM
export DB_PASSWORD=SUA_SENHA
./mvnw spring-boot:run
```
No Windows (cmd): `set DB_URL=...`.  Front: http://localhost:8080/pokemon  | API: http://localhost:8080/api/pokemon

## Build
```bash
./mvnw clean -DskipTests package
java -jar target/pokemon-0.0.1-SNAPSHOT.jar
```

## Git
```bash
git init
git add .
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/SUA_CONTA/SEU_REPOSITORIO
git push -u origin main
```

## Deploy no Render
1. render.com → entrar com GitHub → **New → Web Service** → escolher o repositório.
2. Nome único (ex.: `pokemon-api-RM12345`), runtime **Docker**, instância **Free**.
3. Environment Variables: `DB_URL`, `DB_USER`, `DB_PASSWORD`.
4. **Deploy Web Service** e aguardar. A URL `https://<nome>.onrender.com/pokemon` abre o front.
   (Free tier "dorme" por inatividade: abra a URL uns 50 s antes de apresentar.)
   Para o Insomnia, troque `base_url` no Base Environment pela URL do Render.

4. Tentar cadastrar com dados inválidos (data futura, preço negativo) → erros de validação.
5. **Editar** (formulário preenchido) → salvar → lista atualizada.
6. **Excluir** (confirmação) → some da lista.
7. (Opcional) Insomnia: POST, GET, GET por código, PUT, DELETE e um 404.
