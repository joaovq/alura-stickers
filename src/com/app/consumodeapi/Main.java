package com.app.consumodeapi;

import com.app.consumodeapi.ClientHTTP.ClientHTTP;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

/*
Desafios desta aula
TODO: Consumir o endpoint de filmes mais populares da API do IMDB. Procure também, na documentação da API do IMDB, o endpoint
que retorna as melhores séries e o que retorna as séries mais populares.

TODO: Usar sua criatividade para deixar a saída dos dados mais bonitinha:
usar emojis com código UTF-8, mostrar a nota do filme como estrelinhas, decorar o terminal com cores,
negrito e itálico usando códigos ANSI, e mais!

TODO: Colocar a chave da API do IMDB em algum lugar fora do código como um arquivo de configuração
(p. ex, um arquivo .properties) ou
uma variável de ambiente

TODO: Mudar o JsonParser para usar uma biblioteca de parsing de JSON como Jackson ou GSON

 TODO: Desafio supremo: criar alguma maneira para você dar uma avaliação ao filme,
puxando de algum arquivo de configuração OU pedindo a avaliação para o usuário digitar no terminal.





*/

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        // Pegar os dados do Imdb
        // Fazer uma conexão HTTP e buscar os top 250 filmes

        String urlIMDB = "https://alura-filmes.herokuapp.com/conteudos";
        String urlNasa = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY";

        var http = new ClientHTTP();
        String json = http.searchData(urlIMDB);

        // pegar so os dados que interessam (titulo, poster, classificação
        var parser =new JsonParser();
        List<Map<String,String>> listOfContents = parser.parse(json);

        System.out.println(listOfContents.size());
        System.out.println(listOfContents.get(0));

        // Exibir  e manipular os dados

        GeneratorStickers stickers = new GeneratorStickers();

//        Utilizando emojis e mostrando os filmes da lista
        for (int i = 0; i<10 ; i++) {
            Map<String,String> film = listOfContents.get(i);

//            Produzindo figurinhas para cada imagem dos filmes
            String urlImage = film.get("image")
                                            .replaceAll("(@+)(.*).jpg$", "$1.jpg");
            String tile = film.get("title");
            String nameFile = tile + ".png";

                InputStream inputStream = new URL(urlImage).openStream();

                stickers.create(inputStream, nameFile);

            System.out.println("\u001b[1m \u2B50 ⭐ Ranking \u2B50 ⭐ : \u001b[37m \u001b[44m"+film.get("rank")+"\u001b[m\n Title: "
                    +film.get("title") + "\n Imdb Rating: " + film.get("imDbRating") +"\n Pôster: "+ film.get("image"));
        }
    }
}


