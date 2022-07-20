package com.app.consumodeapi.ContentExtractors;

import com.app.consumodeapi.Content;
import com.app.consumodeapi.ContentExtractor;
import com.app.consumodeapi.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// TODO: utilizar uma interface para os metodos? coisa a si pensar.

public class ContentExtractorIMDB  implements ContentExtractor {
    public List<Content> extract (String json){
        var parser =new JsonParser();
        List<Map<String,String>> atributesList = parser.parse(json);

        List<Content> contentList = new ArrayList<>();

        for (Map<String,String> atribute: atributesList) {
            String title = atribute.get("title");
            String urlImage = atribute.get("image");

            Content content =  new Content(title, urlImage);

            contentList.add(content);

        }
        return contentList;
    }
}
