package com.app.consumodeapi;

import com.app.consumodeapi.ClientHTTP.ClientHTTP;
import com.app.consumodeapi.ContentExtractors.ContentExtractorIMDB;
import com.app.consumodeapi.ContentExtractors.ContentExtractorNasa;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

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

//        String urlIMDB = "https://alura-filmes.herokuapp.com/conteudos";
//        ContentExtractor extractorIMDB = new ContentExtractorIMDB();
        String urlNasa = "https://api.nasa.gov/planetary/apod?api_key=1VGecNjTX2r" +
                                        "TIrmmRQkvMGf9OWPuTWQr9mtPlSD5&start_date=2022-06-12&end_date=2022-06-14";
        ContentExtractor extractorNasa = new ContentExtractorNasa();

        var http = new ClientHTTP();
        String json = http.searchData(urlNasa);


        // pegar so os dados que interessam (titulo, poster, classificação

        List<Content> contentList = extractorNasa.extract(json);

        // Exibir  e manipular os dados

        GeneratorStickers stickers = new GeneratorStickers();

//        Utilizando emojis e mostrando os filmes da lista
        for(int i = 0; i<contentList.size() ; i++){
            Content content = contentList.get(i);

//            Produzindo figurinhas para cada imagem dos filmes

                InputStream inputStream = new URL(content.getUrlImage()).openStream();
                String nameFile = content.getTitle() + ".png";

            stickers.create(inputStream, nameFile);

//                Mostrar as fotos e fazer stickers consumindo API da Nasa
            System.out.println("Title: "
                    +content.getTitle() + "\n Image: "+ content.getUrlImage());

//          para mostrar os filmes do IMDB
      /*      System.out.println("\u001b[1m \u2B50 ⭐ Ranking \u2B50 ⭐ : \u001b[37m \u001b[44m"+content.get("rank")+
                                "\u001b[m\n Title: " +content.get("title") + "\n Imdb Rating: "
                         + content.get("imDbRating") +"\n Pôster: "+ content.get("image"));*/
        }
    }
}


