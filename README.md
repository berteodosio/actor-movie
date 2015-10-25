# ActorMovie
Passando o nome de um ou mais atores, exibe os filmes nos quais esses atuaram.

## Para funcionar

É necessário, para o funcionamento do app, que uma classe ApiConfiguration (localizada no package "com.berteodosio.actormovie.constants" deva ser criada. Esta classe deve conter como atributos <b>uma String constante e pública, de nome API_KEY, que tem como valor a key a ser usada para as requisições</b>. Sem este arquivo, o app não funcionará. Abaixo, um exemplo de como deve ser esta classe:

```
package com.berteodosio.actormovie.constants;

public class ApiConfiguration {
    public static final String API_KEY = "valor-da-apikey";
}
```
